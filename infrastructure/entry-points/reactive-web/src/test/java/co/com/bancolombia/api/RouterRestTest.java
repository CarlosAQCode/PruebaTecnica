package co.com.bancolombia.api;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {RouterRest.class, Statshandler.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;    
    
    @Mock
    private co.com.bancolombia.usecase.stats.StatsUseCase StatsUseCase;

    //@Test
    void testListenGETUseCase() {  	
    	

    }
}
