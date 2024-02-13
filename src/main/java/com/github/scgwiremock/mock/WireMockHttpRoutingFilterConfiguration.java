package com.github.scgwiremock.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.direct.DirectCallHttpServer;
import com.github.tomakehurst.wiremock.direct.DirectCallHttpServerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Configuration
@RequiredArgsConstructor
public class WireMockHttpRoutingFilterConfiguration {


    @Bean
    public DirectCallHttpServer directCallHttpServer() {
        //TODO Need to wire an extension to fetch and cache stubs
        //https://wiremock.org/docs/extensibility/adding-mappings-loader/
        DirectCallHttpServerFactory factory = new DirectCallHttpServerFactory();
        WireMockServer wm = new WireMockServer(wireMockConfig()
                .extensions(new WireMockMappingsLoaderExtension())
                .httpServerFactory(factory));
        wm.start(); // no-op, not required

        return factory.getHttpServer();
    }


}
