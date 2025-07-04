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
import co.com.bancolombia.model.StatsRequest;
import co.com.bancolombia.ports.StatsUseCasePort;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {RouterRest.class, Statshandler.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;    
    
    @Mock
    private StatsUseCasePort StatsUseCase;

    //@Test
    void testListenGETUseCase() {    	
    	
    	when(StatsUseCase.validateAndSaveStat(any())).thenReturn(Mono.just(Boolean.TRUE));
    	
    	var request = new StatsRequest(250, 25, 10, 100, 100, 7, 8, "5484062a4be1ce5645eb414663e14f59");    	
        webTestClient.post()
                .uri("/api/Stats/Savestats")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEmpty();
                        }
                );
    }
}
