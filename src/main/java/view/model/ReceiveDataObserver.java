package view.model;

import java.util.List;

public interface ReceiveDataObserver {

    void keyActionUpdate();

    List<String> getData();

}
