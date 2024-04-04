package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import javafx.scene.image.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cooperative {
    private String nombre;
    private Image logo;

    public Cooperative() {
        //stage.setTitle(name);
    }

    public void loadFromTxtFile(String filePath) {
        File txtFile = new File(filePath);
        if (txtFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(txtFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();

                        if (key.equals("Nombre de la cooperativa")) {
                            this.nombre = value;
                        } else if (key.equals("Ruta del logo")) {
                            this.logo = new Image(value);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar cualquier error al leer el archivo
            }
        } else {
            // Establecer un nombre y un logo por defecto si el archivo no existe
            this.nombre = "Nombre por defecto";
            this.logo = null;
        }
    }

    // Método para guardar la información en el archivo de texto
    public void saveToTxtFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Nombre de la cooperativa: " + this.nombre);
            writer.newLine();
            writer.write("Ruta del logo: " + (this.logo != null ? this.logo.getUrl() : ""));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier error al escribir en el archivo
        }
    }
}


