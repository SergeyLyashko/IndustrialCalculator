import appcomponents.*;
import appcomponents.ComponentsCollector;
import checkboxes.ColorThemeCheckBox;
import checkboxes.ComplexAreaCheckBox;
import checkboxes.ToolTipsCheckBox;
import comboboxes.AssortmentsBox;
import comboboxes.NumbersBox;
import comboboxes.TypesBox;
import fields.Length;
import fields.Width;
import infocomponents.InfoText;
import tabs.ApplicationView;

public class Main {
    public static void main(String[] args){

        Visitor visitor = new VisitorImpl();

        ComponentsCollector calculatorComponents = new ComponentsCollector();
        calculatorComponents.addComponent(new ComplexAreaCheckBox(), visitor);
        calculatorComponents.addComponent(new AssortmentsBox(), visitor);
        calculatorComponents.addComponent(new TypesBox(), visitor);
        calculatorComponents.addComponent(new NumbersBox(), visitor);
        calculatorComponents.addComponent(new Length(), visitor);
        calculatorComponents.addComponent(new Width(), visitor);

        ComponentsCollector settingsComponents = new ComponentsCollector();
        settingsComponents.addComponent(new ColorThemeCheckBox(), visitor);
        settingsComponents.addComponent(new ToolTipsCheckBox(), visitor);

        ComponentsCollector infoComponents = new ComponentsCollector();
        infoComponents.addComponent(new InfoText(), visitor);

        ApplicationView applicationView = new ApplicationView();
        applicationView.createPanel("Калькулятор", calculatorComponents, visitor);
        applicationView.createPanel("Настройки", settingsComponents, visitor);
        applicationView.createPanel("Справка", infoComponents, visitor);

        applicationView.createView();
        applicationView.savePreferencesAndExit();
    }
}
