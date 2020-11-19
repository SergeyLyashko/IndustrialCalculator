package view;

import view.calculator.CalculatorFactory;
import view.info.InfoFactory;
import view.settings.SettingsFactory;
import view.staticelements.AppFrame;

public class ViewDispatcher {

    private final MenuReceivable menuReceivable;

    public ViewDispatcher(MenuReceivable menuReceivable) {
        this.menuReceivable = menuReceivable;
    }

    public void createVew(){
        Visitor visitor = new ColorVisitorImpl();


        ComponentsFactory calculator = new CalculatorFactory();
        calculator.create(menuReceivable, visitor);

        ComponentsFactory settings = new SettingsFactory();
        settings.create(menuReceivable, visitor);

        ComponentsFactory info = new InfoFactory();
        info.create(menuReceivable, visitor);

        AppFrame appFrame = new AppFrame();
        appFrame.createPanel("Калькулятор", calculator, visitor);
        appFrame.createPanel("Настройки", settings, visitor);
        appFrame.createPanel("Справка", info, visitor);

        visitor.activate();
        appFrame.create();
        appFrame.savePreferencesAndExit();
    }

}
