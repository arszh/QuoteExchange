package com.arsen.exchange.filter;


import com.arsen.exchange.model.Log;
import com.arsen.exchange.service.LogService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class RequestAndResponseLoggingFilter extends OncePerRequestFilter {

    private LogService logService;
    private String route;

    public RequestAndResponseLoggingFilter(String route, LogService logService) {
        this.logService = logService;
        this.route = route;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (isAsyncDispatch(request) || !request.getRequestURI().contains(route)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
        }
    }


    private void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
            Long idPathVariable = getIdPathVariable(request);
            RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());
            int status = response.getStatus();
            String message = getResponsePayload(response);
            Log log = logService.createLog(requestMethod, idPathVariable, status, message);
            logService.save(log);
        } finally {
            response.copyBodyToResponse();
        }
    }

    private Long getIdPathVariable(ContentCachingRequestWrapper request) {
        Map<String, String> pathVariables = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String idString = pathVariables.get("id");
        Long id = null;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
        }
        return id;
    }


    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }


    private static String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();

            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                }
            }
        }
        return "[unknown]";
    }

}


