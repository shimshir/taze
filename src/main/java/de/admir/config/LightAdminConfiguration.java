package de.admir.config;

import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LightAdminConfiguration {
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            LightAdmin.configure(servletContext)
                    .basePackage("de.admir.administration")
                    .baseUrl("/admin")
                    .security(false)
                    .backToSiteUrl("/");

            new LightAdminWebApplicationInitializer().onStartup(servletContext);
        };
    }
}
