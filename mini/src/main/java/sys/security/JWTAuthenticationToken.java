package sys.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by xiaoq on 2017-06-26.
 */
public class JWTAuthenticationToken extends AbstractAuthenticationToken {
    private UserDetails principal;
    private String jsonWebToken;

    public JWTAuthenticationToken(UserDetails principal, String jsonWebToken) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public String getJsonWebToken() {
        return jsonWebToken;
    }

    public String getOpenId(){
        return principal.getUsername();
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
