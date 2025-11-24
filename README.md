# Sistema de Gestión de Cooperativa

Sistema de gestión integral para cooperativas de ahorro y crédito desarrollado con JavaFX y Maven.

## Autores

- Kendall Fonseca
- Emmanuel Gamboa

## Descripción

Aplicación de escritorio para la administración de cooperativas que permite gestionar asociados, cuentas bancarias, depósitos, retiros y movimientos financieros. El sistema cuenta con diferentes niveles de acceso (administrador, trabajador y asociado) y funcionalidades de respaldo de datos.

## Características Principales

- **Gestión de Asociados**: Registro y mantenimiento de información de asociados con captura fotográfica
- **Cuentas Bancarias**: Administración de diferentes tipos de cuentas
- **Transacciones**: Procesamiento de depósitos y retiros
- **Reportes**: Generación de reportes en PDF de movimientos y estados de cuenta
- **Respaldos**: Sistema de backup y restauración de datos en formato JSON
- **Interfaz Moderna**: Diseño basado en MaterialFX con interfaz intuitiva
- **Control de Acceso**: Diferentes vistas según el tipo de usuario

## Requisitos del Sistema

- **Java**: JDK 11 o superior
- **Maven**: 3.6 o superior
- **JavaFX**: 21 (incluido en las dependencias)

## Tecnologías Utilizadas

- JavaFX 21 - Framework de interfaz gráfica
- MaterialFX 11.16.1 - Componentes de interfaz Material Design
- Apache PDFBox 2.0.24 - Generación de documentos PDF
- Jackson 2.17.0 - Serialización/deserialización JSON
- Webcam Capture 0.3.12 - Captura de imágenes desde webcam

## Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/kfonsecah/Tarea1_KendallFonseca_EmmanuelGamboa.git
cd Tarea1_KendallFonseca_EmmanuelGamboa
```

2. Compilar el proyecto:
```bash
mvn clean compile
```

3. Ejecutar la aplicación:
```bash
mvn javafx:run
```

## Uso

### Perfiles de Usuario

El sistema soporta tres tipos de acceso:

- **Administrador (A)**: Acceso completo a todas las funcionalidades
- **Trabajador (W)**: Gestión de asociados y transacciones
- **Asociado (M)**: Consulta de movimientos y saldos personales

### Acceso Directo a Perfiles

Para iniciar con un perfil específico:

```bash
mvn javafx:run -Dexec.args="A"  # Administrador
mvn javafx:run -Dexec.args="W"  # Trabajador
mvn javafx:run -Dexec.args="M"  # Asociado
```

## Estructura del Proyecto

```
src/main/java/cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/
├── controller/     # Controladores de las vistas
├── model/          # Modelos de datos (Cooperative, Account, Associated, etc.)
├── util/           # Utilidades y helpers
└── App.java        # Clase principal de la aplicación

src/main/resources/
└── view/           # Archivos FXML de las interfaces
```

## Generación de Ejecutable

Para crear un JAR ejecutable:

```bash
mvn clean package
```

El archivo JAR se generará en el directorio `target/`.

## Licencia

Este proyecto es parte de un trabajo académico para la Universidad Nacional de Costa Rica (UNA).

---

**Institución**: Universidad Nacional de Costa Rica (UNA)  
**Curso**: Programación Avanzada  
**Versión**: 1.0
