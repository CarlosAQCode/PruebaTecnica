package co.com.bancolombia.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.bancolombia.model.stat.Stat;

import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class Statshandler {

	private final co.com.bancolombia.usecase.stats.StatsUseCase StatsUseCase;

	public Mono<ServerResponse> Savestats(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(Stat.class).flatMap(stat -> StatsUseCase.validateAndSaveStat(stat))
				.flatMap(resp -> {
					if (resp) {
						return ServerResponse.ok().build();
					} else {
						return ServerResponse.badRequest().bodyValue("Md5 no corresponde al ingresado favor validar");
					}
				}).onErrorResume(throwable -> {
					log.error("Error de procesamiento, Error : ", throwable.getMessage());
					return Mono.error(throwable);
				});

	}
}