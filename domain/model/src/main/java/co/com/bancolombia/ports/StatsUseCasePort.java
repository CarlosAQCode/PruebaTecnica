package co.com.bancolombia.ports;

import co.com.bancolombia.model.stat.Stat;
import reactor.core.publisher.Mono;

public interface StatsUseCasePort {
	
	Mono<Boolean> validateAndSaveStat(Stat request);

}
