package database;

import model.ValueReceivable;
import view.MenuListReceivable;

public class DataBaseDispatcher {

    private final Data data;

    public DataBaseDispatcher(){
        data = new Data();
        Connector connector = new Connector();
        data.addConnection(connector);
    }

    public MenuListReceivable getMenuReceiver(){
        return new MenuListReceivableImpl(data);
    }

    public ValueReceivable getValueReceiver() {
        return new ValueReceivableImpl(data);
    }
}
