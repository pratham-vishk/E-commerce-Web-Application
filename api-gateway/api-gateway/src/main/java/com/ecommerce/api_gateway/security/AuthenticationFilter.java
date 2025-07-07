package com.ecommerce.api_gateway.security;
import com.ecommerce.api_gateway.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RestTemplate template;



    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest mobileNumber=null;
            if (validator.isSecured.test(exchange.getRequest())) {

                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new ResourceNotFoundException("missing authorization header");
                }

                String jwt = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (jwt != null && jwt.startsWith("Bearer ")) {
                    jwt = jwt.substring(7);
                }
                try {
//                    //REST call to AUTH service

//                    TokenDto tokenDto = template.postForObject("http://user-service/api/auth/findByToken/{token}", null, TokenDto.class, jwt);
//                    if (tokenDto!=null && tokenDto.isExpired() && tokenDto.isRevoked()) {
//                            jwtService.validateToken(jwt);
//                        }
                    jwtService.validateToken(jwt);
                    mobileNumber = exchange.getRequest()
                            .mutate()
                            .header("mobileNumber", jwtService.extractUsername(jwt))
                            .build();

                } catch (Exception e) {
                    throw new ResourceNotFoundException("un authorized access to application");
                }
            }
            return chain.filter(exchange.mutate().request(mobileNumber).build());
        });
    }

    public static class Config {

    }
}

