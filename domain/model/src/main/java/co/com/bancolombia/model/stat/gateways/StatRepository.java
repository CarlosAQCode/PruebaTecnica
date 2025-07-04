package co.com.bancolombia.model.stat.gateways;

import co.com.bancolombia.model.stat.Stat;
import reactor.core.publisher.Mono;

public interface StatRepository {

	Mono<Object> saveEntity(Stat stat);
}
