package models;

import java.time.LocalDate;

public class Reserva {

	private Integer id;
	private LocalDate fecha_entrada;
	private LocalDate fecha_salida;
	private String valor;
	private String forma_pago;
	
	public Reserva() {
	}
	
	public Reserva(LocalDate fecha_entrada,LocalDate fecha_salida,String valor, String forma_pago) {
		super();
		this.fecha_entrada=fecha_entrada;
		this.fecha_salida=fecha_salida;
		this.valor=valor;
		this.forma_pago=forma_pago;
	}
	
	public Reserva(Integer id,LocalDate fecha_entrada,LocalDate fecha_salida,String valor, String forma_pago) {
		super();
		this.id=id;
		this.fecha_entrada=fecha_entrada;
		this.fecha_salida=fecha_salida;
		this.valor=valor;
		this.forma_pago=forma_pago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(LocalDate fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public LocalDate getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(LocalDate fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getForma_pago() {
		return forma_pago;
	}

	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	
	
}
