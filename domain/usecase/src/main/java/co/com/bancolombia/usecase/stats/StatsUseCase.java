package co.com.bancolombia.usecase.stats;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import co.com.bancolombia.model.stat.Stat;
import co.com.bancolombia.model.stat.gateways.StatRepository;
import co.com.bancolombia.model.events.gateways.EventsGateway;

@RequiredArgsConstructor
public class StatsUseCase {

	private final StatRepository StatRepository;
	private final EventsGateway rabbitEvent;

	public Mono<Boolean> validateAndSaveStat(Stat request) {
		try {
			if (request == null) {
				return Mono.just(Boolean.FALSE);
			}

			var chain = new StringBuffer();
			chain.append(request.getTotalContactoClientes()).append(",").append(request.getMotivoReclamo()).append(",")
					.append(request.getMotivoGarantia()).append(",").append(request.getMotivoDuda()).append(",")
					.append(request.getMotivoCompra()).append(",").append(request.getMotivoFelicitaciones()).append(",")
					.append(request.getMotivoCambio());

			var schain = chain.toString();
			var md5 = generateMD5(schain);
			if (!md5.equals(request.getHash())) {
				return Mono.just(Boolean.FALSE);
			}
			request.setTimestamp(DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
			return StatRepository.saveEntity(request)
					.flatMap(entity -> rabbitEvent.emit(entity).thenReturn(Boolean.TRUE)).onErrorResume(Mono::error);

		} catch (Exception e) {
			return Mono.error(e);
		}
	}

	private static String generateMD5(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();

	}
}
