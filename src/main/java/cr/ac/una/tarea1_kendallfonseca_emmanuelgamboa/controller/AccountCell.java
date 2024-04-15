package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import javafx.scene.control.ListCell;

class AccountCell extends ListCell<Account> {

    @Override
    protected void updateItem(Account account, boolean empty) {
        super.updateItem(account, empty);
        System.out.println("Updating item: " + account);
        if (empty || account == null) {
            setText(null);
        } else {
            setText(account.getAccountNumber() + " - " + account.getAccountType());
        }
    }
}
