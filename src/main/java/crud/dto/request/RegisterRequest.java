package crud.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Mukhammed Asantegin
 */
public record RegisterRequest(
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String phoneNumber
) {
}
