package com.puuaru.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Description: 自定义全局filter
 * @Author: puuaru
 * @Date: 2022/9/30
 */
@Component
@Slf4j
public class LogGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("******** 进入全局过滤器：" + LogGatewayFilter.class.getName() + ", " + new Date() + "********");
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if (name == null) { // 用户名检测
            log.info("******** 用户名为null ********");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            // 通过处理请求响应直接返回
            return exchange.getResponse().setComplete();
        }
        // 调用过滤器链继续处理请求
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() { // 加载filter的顺序，order越小优先级越高
        return 0;
    }
}
