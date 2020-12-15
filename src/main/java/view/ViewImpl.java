package view;

import model.View;
import calculatorcomponents.CalculatorComponents;
import infocomponents.InfoComponents;
import settingscomponents.SettingsComponents;

public class ViewImpl implements View {

    private final ViewController viewController;

    public ViewImpl(DataBaseMenuReceiver dataBaseMenuReceiver, ViewController viewController) {
        this.viewController = viewController;

        CalculatorComponents calculatorComponents = new CalculatorComponents(viewController, dataBaseMenuReceiver);
        SettingsComponents settingsComponents = new SettingsComponents(viewController);
        InfoComponents infoComponents = new InfoComponents(viewController);

        AppPanel calculatorPanel = new AppPanel(calculatorComponents, viewController);
        AppPanel infoPanel = new AppPanel(infoComponents, viewController);
        AppPanel settingsPanel = new AppPanel(settingsComponents, viewController);

        CalculatorFocusTraversalPolicy focusTraversalPolicy = new CalculatorFocusTraversalPolicy();
        calculatorPanel.addFocusPolicy(focusTraversalPolicy);

        AppFrame appFrame = new AppFrame(calculatorPanel, settingsPanel, infoPanel);
        appFrame.registerWinCloseObserver(settingsComponents);

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
