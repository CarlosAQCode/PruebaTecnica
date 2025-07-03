package co.com.bancolombia.usecase.stats;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import co.com.bancolombia.model.StatsRequest;
import co.com.bancolombia.ports.StatsUseCasePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StatsUseCase implements StatsUseCasePort {
	@Override
	public Mono<Boolean> validateAndSaveStat(StatsRequest request) {
		var chain = new StringBuffer();
		chain.append(request.getTotalContactoClientes());
		chain.append(",");
		chain.append(request.getMotivoReclamo());
		chain.append(",");
		chain.append(request.getMotivoGarantia());
		chain.append(",");
		chain.append(request.getMotivoDuda());
		chain.append(",");
		chain.append(request.getMotivoCompra());
		chain.append(",");
		chain.append(request.getMotivoFelicitaciones());
		chain.append(",");
		chain.append(request.getMotivoCambio());

		var schain = chain.toString();
		try {
			var md5 = generateMD5(schain);
			if (md5.equals(request.getHash())) {
				// TODO: enviar a dynamo y a la cola
				return Mono.just(Boolean.TRUE);
			} else {
				return Mono.just(Boolean.FALSE);
			}
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
