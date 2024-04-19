package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author kfonsecah
 */
public class OpenAccountsController extends Controller implements Initializable {

    @FXML
    private ListView<Account> activeAccounts;

    @FXML
    private ListView<Account> inactiveAccounts;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {




        inactiveAccounts.setCellFactory(param -> new AccountCell());
        activeAccounts.setCellFactory(param -> new AccountCell());

        activeAccounts.setOnDragDetected(this::onDragDetected);
        inactiveAccounts.setOnDragDetected(this::onDragDetected);

        activeAccounts.setOnDragOver(this::onDragOver);
        inactiveAccounts.setOnDragOver(this::onDragOver);

        activeAccounts.setOnDragDropped(this::onDragDropped);
        inactiveAccounts.setOnDragDropped(this::onDragDropped);
    }

    @FXML
    void onDragDetected(MouseEvent event) {
        ListView<Account> listView = (ListView<Account>) event.getSource();
        Account selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Dragboard dragboard = listView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();


            String accountString = String.format("[%s/%s/%.2f/%s/%s]%n",
                    selectedItem.getAccountNumber(),
                    selectedItem.getAccountType(),
                    selectedItem.getBalance(),
                    selectedItem.getCurrency(),
                    selectedItem.getAccountHolder());

            content.putString(accountString);
            dragboard.setContent(content);
        }
        event.consume();
    }


    @FXML
    void onDragOver(DragEvent event) {
        if (event.getGestureSource() != event.getSource() && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }


    @FXML
    void onDragDropped(DragEvent event) {

    }

    private void findAndMoveAccount(Account account, ListView<Account> fromListView, ListView<Account> toListView) {
        if (fromListView.getItems().contains(account)) {
            fromListView.getItems().remove(account);
            toListView.getItems().add(account);
        } else {
            System.out.println("Error: The account was not found in either the active or inactive accounts list.");
        }
    }

    @Override
    public void initialize() {
    }
}




