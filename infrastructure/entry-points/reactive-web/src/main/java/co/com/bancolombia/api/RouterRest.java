package co.com.bancolombia.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
	@Bean
	public RouterFunction<ServerResponse> routerFunction(Statshandler Statshandler) {
		return route(POST("/api/Stats/Savestats"), Statshandler::Savestats);
	}
}
