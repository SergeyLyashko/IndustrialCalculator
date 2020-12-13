package view;

import controller.ViewObserver;
import calculatorcomponents.CalculatorComponentsDI;
import infocomponents.InfoComponentsDI;
import settingscomponents.SettingsComponentsDI;

class ViewDispatcherDI implements ViewObserver {

    private final ViewController viewController;

    ViewDispatcherDI(DataBaseMenuReceiver dataBaseMenuReceiver, ViewController viewController) {
        this.viewController = viewController;

        ComponentsList calculatorComponents = new CalculatorComponentsDI(viewController, dataBaseMenuReceiver);
        ComponentsList settingsComponents = new SettingsComponentsDI(viewController);
        ComponentsList infoComponents = new InfoComponentsDI(viewController);

        AppPanel calculatorPanel = new AppPanel(calculatorComponents, viewController);
        AppPanel settingsPanel = new AppPanel(settingsComponents, viewController);
        AppPanel infoPanel = new AppPanel(infoComponents, viewController);

        AppFrame appFrame = new AppFrame(calculatorPanel, settingsPanel, infoPanel);

        CalculatorFocusTraversalPolicy focusTraversalPolicy = new CalculatorFocusTraversalPolicy();
        calculatorPanel.addFocusPolicy(focusTraversalPolicy);

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
