package view;

import view.calculator.CalculatorFactory;
import view.info.InfoFactory;
import view.settings.SettingsFactory;
import view.staticelements.AppFrame;

public class ViewDispatcher {

    private final MenuReceiver menuReceiver;

    public ViewDispatcher(MenuReceiver menuReceiver) {
        this.menuReceiver = menuReceiver;
    }

    public void createVew(){
        Visitor visitor = new VisitorImpl();

        ComponentsFactory calculator = new CalculatorFactory();
        calculator.create(menuReceiver, visitor);

        ComponentsFactory settings = new SettingsFactory();
        settings.create(menuReceiver, visitor);

        ComponentsFactory info = new InfoFactory();
        info.create(menuReceiver, visitor);

        AppFrame appFrame = new AppFrame();
        appFrame.createPanel("Калькулятор", calculator, visitor);
        appFrame.createPanel("Настройки", settings, visitor);
        appFrame.createPanel("Справка", info, visitor);

        appFrame.create();
        appFrame.savePreferencesAndExit();
    }

}
