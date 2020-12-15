package com.lbj.gateway.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

//    @Autowired
//    private LoginFilter loginFilter;

//    @Bean
//    public RouteLocator route(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("shop-auth", p -> p.path("/oauth/**").uri("lb://shop-auth"))
//                .route("shop-manage", p -> p.path("/admin/**").uri("lb://shop-manage"))
//                .route("shop-seller", p -> p.path("/seller/**").and().readBody(String.class, s -> true).uri("lb://shop-seller").filter(loginFilter))
//                .route("shop-user", p -> p.path("/user/app/**").and().readBody(String.class, s -> true).uri("lb://shop-user").filter(loginFilter)) // 此类请求是需要登陆验证的
//                .route("shop-user", p -> p.path("/user/**").and().readBody(String.class, s -> true).uri("lb://shop-user"))
//
//                .build();
//    }

    // 能正常使用
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                // 转发百度
//                .route(r -> r.path("/baidu").uri("http://baidu.com:80/"))
//                // 转发ca
//                .route(r -> r.path("/api/v1/ca/*").uri("lb://lbj-client-ca/"))
//                .build();
//    }
}
