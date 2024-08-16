package com.example.handlers;

import com.example.utils.Jwt;
import com.example.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component("loginInterceptor")
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token= request.getHeader("Authorization");
        //System.out.println(token);

        try{
            Map<String,Object> claims= Jwt.parseToken(token);
            ThreadLocalUtil.set(claims);
            return true;
        }
        catch (Exception e){
            response.setStatus(401);
            return false;  //不放行
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception{
        ThreadLocalUtil.remove();
    }
}
