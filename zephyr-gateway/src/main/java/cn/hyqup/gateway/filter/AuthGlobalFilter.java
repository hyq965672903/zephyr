package cn.hyqup.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hyqup.common.core.constant.SecurityConstants;
import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 无token放行
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        if (StrUtil.isBlank(token) || !token.startsWith(SecurityConstants.AUTHORIZATION_PREFIX)) {
            return chain.filter(exchange);
        }
        // 解析JWT获取jti
        token = token.replace(SecurityConstants.AUTHORIZATION_PREFIX, Strings.EMPTY);
        JWSObject jwsObject = JWSObject.parse(token);
        String payload = jwsObject.getPayload().toString();
        request = exchange.getRequest().mutate()
                .header(SecurityConstants.JWT_PAYLOAD_KEY, payload)
                .build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
