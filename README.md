# 🐾 Proyecto Integrador: Sistema Veterinario "Patitas Felices"
### Diplomado en Desarrollo de Software - Módulo de Programación Orientada a Objetos (POO) en Java

---

## 🎯 Objetivo del Proyecto

El objetivo de este proyecto integrador es demostrar y evaluar el dominio de los conceptos fundamentales de la **Programación Orientada a Objetos (POO)** en Java, integrándolos en una solución realista: un sistema de gestión clínica veterinaria que administra pacientes, control de vacunación y persistencia de datos en disco.

---

## 🧠 Conceptos de POO Evaluados

| Concepto POO | Clase(s) donde se aplica | Descripción |
| :--- | :--- | :--- |
| **Interfaces (Contratos)** | [`Vacunable.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Vacunable.java) | Define el contrato de comportamiento para cualquier ser vivo que pueda vacunarse. |
| **Clases Abstractas y Abstracción** | [`Mascota.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Mascota.java) | Modela la entidad base. No se pueden crear mascotas genéricas en la vida real. |
| **Encapsulamiento** | Todas las clases | Atributos con modificador `private`, constructores con asignación (`this.`) y métodos de acceso `get...`. |
| **Herencia** | [`Perro.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Perro.java), [`Gato.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Gato.java) | Reutilizan estado y comportamiento de `Mascota`, invocando `super(...)` en sus constructores. |
| **Polimorfismo** | [`Mascota.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Mascota.java), [`Perro.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Perro.java), [`Gato.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Gato.java) | Sobrescritura (`@Override`) de `hacerSonido()`, `getTipo()`, `getDetalle()` y `toString()`. |
| **Composición** | `Mascota` tiene `CartillaVacunacion` | Una mascota posee una cartilla como parte de su estado interno. |
| **Colaboración entre Objetos** | [`Veterinario.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Veterinario.java) | El veterinario no modifica directamente la cartilla; colabora pidiéndole a la mascota que se vacune. |
| **Colecciones (List y Map)** | [`Veterinaria.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Veterinaria.java), [`CartillaVacunacion.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/CartillaVacunacion.java) | `ArrayList` para listas de pacientes y `HashMap` para pares vacuna $\rightarrow$ fecha. |
| **Manejo de Archivos (I/O)** | [`Veterinaria.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Veterinaria.java), [`CartillaVacunacion.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/CartillaVacunacion.java) | Lectura y escritura con `java.nio.file.Files` y `Path` en formatos CSV y TXT. |

---

## 🛠️ Estructura del Código

```text
modulo2/
├── pom.xml
└── src/
    ├── main/java/com/anahuac/diplomado/integrador/
    │   ├── Vacunable.java          <-- Interfaz (Contrato)
    │   ├── Mascota.java            <-- Clase abstracta base
    │   ├── Perro.java              <-- Subclase concreta
    │   ├── Gato.java               <-- Subclase concreta
    │   ├── CartillaVacunacion.java <-- Colección Map y persistencia individual
    │   ├── Veterinario.java        <-- Colaboración y reglas de negocio
    │   ├── Veterinaria.java        <-- Colección List y persistencia global
    │   └── Main.java               <-- Demostrador principal del sistema
    └── test/java/com/anahuac/diplomado/
        └── AppTest.java            <-- Suite de pruebas unitarias (JUnit)
```

---

## 🚀 Instrucciones de Ejecución

Abre tu terminal dentro del directorio `modulo2`:

```bash
cd modulo2
```

### 1. Compilar el proyecto
```bash
mvn compile
```

### 2. Ejecutar las pruebas unitarias automatizadas
A medida que completes los `TODOs`, tus pruebas pasarán de rojo a verde:
```bash
mvn test
```

### 3. Ejecutar la aplicación principal
```bash
mvn exec:java
```

---

## 📋 Guía de Trabajo Sugerida (TODO 1 al 37)

Te sugerimos avanzar en el siguiente orden:

### Fase 1: El Contrato y la Clase Base
1. **[`Vacunable.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Vacunable.java)**:
   - `TODO 1` y `TODO 2`: Declarar los métodos abstractos `vacunar` y `vacunasPendientes`.
2. **[`Mascota.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Mascota.java)**:
   - `TODO 3`: Declarar atributos privados y la composición con `CartillaVacunacion`.
   - `TODO 4`: Implementar el constructor asignando con `this`.
   - `TODO 5`: Implementar `vacunar` delegando a `cartilla.registrar`.
   - `TODO 6`: Implementar `vacunasPendientes` comparando con `cartilla.tieneVacuna`.
   - `TODO 7`: Implementar los getters.
   - `TODO 8`: Implementar `toString()` con formato polimórfico.

### Fase 2: Herencia y Especialización
3. **[`Perro.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Perro.java)**:
   - `TODO 9` al `TODO 15`: `extends Mascota`, atributo `raza`, constructor con `super(...)`, y sobrescritura de métodos (`¡Guau!`, `"PERRO"`, raza, vacunas).
4. **[`Gato.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Gato.java)**:
   - `TODO 16` al `TODO 22`: `extends Mascota`, atributo `interior`, constructor con `super(...)`, y sobrescritura de métodos (`¡Miau!`, `"GATO"`, `"interior"`/`"exterior"`, vacunas).

### Fase 3: La Cartilla y Persistencia Individual
5. **[`CartillaVacunacion.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/CartillaVacunacion.java)**:
   - `TODO 23`: Inicializar el `Map<String, LocalDate>`.
   - `TODO 24`: Implementar `registrar` con `.put(...)`.
   - `TODO 25`: Implementar `tieneVacuna` con `.containsKey(...)`.
   - `TODO 26`: Implementar `mostrar` verificando si está vacía.
   - `TODO 27`: Implementar `guardar` escribiendo líneas `"vacuna,fecha"`.
   - `TODO 28`: Implementar `cargar` leyendo y parseando `LocalDate.parse`.

### Fase 4: Colaboración y Reglas de Negocio
6. **[`Veterinario.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Veterinario.java)**:
   - `TODO 29` y `TODO 30`: Atributo y constructor.
   - `TODO 31`: Implementar `aplicarVacuna` validando si la vacuna es recomendada antes de aplicarla.

### Fase 5: Agregación, Búsqueda y Persistencia Global
7. **[`Veterinaria.java`](modulo2/src/main/java/com/anahuac/diplomado/integrador/Veterinaria.java)**:
   - `TODO 32`: Atributos privados y lista de pacientes.
   - `TODO 33`: Implementar `registrarPaciente`.
   - `TODO 34`: Implementar `buscar` ignorando mayúsculas/minúsculas (`equalsIgnoreCase`).
   - `TODO 35`: Implementar `mostrarPacientes`.
   - `TODO 36`: Implementar `guardarTodo` (genera `pacientes.csv` y archivos `cartilla_<nombre>.txt`).
   - `TODO 37`: Implementar `cargarTodo` (reconstruye objetos `Perro`/`Gato` y sus cartillas).

---

## 🖥️ Salida Esperada en Consola

Al ejecutar `mvn exec:java` tras completar todos los `TODOs`, deberás obtener una salida idéntica a la siguiente:

```text
🐾 INICIANDO SISTEMA VETERINARIO 'PATITAS FELICES' 🐾

📋 Pacientes de Patitas Felices:
  • PERRO Firulais (3 años, dueño: Ana, Labrador)
  • GATO Michi (2 años, dueño: Luis, interior)
  • PERRO Rex (5 años, dueño: Marta, Pastor Alemán)

--- Jornada de vacunación ---
🩺 Dr(a). Sofía Ramírez aplicó Rabia a Firulais
🩺 Dr(a). Sofía Ramírez aplicó Parvovirus a Firulais
🩺 Dr(a). Sofía Ramírez aplicó Triple felina a Michi
⚠️  Moquillo no es una vacuna recomendada para Michi

--- Polimorfismo ---
Firulais dice ¡Guau!
Michi dice ¡Miau!
Rex dice ¡Guau!

--- Cartillas ---
Firulais | pendientes: [Moquillo]
    💉 Parvovirus -> 2026-10-02
    💉 Rabia -> 2026-10-02
Michi | pendientes: [Rabia, Leucemia felina]
    💉 Triple felina -> 2026-10-02
Rex | pendientes: [Rabia, Parvovirus, Moquillo]
    (cartilla vacía)

💾 Datos guardados en la carpeta: datos_veterinaria

📋 Pacientes de Patitas Felices (recargada):
  • PERRO Firulais (3 años, dueño: Ana, Labrador)
  • GATO Michi (2 años, dueño: Luis, interior)
  • PERRO Rex (5 años, dueño: Marta, Pastor Alemán)
Cartilla de Firulais recuperada desde el archivo:
    💉 Parvovirus -> 2026-10-02
    💉 Rabia -> 2026-10-02
```

---

## 📊 Rúbrica de Evaluación Sugerida (100 Puntos)

| Criterio | Puntos | Aspectos a Evaluar |
| :--- | :---: | :--- |
| **1. Encapsulamiento y Abstracción** | 20 pts | Uso correcto de `private`, métodos de acceso `get...`, clase abstracta `Mascota` y métodos abstractos. |
| **2. Herencia y Constructores** | 20 pts | Correcto uso de `extends`, llamada a `super(...)` en `Perro` y `Gato`, atributos específicos de subclase. |
| **3. Polimorfismo e Interfaces** | 20 pts | Implementación de `Vacunable`, sobrescritura `@Override` de métodos en subclases y comportamiento dinámico. |
| **4. Colecciones y Colaboración** | 20 pts | Uso adecuado de `List` (`ArrayList`) y `Map` (`HashMap`), búsqueda lineal, interacción `Veterinario` $\rightarrow$ `Mascota`. |
| **5. Persistencia y Archivos (I/O)** | 10 pts | Guardado y lectura correcta de `pacientes.csv` y `cartilla_<nombre>.txt` con manejo de excepciones `IOException`. |
| **6. Pruebas Unitarias** | 10 pts | Las 9 pruebas de [`AppTest.java`](modulo2/src/test/java/com/anahuac/diplomado/AppTest.java) pasan en verde con `mvn test`. |
| **Total** | **100 pts** | |
