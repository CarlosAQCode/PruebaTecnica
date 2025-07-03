package co.com.bancolombia.dynamodb;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor
public class MotivoContactoEntity {

	private String timestamp;
	private int totalContactoClientes;
	private int motivoReclamo;
	private int motivoGarantia;
	private int motivoDuda;
	private int motivoCompra;
	private int motivoFelicitaciones;
	private int motivoCambio;
	private String hash;

	@DynamoDbPartitionKey
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getTotalContactoClientes() {
		return totalContactoClientes;
	}

	public void setTotalContactoClientes(int totalContactoClientes) {
		this.totalContactoClientes = totalContactoClientes;
	}

	public int getMotivoReclamo() {
		return motivoReclamo;
	}

	public void setMotivoReclamo(int motivoReclamo) {
		this.motivoReclamo = motivoReclamo;
	}

	public int getMotivoGarantia() {
		return motivoGarantia;
	}

	public void setMotivoGarantia(int motivoGarantia) {
		this.motivoGarantia = motivoGarantia;
	}

	public int getMotivoDuda() {
		return motivoDuda;
	}

	public void setMotivoDuda(int motivoDuda) {
		this.motivoDuda = motivoDuda;
	}

	public int getMotivoCompra() {
		return motivoCompra;
	}

	public void setMotivoCompra(int motivoCompra) {
		this.motivoCompra = motivoCompra;
	}

	public int getMotivoFelicitaciones() {
		return motivoFelicitaciones;
	}

	public void setMotivoFelicitaciones(int motivoFelicitaciones) {
		this.motivoFelicitaciones = motivoFelicitaciones;
	}

	public int getMotivoCambio() {
		return motivoCambio;
	}

	public void setMotivoCambio(int motivoCambio) {
		this.motivoCambio = motivoCambio;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
