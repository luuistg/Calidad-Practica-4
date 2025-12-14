package main.java.edu.ucam.models;

import java.text.DecimalFormat;
import java.util.ArrayList;

import main.java.edu.ucam.enums.TipoMovimiento;
import main.java.edu.ucam.exceptions.NegativeBalanceException;

public class CuentaBancaria {
	private String numeroCuenta;
	private String titular;
	private double saldo;
	private ArrayList<Movimiento> historial;
	
	private static int contador = 0;
	DecimalFormat dF = new DecimalFormat ("000");
	
	

	public CuentaBancaria(String titular, double saldo) throws Exception {
		super();
		contador ++;
		this.numeroCuenta = "CTA-" + dF.format(contador) ;
		this.titular = titular;
		this.saldo = saldo;
		this.historial = new ArrayList<>();
		if(saldo < 0) {
			throw new NegativeBalanceException("Saldo introducido negativo");
		}
	}
	
	public CuentaBancaria(String titular) throws Exception{
		this(titular, 0.0);
	}
	
	//GETTER SETTER

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public ArrayList<Movimiento> getHistorial() {
		return historial;
	}

	public void setHistorial(ArrayList<Movimiento> historial) {
		this.historial = historial;
	}

	//Logica Negocio
	public void depositar(double monto) {
		if (monto <= 0)
			return;
		
		this.saldo += monto;
		
		this.registrarMovimiento(new Movimiento(TipoMovimiento.DEPOSITO, monto));
		
		System.out.println("Saldo añadido a la cuenta con N Cuenta: " + this.numeroCuenta + ", con titular: " +
		this.titular + ", con un monto de " +
				monto + ". Saldo Total: " + this.saldo);
	}
	
	public boolean retirar(double monto){
		if (monto <= 0)
			return false;
		
		double saldoTemp = this.saldo - monto;
		
		if (saldoTemp >= 0) {
			this.saldo = saldoTemp;
			
			this.registrarMovimiento(new Movimiento(TipoMovimiento.RETIRADA, monto));
			
			System.out.println("Retirada correcta, saldo Total: " + this.saldo);
			return true;
		}
		
		System.out.println("Saldo insufuciente");
		return false;
			
	}
	
	public void registrarMovimiento(Movimiento mov) {
	    historial.add(mov);
	}

	public void mostrarHistorial() {
	    System.out.println("Historial de la cuenta " + numeroCuenta + ":");
	    historial.forEach(System.out::println);
	}

	@Override
	public String toString() {
		return "titular=" + titular + ", saldo=" + saldo + "]";
	}

	
	
	

}
