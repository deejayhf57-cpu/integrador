package com.anahuac.diplomado;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.anahuac.diplomado.integrador.CartillaVacunacion;
import com.anahuac.diplomado.integrador.Gato;
import com.anahuac.diplomado.integrador.Mascota;
import com.anahuac.diplomado.integrador.Perro;
import com.anahuac.diplomado.integrador.Vacunable;
import com.anahuac.diplomado.integrador.Veterinaria;
import com.anahuac.diplomado.integrador.Veterinario;

/**
 * ============================================================================
 * SUITE DE PRUEBAS UNITARIAS (JUNIT)
 * ============================================================================
 * Puedes ejecutar estas pruebas en cualquier momento desde tu terminal con:
 *     mvn test
 * 
 * A medida que implementes cada clase y sus TODOs, las pruebas correspondientes
 * comenzarán a pasar de ROJO a VERDE.
 */
public class AppTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    // ------------------------------------------------------------------------
    // PRUEBAS DE HERENCIA, POLIMORFISMO Y CLASES CONCRETAS (TODO 3 al 22)
    // ------------------------------------------------------------------------
    @Test
    public void testPerroHerenciaYPolimorfismo() {
        Perro perro = new Perro("Firulais", 3, "Ana", "Labrador");

        // Verifica herencia e interfaz
        assertTrue("Perro debe ser una instancia de Mascota", perro instanceof Mascota);
        assertTrue("Perro debe implementar Vacunable", perro instanceof Vacunable);

        // Verifica encapsulamiento y constructor
        assertEquals("Firulais", perro.getNombre());
        assertEquals(3, perro.getEdad());
        assertEquals("Ana", perro.getDueno());
        assertEquals("Labrador", perro.getDetalle());

        // Verifica polimorfismo
        assertEquals("PERRO", perro.getTipo());
        assertEquals("¡Guau!", perro.hacerSonido());

        List<String> vacunas = perro.vacunasRecomendadas();
        assertNotNull(vacunas);
        assertEquals(3, vacunas.size());
        assertTrue(vacunas.contains("Rabia"));
        assertTrue(vacunas.contains("Parvovirus"));
        assertTrue(vacunas.contains("Moquillo"));
    }

    @Test
    public void testGatoHerenciaYPolimorfismo() {
        Gato gatoInterior = new Gato("Michi", 2, "Luis", true);
        Gato gatoExterior = new Gato("Tom", 4, "Carlos", false);

        assertTrue("Gato debe ser una instancia de Mascota", gatoInterior instanceof Mascota);
        assertTrue("Gato debe implementar Vacunable", gatoInterior instanceof Vacunable);

        assertEquals("Michi", gatoInterior.getNombre());
        assertEquals(2, gatoInterior.getEdad());
        assertEquals("Luis", gatoInterior.getDueno());
        assertEquals("interior", gatoInterior.getDetalle());
        assertEquals("exterior", gatoExterior.getDetalle());

        assertEquals("GATO", gatoInterior.getTipo());
        assertEquals("¡Miau!", gatoInterior.hacerSonido());

        List<String> vacunas = gatoInterior.vacunasRecomendadas();
        assertNotNull(vacunas);
        assertEquals(3, vacunas.size());
        assertTrue(vacunas.contains("Rabia"));
        assertTrue(vacunas.contains("Triple felina"));
        assertTrue(vacunas.contains("Leucemia felina"));
    }

    @Test
    public void testMascotaToString() {
        Perro perro = new Perro("Firulais", 3, "Ana", "Labrador");
        Gato gato = new Gato("Michi", 2, "Luis", true);

        assertEquals("PERRO Firulais (3 años, dueño: Ana, Labrador)", perro.toString());
        assertEquals("GATO Michi (2 años, dueño: Luis, interior)", gato.toString());
    }

    // ------------------------------------------------------------------------
    // PRUEBAS DE CARTILLA Y COLECCIONES (TODO 23 al 28)
    // ------------------------------------------------------------------------
    @Test
    public void testCartillaVacunacionRegistrarYTieneVacuna() {
        CartillaVacunacion cartilla = new CartillaVacunacion();
        assertFalse(cartilla.tieneVacuna("Rabia"));

        LocalDate hoy = LocalDate.now();
        cartilla.registrar("Rabia", hoy);
        assertTrue(cartilla.tieneVacuna("Rabia"));
        assertFalse(cartilla.tieneVacuna("Parvovirus"));
    }

    @Test
    public void testVacunasPendientes() {
        Perro perro = new Perro("Firulais", 3, "Ana", "Labrador");
        List<String> pendientesIniciales = perro.vacunasPendientes();

        // Al inicio debe tener pendientes todas las recomendadas (Rabia, Parvovirus, Moquillo)
        assertEquals(3, pendientesIniciales.size());
        assertTrue(pendientesIniciales.contains("Rabia"));

        // Se le aplica Rabia
        perro.vacunar("Rabia", LocalDate.now());

        List<String> pendientesDespues = perro.vacunasPendientes();
        assertEquals(2, pendientesDespues.size());
        assertFalse(pendientesDespues.contains("Rabia"));
        assertTrue(pendientesDespues.contains("Parvovirus"));
        assertTrue(pendientesDespues.contains("Moquillo"));
    }

    // ------------------------------------------------------------------------
    // PRUEBAS DE COLABORACIÓN Y REGLAS DE NEGOCIO (TODO 29 al 31)
    // ------------------------------------------------------------------------
    @Test
    public void testVeterinarioAplicaVacunaValidaEInvalida() {
        Veterinario vet = new Veterinario("Sofía Ramírez");
        Gato gato = new Gato("Michi", 2, "Luis", true);

        // Vacuna válida para gato: Triple felina
        vet.aplicarVacuna(gato, "Triple felina");
        assertTrue(gato.getCartilla().tieneVacuna("Triple felina"));

        // Vacuna no recomendada para gato: Moquillo
        vet.aplicarVacuna(gato, "Moquillo");
        assertFalse(gato.getCartilla().tieneVacuna("Moquillo"));
    }

    // ------------------------------------------------------------------------
    // PRUEBAS DE VETERINARIA (LISTA, BÚSQUEDA Y PERSISTENCIA) (TODO 32 al 37)
    // ------------------------------------------------------------------------
    @Test
    public void testVeterinariaRegistroYBusqueda() {
        Veterinaria clinica = new Veterinaria("Patitas Felices");
        Perro p = new Perro("Firulais", 3, "Ana", "Labrador");
        Gato g = new Gato("Michi", 2, "Luis", true);

        clinica.registrarPaciente(p);
        clinica.registrarPaciente(g);

        assertEquals(2, clinica.getPacientes().size());

        // Búsqueda insensible a mayúsculas
        assertNotNull(clinica.buscar("firulais"));
        assertEquals("Firulais", clinica.buscar("FIRULAIS").getNombre());
        assertEquals("Michi", clinica.buscar("michi").getNombre());

        // Mascota que no existe
        assertNull(clinica.buscar("Snoopy"));
    }

    @Test
    public void testPersistenciaCartilla() throws IOException {
        Path tempFile = tempFolder.newFile("test_cartilla.txt").toPath();
        CartillaVacunacion original = new CartillaVacunacion();
        LocalDate fecha = LocalDate.of(2026, 5, 10);
        original.registrar("Rabia", fecha);
        original.guardar(tempFile);

        assertTrue(Files.exists(tempFile));

        CartillaVacunacion cargada = new CartillaVacunacion();
        cargada.cargar(tempFile);
        assertTrue(cargada.tieneVacuna("Rabia"));
    }

    @Test
    public void testPersistenciaVeterinariaCompleta() throws IOException {
        Path testDir = tempFolder.newFolder("datos_test").toPath();

        Veterinaria original = new Veterinaria("Clínica Test");
        Perro p = new Perro("Rex", 5, "Marta", "Pastor Alemán");
        p.vacunar("Rabia", LocalDate.of(2026, 1, 15));
        Gato g = new Gato("Michi", 2, "Luis", true);

        original.registrarPaciente(p);
        original.registrarPaciente(g);
        original.guardarTodo(testDir);

        // Verificar que los archivos existen
        assertTrue(Files.exists(testDir.resolve("pacientes.csv")));
        assertTrue(Files.exists(testDir.resolve("cartilla_Rex.txt")));
        assertTrue(Files.exists(testDir.resolve("cartilla_Michi.txt")));

        // Recargar en una nueva clínica
        Veterinaria recargada = new Veterinaria("Clínica Recargada");
        recargada.cargarTodo(testDir);

        assertEquals(2, recargada.getPacientes().size());

        Mascota rexRecargado = recargada.buscar("Rex");
        assertNotNull(rexRecargado);
        assertEquals("PERRO", rexRecargado.getTipo());
        assertEquals("Pastor Alemán", rexRecargado.getDetalle());
        assertTrue(rexRecargado.getCartilla().tieneVacuna("Rabia"));

        Mascota michiRecargado = recargada.buscar("Michi");
        assertNotNull(michiRecargado);
        assertEquals("GATO", michiRecargado.getTipo());
        assertEquals("interior", michiRecargado.getDetalle());
    }
}
