package org.softuni.JobBoardRESTfulAPI.config;

import org.softuni.JobBoardRESTfulAPI.interceptors.MaintenanceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final MaintenanceInterceptor maintenanceInterceptor;

    public WebConfig(MaintenanceInterceptor maintenanceInterceptor) {

        this.maintenanceInterceptor = maintenanceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(maintenanceInterceptor);
    }
}
