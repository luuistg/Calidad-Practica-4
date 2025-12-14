package main.java.edu.ucam.models;

import main.java.edu.ucam.enums.TipoMovimiento;

public class Movimiento {
	
	private TipoMovimiento movimiento;
	private double cantidad;
	
	public Movimiento(TipoMovimiento movimiento, double cantidad) {
		super();
		this.movimiento = movimiento;
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
	    switch (movimiento) {
	        case RETIRADA:
	            return "Retirada de " + cantidad + " €";
	        case DEPOSITO:
	            return "Depósito de " + cantidad + " €";
	        case REMITENTE:
	            return "Transferencia enviada de " + cantidad + " €";
	        case DESTINATARIO:
	            return "Transferencia recibida de " + cantidad + " €";
	        default:
	            return movimiento + " - " + cantidad;
	    }
	}
	
	
}
