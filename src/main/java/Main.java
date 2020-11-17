import database.DataBaseDispatcher;
import view.*;

import java.awt.*;

public class Main {
    public static void main(String[] args){

        EventQueue.invokeLater(() -> {
            DataBaseDispatcher dataBaseDispatcher = new DataBaseDispatcher();
            ReceivableMenu receivableMenu = dataBaseDispatcher.getMenuReceiver();

            ViewDispatcher viewDispatcher = new ViewDispatcher(receivableMenu);
            viewDispatcher.createVew();
        });
    }
}
