package main.java.edu.ucam.UI;

import java.util.Scanner;

import main.java.edu.ucam.models.Banco;
import main.java.edu.ucam.models.CuentaBancaria;

public class ConsoleInterface {
	private Scanner sc = new Scanner(System.in);
    private Banco banco = Banco.getInstance();

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1 -> crearCuenta();
                case 2 -> depositar();
                case 3 -> retirar();
                case 4 -> transferir();
                case 5 -> mostrarCuentas();
                case 6 -> mostrarSaldoTotal();
                case 7 -> mostrarHistorial();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida");
            }
        } while(opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENÚ BANCO ---");
        System.out.println("1. Crear cuenta");
        System.out.println("2. Depositar");
        System.out.println("3. Retirar");
        System.out.println("4. Transferir");
        System.out.println("5. Mostrar cuentas");
        System.out.println("6. Saldo total del banco");
        System.out.println("7. Mostrar historial de una cuenta");
        System.out.println("0. Salir");
        System.out.print("Seleccione opción: ");
    }

    private void crearCuenta() {
        try {
            System.out.print("Titular: ");
            String titular = sc.nextLine();
            System.out.print("Saldo inicial: ");
            double saldo = sc.nextDouble();
            sc.nextLine();

            CuentaBancaria cb = new CuentaBancaria(titular, saldo);
            banco.agregarCuenta(cb);
            System.out.println("Cuenta creada: " + cb.getNumeroCuenta());
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void depositar() {
        System.out.print("Número de cuenta: ");
        String num = sc.nextLine();
        CuentaBancaria cb = banco.obtenerCuenta(num);
        if(cb != null) {
            System.out.print("Monto a depositar: ");
            double monto = sc.nextDouble();
            sc.nextLine();
            cb.depositar(monto);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void retirar() {
        System.out.print("Número de cuenta: ");
        String num = sc.nextLine();
        CuentaBancaria cb = banco.obtenerCuenta(num);
        if(cb != null) {
            System.out.print("Monto a retirar: ");
            double monto = sc.nextDouble();
            sc.nextLine();
            cb.retirar(monto);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void transferir() {
        System.out.print("Cuenta origen: ");
        String origen = sc.nextLine();
        System.out.print("Cuenta destino: ");
        String destino = sc.nextLine();
        System.out.print("Monto: ");
        double monto = sc.nextDouble();
        sc.nextLine();

        CuentaBancaria cb1 = banco.obtenerCuenta(origen);
        CuentaBancaria cb2 = banco.obtenerCuenta(destino);

        if(cb1 != null && cb2 != null) {
            banco.transferencia(cb1, cb2, monto);
        } else {
            System.out.println("Cuenta origen o destino no encontrada.");
        }
    }

    private void mostrarCuentas() {
        banco.mostrarCuentas();
    }

    private void mostrarSaldoTotal() {
        System.out.println("Saldo total del banco: " + banco.saldoTotalBanco());
    }
    
    private void mostrarHistorial() {
        System.out.print("Número de cuenta: ");
        String num = sc.nextLine();
        CuentaBancaria cb = banco.obtenerCuenta(num);
        if(cb != null) {
            cb.mostrarHistorial();
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

}
