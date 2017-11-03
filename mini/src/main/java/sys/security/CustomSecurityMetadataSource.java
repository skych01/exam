package sys.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import sys.entity.WechatResource;
import sys.entity.WechatRole;
import sys.repository.WechatResourceRepository;
import sys.repository.WechatRoleRepository;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 从数据库中加载资源权限
 */
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private WechatRoleRepository roleRepository;

    @Autowired
    private WechatResourceRepository resourceRepository;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次
     * 为了通过管理后台动态修改用户角色、权限，可以考虑修改为cache
     */
    @PostConstruct
    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

        List<WechatRole> roles = roleRepository.findAll();
        for (WechatRole role : roles) {
            ConfigAttribute roleConfigAttribute = new SecurityConfig(role.getRoleName());
            List<String> urlList = new ArrayList<String>();
            List<WechatResource> resources = role.getResources();
            if(resources!=null && resources.size()>0) {
                for(WechatResource resource :resources){
                    urlList.add(resource.getResourceUrl());
                }
            }
            for (String url : urlList) {
                //判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                if (resourceMap.containsKey(url)) {
                    Collection<ConfigAttribute> atts = resourceMap.get(url);
                    atts.add(roleConfigAttribute);
                    resourceMap.put(url, atts);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                    atts.add(roleConfigAttribute);
                    resourceMap.put(url, atts);
                }
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
