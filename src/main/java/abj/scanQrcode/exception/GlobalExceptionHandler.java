package abj.scanQrcode.exception;

import abj.scanQrcode.dto.responce.AppErrorDto;
import abj.scanQrcode.dto.responce.DataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@CrossOrigin
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handlerUserNotFoundException(UsernameNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(
                DataDto.<AppErrorDto>builder()
                        .success(false)
                        .appErrorDto(
                                new AppErrorDto(
                                        exception.getMessage(),
                                        request,
                                        HttpStatus.METHOD_NOT_ALLOWED,
                                        exception.getDeveloperMessage()
                                )
                        )
                        .build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {AlreadyException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handlerAlreadyUsernameException(AlreadyException exception, WebRequest request) {
        return new ResponseEntity<>(
                DataDto.<AppErrorDto>builder()
                        .success(false)
                        .appErrorDto(
                                new AppErrorDto(
                                        exception.getMessage(),
                                        request,
                                        HttpStatus.ALREADY_REPORTED,
                                        exception.getDeveloperMessage()
                                )
                        )
                        .build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<DataDto<AppErrorDto>> handlerException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(
                DataDto.<AppErrorDto>builder()
                        .success(false)
                        .appErrorDto(
                                new AppErrorDto(
                                        exception.getMessage(),
                                        request,
                                        HttpStatus.METHOD_NOT_ALLOWED,
                                        exception.toString()
                                )
                        )
                        .build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {NotNullFieldException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handlerNotNullFieldException(NotNullFieldException exception, WebRequest request) {
        return new ResponseEntity<>(
                DataDto.<AppErrorDto>builder()
                        .success(false)
                        .appErrorDto(
                                new AppErrorDto(
                                        exception.getMessage(),
                                        request,
                                        HttpStatus.BAD_REQUEST,
                                        exception.getDeveloperMessage()
                                )
                        )
                        .build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handlerAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        return new ResponseEntity<>(
                DataDto.<AppErrorDto>builder()
                        .success(false)
                        .appErrorDto(
                                new AppErrorDto(
                                        exception.getMessage(),
                                        request,
                                        HttpStatus.FORBIDDEN,
                                        ""
                                )
                        )
                        .build(),
                HttpStatus.OK);
    }

}
