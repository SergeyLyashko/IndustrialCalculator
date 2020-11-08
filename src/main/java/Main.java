import appcomponents.*;
import appcomponents.CalculatorComponents;
import checkboxes.ColorTheme;
import checkboxes.ComplexArea;
import checkboxes.ToolTip;
import comboboxes.AssortmentsMenu;
import comboboxes.NumbersMenu;
import comboboxes.TypesMenu;
import infocomponents.InfoText;
import tabs.ApplicationView;
import infocomponents.InfoComponents;

public class Main {
    public static void main(String[] args){

        Visitor visitor = new VisitorImpl();

        CalculatorComponents calculatorComponents = new CalculatorComponents();
        calculatorComponents.addComponent(new ComplexArea(), visitor);
        calculatorComponents.addComponent(new AssortmentsMenu());
        calculatorComponents.addComponent(new TypesMenu());
        calculatorComponents.addComponent(new NumbersMenu());

        SettingsComponents settingsComponents = new SettingsComponents();
        settingsComponents.addComponent(new ColorTheme(), visitor);
        settingsComponents.addComponent(new ToolTip(), visitor);

        InfoComponents infoComponents = new InfoComponents();
        infoComponents.addComponent(new InfoText(), visitor);

        ApplicationView applicationView = new ApplicationView();
        applicationView.setCalculatorComponents(calculatorComponents, visitor);
        applicationView.setSettingsComponents(settingsComponents, visitor);
        applicationView.setInfoComponents(infoComponents, visitor);

        applicationView.createView();
        applicationView.savePreferencesAndExit();
    }
}
