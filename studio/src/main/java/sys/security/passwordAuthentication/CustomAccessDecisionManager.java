//package sys.security;
//
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Iterator;
//
///**
// * 定制访问控制
// */
//
//@Component
//public class CustomAccessDecisionManager implements AuthenticationManager {
////    @Override
////    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes/*所需权限*/) throws AccessDeniedException, InsufficientAuthenticationException {
////        System.out.println("AccessDecisionManager");
////
////        if (authentication.isAuthenticated()) {
////            //判断 当前url 是否不需要 权限
////            if (configAttributes == null || configAttributes.size() < 1) {
////                return;
////            }
////            Iterator<ConfigAttribute> iterator = configAttributes.iterator();
////            while (iterator.hasNext()) {
////                ConfigAttribute ca = iterator.next();
////                String needRole = ca.getAttribute();
////
////                //ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
////                for (GrantedAuthority ga : authentication.getAuthorities()) {
////                    if (needRole.trim().equals(ga.getAuthority().trim())) {
////                        return;
////                    }
////                }
////            }
////        }
////
////        throw new AccessDeniedException("权限不足");
////    }
//
////    @Override
////    public boolean supports(ConfigAttribute configAttribute) {
////        return true;
////    }
////
////    @Override
////    public boolean supports(Class<?> aClass) {
////        return FilterInvocation.class.isAssignableFrom(aClass);
////    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println("AuthenticationManager");
//        return null;
//    }
//}
