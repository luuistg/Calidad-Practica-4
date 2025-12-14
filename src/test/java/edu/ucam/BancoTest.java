package test.java.edu.ucam;

import org.junit.jupiter.api.*;

import main.java.edu.ucam.models.*;

import static org.junit.jupiter.api.Assertions.*;

class BancoTest {

    private Banco banco;
    private CuentaBancaria c1, c2;
    
    @BeforeAll
    static void inicializarGlobal() {
        System.out.println(">>> Iniciando pruebas de Banco...");
    }

    @AfterAll
    static void liberarGlobal() {
        System.out.println(">>> Finalizadas todas las pruebas de Banco.");
    }


    @BeforeEach
    void setUp() throws Exception {
        banco = Banco.getInstance();
        banco.vaciar(); //<- Limpieza entre pruebas por patron singleton
        c1 = new CuentaBancaria("Luis", 100);
        c2 = new CuentaBancaria("Ana", 50);
        banco.agregarCuenta(c1);
        banco.agregarCuenta(c2);
    }

    @Test
    void transferenciaValida_actualizaSaldos() {
        Banco.transferencia(c1, c2, 50);
        assertEquals(50, c1.getSaldo());
        assertEquals(100, c2.getSaldo());
    }

    @Test
    void transferenciaSaldoInsuficiente_noCambiaSaldos() {
        Banco.transferencia(c1, c2, 200);
        assertEquals(100, c1.getSaldo());
        assertEquals(50, c2.getSaldo());
    }

    @Test
    void saldoTotalBanco_correcto() {
        assertEquals(150, banco.saldoTotalBanco());
    }

    @Test
    void obtenerCuenta_devuelveCuentaCorrecta() {
        assertEquals(c1, banco.obtenerCuenta(c1.getNumeroCuenta()));
    }

    @Test
    void agregarCuenta_incrementaBanco() throws Exception {
        CuentaBancaria c3 = new CuentaBancaria("Pedro", 20);
        banco.agregarCuenta(c3);
        assertEquals(170, banco.saldoTotalBanco());
    }
}
