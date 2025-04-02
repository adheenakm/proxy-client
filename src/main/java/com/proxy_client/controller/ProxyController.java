package com.proxy_client.controller;

import com.proxy_client.service.ProxyClient;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProxyController {
    private final ProxyClient proxyClient;

    public ProxyController(ProxyClient proxyClient) {
        this.proxyClient = proxyClient;
    }

    @GetMapping("/**")
    public ResponseEntity<String> proxyRequest(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        String response = proxyClient.forwardRequest(requestUrl);
        return ResponseEntity.ok(response);
    }
}
