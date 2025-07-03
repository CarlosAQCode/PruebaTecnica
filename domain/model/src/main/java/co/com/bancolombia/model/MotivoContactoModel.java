package co.com.bancolombia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MotivoContactoModel {

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
