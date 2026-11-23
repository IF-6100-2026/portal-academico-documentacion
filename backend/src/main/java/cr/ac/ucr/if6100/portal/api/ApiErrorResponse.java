package cr.ac.ucr.if6100.portal.api;

import java.util.UUID;

public record ApiErrorResponse(String code, String message, UUID correlationId) {
    public static ApiErrorResponse generic(String code, String message) {
        return new ApiErrorResponse(code, message, UUID.randomUUID());
    }

    public static ApiErrorResponse forField(String field) {
        return generic("INVALID_FIELD", "El campo " + field + " es obligatorio o no es válido.");
    }
}
