package sys.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;
import sys.service.MiniProgramService;

/**
 * 定制授权过滤器，读取请求头部信息
 * 头部信息将被转换为Spring Authentication对象，名称为PreAuthenticatedAuthenticationToken
 */
@Component
//@Slf4j  简单日志门面
public class JWTHeaderAuthenticationFilter extends RequestHeaderAuthenticationFilter {
    private final Logger logger = LoggerFactory.getLogger(JWTHeaderAuthenticationFilter.class);

    @Value("${jwt.header}")
    private String JWT_HEADER;  //Authorization

    @Autowired
    private MiniProgramService miniProgramSettings;

    public JWTHeaderAuthenticationFilter() {
        // Don't throw exceptions if the header is missing
        this.setExceptionIfHeaderMissing(false);

        logger.info("JWT_HEADER : " + JWT_HEADER);

        // This is the request header it will look for
        this.setPrincipalRequestHeader("Authorization");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

}
