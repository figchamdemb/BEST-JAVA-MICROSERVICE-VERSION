package com.egov.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SecurityUtils {

    public static String getCurrentUserIP() {
        HttpServletRequest request = getCurrentRequest();
        if (request != null) {
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
        return "0.0.0.0";
    }

    public static String getCurrentUserAgent() {
        HttpServletRequest request = getCurrentRequest();
        if (request != null) {
            return request.getHeader("User-Agent");
        }
        return "unknown";
    }

    public static String getCurrentSessionId() {
        HttpServletRequest request = getCurrentRequest();
        if (request != null) {
            return request.getSession().getId();
        }
        return "no-session";
    }

    private static HttpServletRequest getCurrentRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (IllegalStateException e) {
            return null;
        }
    }
}
