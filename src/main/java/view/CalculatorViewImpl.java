package view;

import model.CalculatorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("calculatorView")
public class CalculatorViewImpl implements CalculatorView {

    private ViewController viewController;

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
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
