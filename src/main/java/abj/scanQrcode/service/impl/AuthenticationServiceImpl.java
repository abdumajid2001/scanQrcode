package abj.scanQrcode.service.impl;

import abj.scanQrcode.configuration.security.JwtService;
import abj.scanQrcode.configuration.security.MyUserDetails;
import abj.scanQrcode.configuration.security.TokenService;
import abj.scanQrcode.dto.auth.AuthenticationRequest;
import abj.scanQrcode.dto.auth.AuthenticationResponse;
import abj.scanQrcode.dto.responce.AppErrorDto;
import abj.scanQrcode.dto.responce.DataDto;
import abj.scanQrcode.service.AuthenticationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserDetailsServiceImpl userDetailsService;

    @Value("${baseUrl}")
    private String baseUrl;

    @Override
    public synchronized void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        final String refreshToken = authHeader.substring(7);
        final String username = jwtService.extractUsername(refreshToken);

        if (Objects.nonNull(username)) {
            MyUserDetails user = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                tokenService.revokeAllUserTokens(user.getId());
                tokenService.saveUserToken(user.getId(), accessToken);
                AuthenticationResponse authenticationResponse = AuthenticationResponse
                        .builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                mapper.writeValue(response.getOutputStream(), new DataDto<>(authenticationResponse));
            }
        }
    }

    @Override
    public ResponseEntity<DataDto<AuthenticationResponse>> accessToken(AuthenticationRequest request) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(baseUrl + "/auth/authenticate");
            byte[] bytes = mapper.writeValueAsBytes(request);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = mapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("success") && json_auth.get("success").asBoolean()) {
                JsonNode node = json_auth.get("data");
                AuthenticationResponse authenticationResponse = mapper.readValue(node.toString(), AuthenticationResponse.class);
                return new ResponseEntity<>(new DataDto<>(authenticationResponse), HttpStatus.OK);
            }
            return new ResponseEntity<>(new DataDto<>(mapper.readValue(json_auth.get("error").toString(),
                    AppErrorDto.class)), HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .message(e.getLocalizedMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        }
    }

}
