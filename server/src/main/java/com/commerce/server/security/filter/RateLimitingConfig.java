package com.commerce.server.security.filter;

import com.commerce.server.security.config.RateLimitConfig;
import io.github.bucket4j.Bucket;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RateLimitingConfig implements Filter {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ip = request.getRemoteAddr();
        Bucket bucket = buckets.computeIfAbsent(ip,k-> RateLimitConfig.createBucket());

        if (bucket.tryConsume(1)){
            chain.doFilter(request,response);
        } else {
            ((HttpServletResponse) response).setStatus(429);
            response.getWriter().write("Too many requests - Rate limit exceeded");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
