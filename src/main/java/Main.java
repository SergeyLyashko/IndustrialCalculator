import appcomponents.*;
import calculatorcomponents.CalculatorComponents;
import tabs.ApplicationView;
import checkboxes.CheckBoxFactory;
import infocomponents.InfoComponents;

public class Main {
    public static void main(String[] args){

        Visitor visitor = new VisitorImpl();

        CalculatorComponents calculatorComponents = new CalculatorComponents();
        calculatorComponents.addComponent(visitor);

        CheckBoxFactory checkBoxFactory = new CheckBoxFactory();
        checkBoxFactory.addComponent(new ColorTheme(), visitor);
        checkBoxFactory.addComponent(new ToolTip(), visitor);

        InfoComponents infoComponents = new InfoComponents();
        infoComponents.addComponent(new InfoText(), visitor);

        ApplicationView applicationView = new ApplicationView();
        applicationView.setCalculatorComponents(calculatorComponents, visitor);
        applicationView.setSettingsComponents(checkBoxFactory, visitor);
        applicationView.setInfoComponents(infoComponents, visitor);

        applicationView.createView();

        applicationView.savePreferencesAndExit();
    }
}
