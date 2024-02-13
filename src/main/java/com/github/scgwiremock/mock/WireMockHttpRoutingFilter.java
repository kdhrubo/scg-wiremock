
package com.github.scgwiremock.mock;

import com.github.tomakehurst.wiremock.direct.DirectCallHttpServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 * @author Dhrubo
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class WireMockHttpRoutingFilter implements GlobalFilter, Ordered {

	private final DirectCallHttpServer directCallHttpServer;

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("Check if mock request.");

		URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);

		log.info("requestUrl -> {}", requestUrl);

		String scheme = requestUrl.getScheme();

		log.info("scheme -> {}", scheme);

		if (isAlreadyRouted(exchange) || !"mock".equals(scheme)) {
			return chain.filter(exchange);
		}
		setAlreadyRouted(exchange);

		ServerHttpRequest request = exchange.getRequest();

		HttpMethod method = request.getMethod();

		//1. need another class with function to map http request to mock request

		//MockRequest mockRequest = mockRequest().url("/slow-response").method(GET);

		//2. invoke server

		//3. Write response to exchange to be commiited by netty response writer


		return Mono.empty();
	}

	private boolean requiresBody(HttpMethod method) {
		return method.equals(HttpMethod.PUT) || method.equals(HttpMethod.POST) || method.equals(HttpMethod.PATCH);
	}

}
