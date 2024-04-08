/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;



import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

import javax.imageio.ImageIO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


import io.github.palexdev.materialfx.utils.SwingFXUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class CardPrintingController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private MFXButton btnPrint;

    @FXML
    private ImageView imageID;

    @FXML
    private ImageView imagePreview;

    @FXML
    private AnchorPane root;

    @FXML
    private Label txtAge;

    @FXML
    private Label txtFolio;

    @FXML
    private StackPane printPDF;

    @FXML
    private Label txtName;

    @FXML
    private TableView<Associated> usersTable;

    @FXML
    private ImageView imageCardLogo;
    
    @FXML
    private Label txtCardCooperativeName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppContext appContext = AppContext.getInstance();
        ObservableList<Associated> asociados = AppContext.getAsociados();
        loadUsersToTableView();
        setCompanyInfo();
        // populateTextFieldValues();

    }
    @Override
    public void initialize(){
    }
    private void loadUsersToTableView() {
        // Create columns
        TableColumn<Associated, String> folioColumn = new TableColumn<>("Folio");
        TableColumn<Associated, String> nameColumn = new TableColumn<>("Nombre");
        TableColumn<Associated, String> lastNameColumn = new TableColumn<>("Apellido");
        TableColumn<Associated, Integer> ageColumn = new TableColumn<>("Edad");

        // Set cell value factories
        folioColumn.setCellValueFactory(new PropertyValueFactory<>("assoFolio"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("assoName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("assoLastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("assoAge"));

        // Set columns
        usersTable.getColumns().addAll(folioColumn, nameColumn, lastNameColumn, ageColumn);

        // Load data
        ObservableList<Associated> asociados = AppContext.getAsociados();
        usersTable.setItems(asociados);

        usersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            populateTextFieldValues();
        });
    }

    private void populateTextFieldValues() {
        Associated selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            txtName.setText(selectedUser.getAssoName()+" "+selectedUser.getAssoLastName());
            //txtLastName.setText(selectedUser.getAssoLastName());
            txtFolio.setText(selectedUser.getAssoFolio());
            txtAge.setText(String.valueOf(selectedUser.getAssoAge()));


            String imageFileName = selectedUser.getAssoFolio() + ".png";
            String imagePath = "userphotos/" + imageFileName;
            File imageFile = new File(imagePath);

            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                imagePreview.setImage(image);
            } else {

            }
        }
    }
    @FXML
    private void onActionBtnPrint(ActionEvent event) {
        // Tomar una captura de pantalla de la StackPane
        SnapshotParameters params = new SnapshotParameters();
        // Se crea un objeto SnapshotParameters para especificar los parámetros de la captura de pantalla
        WritableImage snapshot = printPDF.snapshot(params, null);
        // Se toma la captura de pantalla de la StackPane y se almacena en el objeto WritableImage

        // Convertir la captura de pantalla a un BufferedImage
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
        // Se convierte la captura de pantalla en un objeto BufferedImage de Java AWT

        try (PDDocument document = new PDDocument()) {
            // Crear un nuevo documento PDF
            PDPage page = new PDPage();
            // Crear una nueva página en el documento PDF
            document.addPage(page);
            // Agregar la página al documento PDF

            // Convertir el BufferedImage a un arreglo de bytes
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byte[] imageData = byteArrayOutputStream.toByteArray();
            // Se convierte el BufferedImage a un arreglo de bytes y se almacena en el objeto ByteArrayOutputStream
            // Luego, se obtiene el arreglo de bytes del objeto ByteArrayOutputStream

            // Crear un PDImageXObject a partir del arreglo de bytes
            PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageData, "image/png");
            // Se crea un objeto PDImageXObject a partir del arreglo de bytes y se agrega al documento PDF

            // Crear un PDPageContentStream
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Dibujar la imagen en la página
                contentStream.drawImage(pdImage, 50, 50);
                // Se dibuja la imagen en la página en las coordenadas (50, 50)
            }

            // Guardar el documento como un archivo PDF
            document.save("card_" + txtFolio.getText() + ".pdf");
            // Se guarda el documento PDF en el disco con el nombre "card" seguido del texto del campo de folio
        } catch (IOException e) {
            e.printStackTrace();
            // Si ocurre una excepción de entrada/salida, se imprime la pila de trazas de la excepción
        }
    }
    private void setCompanyInfo() {
        Cooperative cooperative = (Cooperative) AppContext.getInstance().get("cooperative");

        if (cooperative != null) {
            Image logo = cooperative.getLogo();
            if (logo != null) {
                imageCardLogo.setImage(cooperative.getLogo());
            }

            String companyName = cooperative.getName();

            txtCardCooperativeName.setText(companyName);

        }
    }

}
