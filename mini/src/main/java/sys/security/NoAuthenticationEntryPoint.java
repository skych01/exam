package sys.security;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import sys.entity.JSONResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiaoq on 2017-06-26.
 */
public class NoAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        //返回json形式的错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String message = e.getMessage();
        if (e instanceof InvalidJWTTokenException) {
            message = SecurityConsts.ERROR_TOKEN_INVALID;
        } else if (e instanceof InsufficientAuthenticationException) {
            message = SecurityConsts.ERROR_NO_AUTH;
        }
        JSONResult result = JSONResult.fail(message);

        response.getWriter().println(result.toString());
        response.getWriter().flush();
    }
}
