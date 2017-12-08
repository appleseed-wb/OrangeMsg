package com.orange.msg.conf;

import com.alibaba.fastjson.JSON;
import com.orange.msg.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 鉴权过滤器
 */
public class OAuthFilter implements Filter {

    @Autowired
    private OAuthService oAuthService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest) request;
        String accessToken = hsr.getHeader("access_token");
        if(accessToken==null||"".equals(accessToken)){
            accessToken= hsr.getParameter("access_token");
            //检测Access Token是否合法
            if (accessToken==null || "".equals(accessToken) || !oAuthService.checkAccessToken(accessToken)) {
                writeMsg(response, "无权限访问资源!", HttpServletResponse.SC_UNAUTHORIZED);
            }else {
                chain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private void writeMsg(ServletResponse response, String msgStr, Object code) {
        OutputStream out = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            out = response.getOutputStream();
            Map<String, Object> msg = new HashMap<String, Object>();
            msg.put("status", code);
            msg.put("msg", msgStr);
            out.write(JSON.toJSONString(msg).getBytes("UTF-8"));
            out.flush();
        } catch (Exception e) {
        } finally {
            try {
                out.close();
            } catch (IOException e) {
            }
        }
    }
}
