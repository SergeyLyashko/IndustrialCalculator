package view;

import model.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("view")
public class ViewImpl implements View {

    private ViewController viewController;

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    public ViewImpl() {
        // TODO исправить внедрение интерфейса
        //appFrame.registerWinCloseObserver(settingsComponents);
        // TODO исправить внедрение интерфейса
        //appFrame.registerWinCloseObserver(dataReceiver);
    }

    @Override
    public void messageUpdate(String message, boolean alert) {
        viewController.setMessage(message, alert);
    }

    @Override
    public void resultUpdate(String result, boolean alert) {
        viewController.setResult(result, alert);
    }
}
