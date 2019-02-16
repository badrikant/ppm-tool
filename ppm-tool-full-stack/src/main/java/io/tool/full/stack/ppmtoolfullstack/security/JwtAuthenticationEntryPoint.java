package io.tool.full.stack.ppmtoolfullstack.security;

import com.google.gson.Gson;
import io.tool.full.stack.ppmtoolfullstack.exceptions.InvalidLoginResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author badrikant.soni on 16/02/19
 * JwtAuthenticationEntryPoint - responsible to show the invalid login response json when users attempt wrong username || password
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        InvalidLoginResponse invalidLoginResponse = new InvalidLoginResponse();
        String jsonLoginResponse = new Gson().toJson(invalidLoginResponse);

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().print(jsonLoginResponse);

    }
}
