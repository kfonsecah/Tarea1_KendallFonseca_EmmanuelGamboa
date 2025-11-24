# Cooperative Management System

Comprehensive management system for savings and credit cooperatives developed with JavaFX and Maven.

## Authors

- Kendall Fonseca
- Emmanuel Gamboa

## Description

Desktop application for cooperative management that allows handling associates, bank accounts, deposits, withdrawals, and financial transactions. The system features different access levels (administrator, worker, and associate) and data backup functionalities.

## Key Features

- **Associate Management**: Registration and maintenance of associate information with photo capture
- **Bank Accounts**: Administration of different account types
- **Transactions**: Processing of deposits and withdrawals
- **Reports**: PDF report generation for transactions and account statements
- **Backups**: Backup and restore system in JSON format
- **Modern Interface**: MaterialFX-based design with intuitive interface
- **Access Control**: Different views according to user type

## System Requirements

- **Java**: JDK 11 or higher
- **Maven**: 3.6 or higher
- **JavaFX**: 21 (included in dependencies)

## Technologies Used

- JavaFX 21 - Graphical interface framework
- MaterialFX 11.16.1 - Material Design interface components
- Apache PDFBox 2.0.24 - PDF document generation
- Jackson 2.17.0 - JSON serialization/deserialization
- Webcam Capture 0.3.12 - Image capture from webcam

## Installation

1. Clone the repository:
```bash
git clone https://github.com/kfonsecah/Tarea1_KendallFonseca_EmmanuelGamboa.git
cd Tarea1_KendallFonseca_EmmanuelGamboa
```

2. Compile the project:
```bash
mvn clean compile
```

3. Run the application:
```bash
mvn javafx:run
```

## Usage

### User Profiles

The system supports three types of access:

- **Administrator (A)**: Full access to all functionalities
- **Worker (W)**: Associate and transaction management
- **Associate (M)**: Personal transaction and balance queries

### Direct Profile Access

To start with a specific profile:

```bash
mvn javafx:run -Dexec.args="A"  # Administrator
mvn javafx:run -Dexec.args="W"  # Worker
mvn javafx:run -Dexec.args="M"  # Associate
```

## Project Structure

```
src/main/java/cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/
├── controller/     # View controllers
├── model/          # Data models (Cooperative, Account, Associated, etc.)
├── util/           # Utilities and helpers
└── App.java        # Main application class

src/main/resources/
└── view/           # FXML interface files
```

## Executable Generation

To create an executable JAR:

```bash
mvn clean package
```

The JAR file will be generated in the `target/` directory.

## License

This project is part of academic work for the National University of Costa Rica (UNA).

---

**Institution**: National University of Costa Rica (UNA)  
**Course**: Advanced Programming  
**Version**: 1.0
