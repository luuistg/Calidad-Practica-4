package main.java.edu.ucam.models;

import java.util.Hashtable;

import main.java.edu.ucam.enums.TipoMovimiento;

public class Banco {
	
	private static Banco instancia;

    private Hashtable<String, CuentaBancaria> banco;

    private Banco() {
        banco = new Hashtable<>();
    }

    public static Banco getInstance() {
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }
	
	public static void transferencia(CuentaBancaria cb1, CuentaBancaria cb2, double monto) {
		
		if(cb1.retirar(monto)) {
			cb2.depositar(monto);
			
			cb1.registrarMovimiento(new Movimiento(TipoMovimiento.REMITENTE, monto));
			cb2.registrarMovimiento(new Movimiento(TipoMovimiento.DESTINATARIO, monto));
			
			System.out.println("Transferencia completada.");
			return;
		}
		System.out.println("No se pudo retirar por fondos insuficientes");
	}
	
	public double saldoTotalBanco() {
        return banco.values().stream().mapToDouble(CuentaBancaria::getSaldo).sum();
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        banco.put(cuenta.getNumeroCuenta(), cuenta);
    }

    public CuentaBancaria obtenerCuenta(String numeroCuenta) {
        return banco.get(numeroCuenta);
    }

    public void mostrarCuentas() {
        banco.forEach((k, v) -> System.out.println("Número Cuenta: " + k + ", Información: " + v));
    }
    
    public void vaciar() {
        banco.clear();
    }


}
