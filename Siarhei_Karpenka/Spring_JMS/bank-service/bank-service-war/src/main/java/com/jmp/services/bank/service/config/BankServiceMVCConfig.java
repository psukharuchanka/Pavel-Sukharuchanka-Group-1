package com.jmp.services.bank.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.jmp.services.bank.service.persistance.eao.AccountManagerBean;
import com.jmp.services.bank.service.persistance.eao.AccountManagerLocal;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.jmp.services.bank.service.controller" })
public class BankServiceMVCConfig extends WebMvcConfigurerAdapter {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/lib/**").addResourceLocations("/lib/");
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
 
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
 
}
