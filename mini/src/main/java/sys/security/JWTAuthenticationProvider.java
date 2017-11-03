package sys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sys.service.WecharService;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {
    @Value("${jwt.tokenHead}")
    private String TOKEN_HEAD;  //Bearer

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private WecharService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication result = authentication;
        // Only process the PreAuthenticatedAuthenticationToken
        // 验证JWTRequestHeaderAuthenticationFilter处理的数据
        if (authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                && authentication.getPrincipal() != null) {
            String tokenHeader = (String) authentication.getPrincipal();
            // The part after "Bearer "
            String authToken = tokenHeader.substring(TOKEN_HEAD.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            UserDetails principal = null;
            if (!StringUtils.isEmpty(username)) {
                principal = userDetailsService.loadUserByUsername(username);
                if (principal != null) {
                    if (jwtTokenUtil.validateToken(authToken, principal)) {
                        JWTAuthenticationToken jwtAuthenticationToken = new JWTAuthenticationToken(principal, tokenHeader);
                        jwtAuthenticationToken.setAuthenticated(true);
                        result = jwtAuthenticationToken;
                    } else {
                        throw new InvalidJWTTokenException(SecurityConsts.ERROR_TOKEN_INVALID);
                    }
                }
            }
        }

        return result;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                || aClass.isAssignableFrom(JWTAuthenticationToken.class);
    }
}
