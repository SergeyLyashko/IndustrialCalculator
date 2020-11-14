import appcomponents.*;
import appcomponents.FactoryComponents;
import checkboxes.ColorThemeCheckBox;
import checkboxes.ComplexAreaCheckBox;
import checkboxes.ToolTipsCheckBox;
import menuboxes.AssortmentsMenu;
import menuboxes.NumbersMenu;
import menuboxes.TypesMenu;
import fields.Length;
import fields.Width;
import appcomponents.staticelements.Info;
import appcomponents.staticelements.ApplicationView;

public class Main {
    public static void main(String[] args){

        Visitor visitor = new VisitorImpl();

        FactoryComponents calculatorComponents = new FactoryComponents();
        calculatorComponents.addComponent(new ComplexAreaCheckBox(), visitor);
        calculatorComponents.addComponent(new AssortmentsMenu(), visitor);
        calculatorComponents.addComponent(new TypesMenu(), visitor);
        calculatorComponents.addComponent(new NumbersMenu(), visitor);
        calculatorComponents.addComponent(new Length(), visitor);
        calculatorComponents.addComponent(new Width(), visitor);

        FactoryComponents settingsComponents = new FactoryComponents();
        settingsComponents.addComponent(new ColorThemeCheckBox(), visitor);
        settingsComponents.addComponent(new ToolTipsCheckBox(), visitor);

        FactoryComponents infoComponents = new FactoryComponents();
        infoComponents.addComponent(new Info(), visitor);

        ApplicationView applicationView = new ApplicationView();
        applicationView.createPanel("Калькулятор", calculatorComponents, visitor);
        applicationView.createPanel("Настройки", settingsComponents, visitor);
        applicationView.createPanel("Справка", infoComponents, visitor);

        applicationView.createView();
        applicationView.savePreferencesAndExit();
    }
}
