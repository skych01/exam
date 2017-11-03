package sys.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 定制访问控制
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (authentication.isAuthenticated()) {
            if (configAttributes == null || configAttributes.size() < 1) {
                return;
            }
            Iterator<ConfigAttribute> iterator = configAttributes.iterator();
            while (iterator.hasNext()) {
                ConfigAttribute ca = iterator.next();
                String needRole = ca.getAttribute();

                //ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
                for (GrantedAuthority ga : authentication.getAuthorities()) {
                    if (needRole.trim().equals(ga.getAuthority().trim())) {
                        return;
                    }
                }
            }
        }

        throw new AccessDeniedException(SecurityConsts.ERROR_NO_AUTH);
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
