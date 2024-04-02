package crud.dto.response;

import crud.models.Role;
import lombok.Builder;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record LoginResponse(
        String token,
        Long id,
        Role role,
        String email
) {
}
