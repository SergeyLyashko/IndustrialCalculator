package view;

import model.ViewObserver;
import calculatorcomponents.CalculatorComponents;
import infocomponents.InfoComponentsDI;
import settingscomponents.SettingsComponents;

public class ViewDispatcherDI implements ViewObserver {

    private final ViewController viewController;

    public ViewDispatcherDI(DataBaseMenuReceiver dataBaseMenuReceiver, ViewController viewController) {
        this.viewController = viewController;

        ComponentsList calculatorComponents = new CalculatorComponents(viewController, dataBaseMenuReceiver);
        ComponentsList settingsComponents = new SettingsComponents(viewController);
        ComponentsList infoComponents = new InfoComponentsDI(viewController);

        AppPanel calculatorPanel = new AppPanel(calculatorComponents, viewController);
        AppPanel settingsPanel = new AppPanel(settingsComponents, viewController);
        AppPanel infoPanel = new AppPanel(infoComponents, viewController);

        CalculatorFocusTraversalPolicy focusTraversalPolicy = new CalculatorFocusTraversalPolicy();
        calculatorPanel.addFocusPolicy(focusTraversalPolicy);

        new AppFrame(calculatorPanel, settingsPanel, infoPanel);

        Visitor visitor = viewController.getVisitor();
        visitor.activate();
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
