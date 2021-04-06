package view;

import model.View;
import org.springframework.beans.factory.annotation.Autowired;
import viewcomponents.calculator.CalculatorComponents;
import viewcomponents.info.InfoComponents;
import viewcomponents.settings.SettingsComponents;

public class ViewImpl implements View {

    private final ViewController viewController;
    //private DataReceiver dataReceiver;
    /*
    @Autowired
    public void setDataReceiver(DataReceiver dataReceiver){
        this.dataReceiver = dataReceiver;
    }*/

    public ViewImpl(/*DataReceiver dataReceiver, */ViewController viewController) {
        this.viewController = viewController;

        CalculatorComponents calculatorComponents = new CalculatorComponents(viewController/*, dataReceiver*/);
        SettingsComponents settingsComponents = new SettingsComponents(viewController);
        InfoComponents infoComponents = new InfoComponents(viewController);

        AppPanel calculatorPanel = new AppPanel(calculatorComponents, viewController);
        AppPanel infoPanel = new AppPanel(infoComponents, viewController);
        AppPanel settingsPanel = new AppPanel(settingsComponents, viewController);

        CalculatorFocusTraversalPolicy focusTraversalPolicy = new CalculatorFocusTraversalPolicy();
        calculatorPanel.addFocusPolicy(focusTraversalPolicy);

        AppFrame appFrame = new AppFrame(calculatorPanel, settingsPanel, infoPanel);
        appFrame.registerWinCloseObserver(settingsComponents);
        // TODO исправить внедрение интерфейса
        //appFrame.registerWinCloseObserver(dataReceiver);

        Visitor visitor = viewController.getVisitor();
        visitor.raid();
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
