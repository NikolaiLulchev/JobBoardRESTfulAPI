package org.softuni.JobBoardRESTfulAPI.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class MaintenanceInterceptor implements HandlerInterceptor {

//  @Override
//  public boolean preHandle(HttpServletRequest request,
//                           HttpServletResponse response,
//                           Object handler) throws Exception {
//
//    String requestURI = request.getRequestURI();
//    if (!requestURI.equals("/maintenance")) {
//      LocalTime now = LocalTime.now();
//      if (now.getHour() >= 23) {
//        response.sendRedirect("/maintenance");
//        return false;
//      }
//    }
//
//    return HandlerInterceptor.super.preHandle(request, response, handler);
//  }
}
