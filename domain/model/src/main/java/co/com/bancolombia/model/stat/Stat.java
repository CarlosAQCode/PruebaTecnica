package co.com.bancolombia.model.stat;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stat {

	private String timestamp;
	private int totalContactoClientes;
	private int motivoReclamo;
	private int motivoGarantia;
	private int motivoDuda;
	private int motivoCompra;
	private int motivoFelicitaciones;
	private int motivoCambio;
	private String hash;

}
