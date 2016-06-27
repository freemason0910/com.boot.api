package com.yixin.js.filter;

import com.yixin.js.security.UsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
public class MsgFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter{

    public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        if (password==null){
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        String captcha = getCaptcha(request);
        return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
    }

}