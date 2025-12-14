package test.java.edu.ucam;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import main.java.edu.ucam.enums.TipoMovimiento;
import main.java.edu.ucam.models.*;

class MovimientoTest {
	
	@BeforeAll
    static void inicializarGlobal() {
        System.out.println(">>> Iniciando pruebas de Movimiento...");
    }

    @AfterAll
    static void liberarGlobal() {
        System.out.println(">>> Finalizadas todas las pruebas de Movimiento.");
    }

    @Test
    void toString_depositoCorrecto() {
        Movimiento mov = new Movimiento(TipoMovimiento.DEPOSITO, 50.0);
        assertTrue(mov.toString().contains("Depósito"));
    }

    @Test
    void toString_retiradaCorrecta() {
        Movimiento mov = new Movimiento(TipoMovimiento.RETIRADA, 20.0);
        assertTrue(mov.toString().contains("Retirada"));
    }
}

