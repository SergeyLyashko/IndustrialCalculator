import appcomponents.*;
import appcomponents.ComponentsCollector;
import checkboxes.ColorTheme;
import checkboxes.ComplexArea;
import checkboxes.ToolTip;
import comboboxes.AssortmentsMenu;
import comboboxes.NumbersMenu;
import comboboxes.TypesMenu;
import fields.Length;
import fields.Width;
import infocomponents.InfoText;
import tabs.ApplicationView;

public class Main {
    public static void main(String[] args){

        Visitor visitor = new VisitorImpl();

        ComponentsCollector calculatorComponents = new ComponentsCollector();
        calculatorComponents.addComponent(new ComplexArea(), visitor);
        calculatorComponents.addComponent(new AssortmentsMenu(), visitor);
        calculatorComponents.addComponent(new TypesMenu(), visitor);
        calculatorComponents.addComponent(new NumbersMenu(), visitor);
        calculatorComponents.addComponent(new Length(), visitor);
        calculatorComponents.addComponent(new Width(), visitor);

        ComponentsCollector settingsComponents = new ComponentsCollector();
        settingsComponents.addComponent(new ColorTheme(), visitor);
        settingsComponents.addComponent(new ToolTip(), visitor);

        ComponentsCollector infoComponents = new ComponentsCollector();
        infoComponents.addComponent(new InfoText(), visitor);

        ApplicationView applicationView = new ApplicationView();
        applicationView.setCalculatorComponents(calculatorComponents, visitor);
        applicationView.setSettingsComponents(settingsComponents, visitor);
        applicationView.setInfoComponents(infoComponents, visitor);

        applicationView.createView();
        applicationView.savePreferencesAndExit();
    }
}
