package test.java.edu.ucam;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import main.java.edu.ucam.enums.TipoMovimiento;
import main.java.edu.ucam.exceptions.NegativeBalanceException;
import main.java.edu.ucam.models.*;

class CuentaBancariaTest {

    private CuentaBancaria cuenta;
    
    @BeforeAll
    static void inicializarGlobal() {
        System.out.println(">>> Iniciando pruebas de Cuenta...");
    }

    @AfterAll
    static void liberarGlobal() {
        System.out.println(">>> Finalizadas todas las pruebas de Cuenta.");
    }


    @BeforeEach
    void setUp() throws Exception {
        cuenta = new CuentaBancaria("Luis", 100.0);
    }

    @Test
    void crearCuentaConSaldoValido() throws Exception {
        CuentaBancaria c = new CuentaBancaria("Ana", 50.0);
        assertEquals(50.0, c.getSaldo());
        assertNotNull(c.getNumeroCuenta());
    }

    @Test
    void crearCuentaConSaldoNegativo_lanzaExcepcion() {
        assertThrows(NegativeBalanceException.class, () -> new CuentaBancaria("Luis", -10.0));
    }

    @Test
    void depositarMontoValido_incrementaSaldo() {
        cuenta.depositar(50.0);
        assertEquals(150.0, cuenta.getSaldo());
    }

    @Test
    void depositarMontoNoValido_noCambiaSaldo() {
        cuenta.depositar(0.0);
        assertEquals(100.0, cuenta.getSaldo());
    }

    @Test
    void retirarMontoValido_decrementaSaldo() {
        assertTrue(cuenta.retirar(40.0));
        assertEquals(50.0, cuenta.getSaldo());
    }

    @Test
    void retirarMontoMayorQueSaldo_devuelveFalse() {
        assertFalse(cuenta.retirar(200.0));
        assertEquals(100.0, cuenta.getSaldo());
    }

    @Test
    void registrarMovimiento_seAñadeAlHistorial() {
        Movimiento mov = new Movimiento(TipoMovimiento.DEPOSITO, 20.0);
        cuenta.registrarMovimiento(mov);
        assertEquals(1, cuenta.getHistorial().size());
    }

    @Test
    void cambiarTitular_funcionaCorrectamente() {
        cuenta.setTitular("Carlos");
        assertEquals("Carlos", cuenta.getTitular());
    }
}

