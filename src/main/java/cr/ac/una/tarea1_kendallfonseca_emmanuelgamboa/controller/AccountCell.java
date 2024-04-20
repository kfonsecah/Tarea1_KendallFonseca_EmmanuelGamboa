package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import javafx.scene.control.ListCell;

class AccountCell extends ListCell<Account> {

    @Override
    protected void updateItem(Account account, boolean empty) {
        super.updateItem(account, empty);
        if (empty || account == null) {
            setText(null);
        } else {
            String text = String.format("[%s/%s/%.2f/%s/%s]%n",


                    account.getBalance(),
                    account.getCurrency(),
                    account.getAccountHolder());
            setText(text);
        }
    }

}
