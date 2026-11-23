package cr.ac.ucr.if6100.portal.exception;

import cr.ac.ucr.if6100.portal.api.ApiErrorResponse;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ApiErrorResponse> invalidRequest(Exception exception) {
        return ResponseEntity.badRequest().body(ApiErrorResponse.generic(
            "INVALID_REQUEST", "Revise los datos enviados."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> unexpected(Exception exception) {
        UUID correlationId = UUID.randomUUID();
        LOGGER.error("Fallo inesperado de solicitud. correlationId={}", correlationId, exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new ApiErrorResponse("INTERNAL_ERROR", "No fue posible completar la solicitud.", correlationId));
    }
}
