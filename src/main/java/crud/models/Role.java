package crud.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Mukhammed Asantegin
 */
public enum Role implements GrantedAuthority
 {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
