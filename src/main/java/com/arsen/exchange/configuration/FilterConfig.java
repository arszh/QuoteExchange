package com.arsen.exchange.configuration;

import com.arsen.exchange.filter.FilterByToken;
import com.arsen.exchange.filter.RequestAndResponseLoggingFilter;
import com.arsen.exchange.service.QuoteLogServiceImp;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private QuoteLogServiceImp quoteLogService;

    public FilterConfig(QuoteLogServiceImp quoteLogService) {
        this.quoteLogService = quoteLogService;
    }


    @Bean
    public FilterRegistrationBean requestAndResponseLoggingFilterForQuote() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        RequestAndResponseLoggingFilter requestAndResponseLoggingFilter = new RequestAndResponseLoggingFilter("quote", quoteLogService);
        filterRegistrationBean.setFilter(requestAndResponseLoggingFilter);
        return filterRegistrationBean;
    }


}