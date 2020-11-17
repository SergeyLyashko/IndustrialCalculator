package database;

import view.ReceivableMenu;

public class DataBaseDispatcher {

    private final Data data;

    public DataBaseDispatcher(){
        data = new Data();
        Connector connector = new Connector();
        data.addConnection(connector);
    }

    public ReceivableMenu getMenuReceiver(){
        return new ReceivableMenuImpl(data);
    }
}
