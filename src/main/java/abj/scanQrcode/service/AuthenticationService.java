package abj.scanQrcode.service;

import abj.scanQrcode.dto.auth.AuthenticationRequest;
import abj.scanQrcode.dto.auth.AuthenticationResponse;
import abj.scanQrcode.dto.responce.DataDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthenticationService {

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    ResponseEntity<DataDto<AuthenticationResponse>> accessToken(AuthenticationRequest request);

}
