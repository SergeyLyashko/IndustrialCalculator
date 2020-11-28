import database.DataBaseDispatcher;
import view.ViewDispatcher;
import view.controller.MenuReceivable;

import java.awt.*;

public class Main {
    public static void main(String[] args){

        EventQueue.invokeLater(() -> {
            DataBaseDispatcher dataBaseDispatcher = new DataBaseDispatcher();
            MenuReceivable menuReceivable = dataBaseDispatcher.getMenuReceiver();
            ViewDispatcher view = new ViewDispatcher(menuReceivable);
        });
    }
}
