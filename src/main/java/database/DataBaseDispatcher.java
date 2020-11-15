package database;

import viewcomponents.MenuReceiver;

public class DataBaseDispatcher {

    private Data data;

    public void connected(){
        data = new Data();
        Connector connector = new Connector();
        data.addConnection(connector);
    }

    public MenuReceiver getMenuReceiver(){
        return new MenuReceiverImpl(data);
    }
}
