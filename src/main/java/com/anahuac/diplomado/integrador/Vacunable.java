package com.anahuac.diplomado.integrador;

import java.time.LocalDate;
import java.util.List;

/**
 * ============================================================================
 * CONCEPTO POO: INTERFAZ (CONTRATO)
 * ============================================================================
 * Una interfaz define QUÉ comportamiento debe tener un objeto, sin definir
 * CÓMO se implementa. Quien implemente 'Vacunable' promete que sabe vacunarse
 * y sabe qué vacunas le hacen falta.
 */
public interface Vacunable {

    /**
     * TODO 1: Declarar el método para aplicar una vacuna.
     * 
     * Parámetros:
     *   - vacuna (String): nombre o tipo de la vacuna (ej. "Rabia", "Parvovirus").
     *   - fecha (LocalDate): fecha en que se aplicó la dosis.
     * Tipo de retorno: void
     */
    void vacunar(String vacuna, LocalDate fecha);

    /**
     * TODO 2: Declarar el método para consultar las vacunas pendientes.
     * 
     * No recibe parámetros.
     * Tipo de retorno: List<String> conteniendo los nombres de las vacunas recomendadas
     *                  que el animal aún no tiene registradas.
     */
    List<String> vacunasPendientes();
}
