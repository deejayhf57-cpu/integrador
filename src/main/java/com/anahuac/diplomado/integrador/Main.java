package com.anahuac.diplomado.integrador;

import java.io.IOException;
import java.nio.file.Path;

/**
 * ============================================================================
 * CLASE PRINCIPAL (DRIVER / DEMOSTRADOR)
 * ============================================================================
 * Esta clase orquesta y pone a prueba todos los conceptos de POO implementados:
 * 1. Abstracción y Encapsulamiento (Mascota, CartillaVacunacion)
 * 2. Herencia (Perro y Gato heredando de Mascota)
 * 3. Polimorfismo (hacerSonido, toString, getTipo, getDetalle)
 * 4. Interfaz / Contrato (Vacunable)
 * 5. Colaboración entre Objetos (Veterinario aplica vacuna a Mascota)
 * 6. Colecciones (List<Mascota> y Map<String, LocalDate>)
 * 7. Entrada/Salida (Persistencia en CSV y archivos de texto)
 * 
 * A medida que completes los TODOs en cada clase, ejecuta esta clase con:
 *     mvn exec:java
 * y verifica que la salida en pantalla coincida con la salida esperada.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("🐾 INICIANDO SISTEMA VETERINARIO 'PATITAS FELICES' 🐾\n");

        Veterinaria clinica = new Veterinaria("Patitas Felices");
        Veterinario doctora = new Veterinario("Sofía Ramírez");

        // --------------------------------------------------------------------
        // ETAPA 1: REGISTRAR PACIENTES (Colecciones y Herencia)
        // --------------------------------------------------------------------
        clinica.registrarPaciente(new Perro("Firulais", 3, "Ana", "Labrador"));
        clinica.registrarPaciente(new Gato("Michi", 2, "Luis", true));
        clinica.registrarPaciente(new Perro("Rex", 5, "Marta", "Pastor Alemán"));
        clinica.mostrarPacientes();

        // --------------------------------------------------------------------
        // ETAPA 2: COLABORACIÓN ENTRE OBJETOS (Veterinario -> Mascota -> Cartilla)
        // --------------------------------------------------------------------
        System.out.println("\n--- Jornada de vacunación ---");
        doctora.aplicarVacuna(clinica.buscar("Firulais"), "Rabia");
        doctora.aplicarVacuna(clinica.buscar("Firulais"), "Parvovirus");
        doctora.aplicarVacuna(clinica.buscar("Michi"), "Triple felina");
        doctora.aplicarVacuna(clinica.buscar("Michi"), "Moquillo");   // No aplica a gatos: debe avisar

        // --------------------------------------------------------------------
        // ETAPA 3: POLIMORFISMO (Misma llamada, comportamiento distinto)
        // --------------------------------------------------------------------
        System.out.println("\n--- Polimorfismo ---");
        for (Mascota m : clinica.getPacientes()) {
            if (m != null) {
                System.out.println(m.getNombre() + " dice " + m.hacerSonido());
            }
        }

        // --------------------------------------------------------------------
        // ETAPA 4: CARTILLAS Y VACUNAS PENDIENTES (Lógica con Colecciones)
        // --------------------------------------------------------------------
        System.out.println("\n--- Cartillas ---");
        for (Mascota m : clinica.getPacientes()) {
            if (m != null) {
                System.out.println(m.getNombre() + " | pendientes: " + m.vacunasPendientes());
                m.mostrarCartilla();
            }
        }

        // --------------------------------------------------------------------
        // ETAPA 5: ENTRADA / SALIDA (Guardar en archivos y recargar en clínica nueva)
        // --------------------------------------------------------------------
        Path carpeta = Path.of("datos_veterinaria");
        clinica.guardarTodo(carpeta);
        System.out.println("\n💾 Datos guardados en la carpeta: " + carpeta);

        Veterinaria recargada = new Veterinaria("Patitas Felices (recargada)");
        recargada.cargarTodo(carpeta);
        System.out.println();
        recargada.mostrarPacientes();

        Mascota firulaisRecargado = recargada.buscar("Firulais");
        if (firulaisRecargado != null && firulaisRecargado.getCartilla() != null) {
            System.out.println("Cartilla de Firulais recuperada desde el archivo:");
            firulaisRecargado.getCartilla().mostrar();
        } else {
            System.out.println("⚠️ Firulais no fue recuperado (revisa guardarTodo/cargarTodo en Veterinaria).");
        }
    }
}
