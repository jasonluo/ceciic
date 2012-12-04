package com.vangent.psbpo.sys.web.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.AbstractProcessingFilter;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilterEntryPoint;

/**

      <beans:bean id="exceptionTranslationFilter" class="org.springframework.security.ui.ExceptionTranslationFilter">
            <beans:property name="authenticationEntryPoint">
<!--        <beans:bean class="org.springframework.security.ui.webapp.AuthenticationProcessingFilterEntryPoint">-->
                  <beans:bean class="com.vangent.psbpo.sys.web.security.AuthenticationSafeEntryPoint">
                        <!-- URL redirected to when directly access secured pages without login -->
                        <beans:property name="loginFormUrl" value="/spring/login"/>
                        <beans:property name="forceHttps" value="false"/>
                  </beans:bean>
            </beans:property>
            <beans:property name="accessDeniedHandler">
                  <beans:bean class="org.springframework.security.ui.AccessDeniedHandlerImpl">
                        <!-- URL redirected to when accessing secured pages without sufficient authority -->
                        <beans:property name="errorPage" value="/spring/login?accessDenied=true"/>
                  </beans:bean>
            </beans:property>
      </beans:bean>

*/
/**
 * Extends Spring security to remove requests made after session timeout.
 *
 */
public class AuthenticationSafeEntryPoint extends AuthenticationProcessingFilterEntryPoint
{
    public void commence(ServletRequest requestArg, ServletResponse responseArg, AuthenticationException authException) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) requestArg;
        HttpServletResponse response = (HttpServletResponse) responseArg;
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            // PLEASE NOTE:
            // Remove the Ajax request made after session timeout, otherwise user ends up at
            // that Ajax request after login
            Object targetUrl = session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY);
            if (targetUrl != null)
            {
                session.removeAttribute(AbstractProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY);
            }
        }
        super.commence(request, response, authException);
    }
}
