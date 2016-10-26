package de.admir.config;

import org.apache.catalina.Container;
import org.apache.catalina.Wrapper;
import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
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

    @Bean
    public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                if (container instanceof TomcatEmbeddedServletContainerFactory) {
                    customizeTomcat((TomcatEmbeddedServletContainerFactory)container);
                }
            }

            private void customizeTomcat(TomcatEmbeddedServletContainerFactory tomcatFactory) {
                tomcatFactory.addContextCustomizers((TomcatContextCustomizer) context -> {
                    Container jsp = context.findChild("jsp");
                    if (jsp instanceof Wrapper) {
                        ((Wrapper)jsp).addInitParameter("development", "false");
                    }
                });
            }
        };
    }
}
