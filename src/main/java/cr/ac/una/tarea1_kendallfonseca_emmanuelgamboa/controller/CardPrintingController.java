/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;



import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


import javax.imageio.ImageIO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;


import io.github.palexdev.materialfx.utils.SwingFXUtils;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private TableView<Associated> userSearchList;

    @FXML
    private MFXButton btnSearch;

    @FXML
    private MFXComboBox<String> comboBoxFilter;

    @FXML
    private MFXTextField txtSearch;

    @FXML
    private ImageView imageCardLogo;

    @FXML
    private Label txtCardCooperativeName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnPrint.setDisable(true);
        AppContext appContext = AppContext.getInstance();
        ObservableList<Associated> asociados = AppContext.getAsociados();
        loadUsersToTableView();
        setCompanyInfo();

        comboBoxFilter.getItems().addAll("Todo", "Nombre", "Apellido", "Folio", "Edad");
        comboBoxFilter.setValue("Todo");

        userSearchList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnPrint.setDisable(newValue == null);
        });
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
        userSearchList.getColumns().addAll(folioColumn, nameColumn, lastNameColumn, ageColumn);

        // Load data
        ObservableList<Associated> asociados = AppContext.getAsociados();
        userSearchList.setItems(asociados);

        userSearchList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            populateTextFieldValues();
        });
    }

    @FXML
    private void onActionBtnSearch(ActionEvent event) {

        String filter = comboBoxFilter.getValue();
        if (!txtSearch.getText().isEmpty()) {
            ObservableList<Associated> filteredData = FXCollections.observableArrayList();
            ObservableList<Associated> list = AppContext.getAsociados();

            for (int i = 0; i < list.size(); i++) {
                Associated data = list.get(i);
                if(filter.equals("Todo")&& data.getAssoName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        data.getAssoLastName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        data.getAssoFolio().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        Integer.toString(data.getAssoAge()).contains(txtSearch.getText())){
                    filteredData.add(data);
                } else if (filter.equals("Nombre") && data.getAssoName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Apellido") && data.getAssoLastName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Folio") && data.getAssoFolio().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Edad") && Integer.toString(data.getAssoAge()).contains(txtSearch.getText())) {
                    filteredData.add(data);
                }
            }
            userSearchList.setItems(filteredData);
        } else {
            updateTableView();

        }
    }

    private void updateTableView() {

        ObservableList<Associated> asociados = AppContext.getAsociados();

        // Update the TableView with the updated Associated objects
        int selectedIndex = userSearchList.getSelectionModel().getSelectedIndex();
        userSearchList.setItems(asociados);
        userSearchList.getSelectionModel().select(selectedIndex);
        userSearchList.refresh();
    }
    private void populateTextFieldValues() {
        Associated selectedUser = userSearchList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            txtName.setText(selectedUser.getAssoName()+" "+selectedUser.getAssoLastName());
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
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Exito", root.getScene().getWindow(), "El pdf con su Carnet ha sido creado correctamente");

        } catch (IOException e) {
            e.printStackTrace();
            // Si ocurre una excepción de entrada/salida, se imprime la pila de trazas de la excepción
        }
    }
    //metodo para setear la info de la cooperativa
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
