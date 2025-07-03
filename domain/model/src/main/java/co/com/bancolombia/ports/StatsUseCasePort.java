package co.com.bancolombia.ports;

import java.security.NoSuchAlgorithmException;

import co.com.bancolombia.model.StatsRequest;
import reactor.core.publisher.Mono;

public interface StatsUseCasePort {
	
	Mono<Boolean> validateAndSaveStat(StatsRequest request);

}
