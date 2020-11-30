import controller.Controller;
import controller.ControllerImpl;
import database.DataBaseDispatcher;
import model.ValueReceivable;
import model.CalculatorModel;
import model.ModelDispatcher;
import view.ViewDispatcher;
import view.MenuListReceivable;

import java.awt.*;

public class Main {
    public static void main(String[] args){

        EventQueue.invokeLater(() -> {

            DataBaseDispatcher dataBaseDispatcher = new DataBaseDispatcher();
            MenuListReceivable menuListReceivable = dataBaseDispatcher.getMenuReceiver();
            ValueReceivable valueReceivable = dataBaseDispatcher.getValueReceiver();

            CalculatorModel model = new ModelDispatcher(valueReceivable);

            Controller controller = new ControllerImpl(model);

            ViewDispatcher viewDispatcher = new ViewDispatcher(menuListReceivable, model, controller);

        });
    }
}
