package com.fiap.hackaton.sus.helper.handlers;

import com.fiap.hackaton.sus.helper.exceptions.BadRequestBusinessException;
import com.fiap.hackaton.sus.helper.exceptions.InvalidCredentialsBusinessException;
import com.fiap.hackaton.sus.helper.exceptions.NotFoundBusinessException;
import com.fiap.hackaton.sus.helper.exceptions.UnauthorizedAccessBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler(BadRequestBusinessException.class)
    public final ResponseEntity<ProblemExceptionOutput> handlerBadRequestBusinessException(
            BadRequestBusinessException ex, WebRequest request) {
        ProblemExceptionOutput problema = new ProblemExceptionOutput(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<ProblemExceptionOutput>(problema, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundBusinessException.class)
    public final ResponseEntity<ProblemExceptionOutput> handlerNotFoundBussinessException(NotFoundBusinessException ex,
                                                                                          WebRequest request) {
        ProblemExceptionOutput problema = new ProblemExceptionOutput(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<ProblemExceptionOutput>(problema, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedAccessBusinessException.class)
    public final ResponseEntity<ProblemExceptionOutput> handlerUnauthorizedBusinessException(
            UnauthorizedAccessBusinessException ex, WebRequest request) {
        ProblemExceptionOutput problema = new ProblemExceptionOutput(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<ProblemExceptionOutput>(problema, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidCredentialsBusinessException.class)
    public final ResponseEntity<ProblemExceptionOutput> handlerInvalidCredentialsBussinesException(
            InvalidCredentialsBusinessException ex, WebRequest request) {
        ProblemExceptionOutput problema = new ProblemExceptionOutput(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<ProblemExceptionOutput>(problema, HttpStatus.UNAUTHORIZED);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<FieldsExceptionOutput> campos = ex.getBindingResult().getAllErrors().stream().map(error -> {
            String fieldName = (error instanceof FieldError) ? ((FieldError) error).getField() : error.getObjectName();
            return FieldsExceptionOutput.builder().name(fieldName).message(error.getDefaultMessage()).build();
        }).collect(Collectors.toList());

        ProblemExceptionOutput problema = new ProblemExceptionOutput(HttpStatus.BAD_REQUEST.value(),
                "Erro de validação. Verifique os campos informados.", campos);
        return new ResponseEntity<>(problema, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ProblemExceptionOutput> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("Ocorreu um erro interno: " + ex.getMessage(), ex);

        ProblemExceptionOutput problema = new ProblemExceptionOutput(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno no servidor. Tente novamente mais tarde.");
        return new ResponseEntity<ProblemExceptionOutput>(problema, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
