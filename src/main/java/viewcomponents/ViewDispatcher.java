package viewcomponents;

import viewcomponents.checkboxes.ColorThemeCheckBox;
import viewcomponents.checkboxes.ComplexAreaCheckBox;
import viewcomponents.checkboxes.ToolTipsCheckBox;
import viewcomponents.fields.Length;
import viewcomponents.fields.Width;
import viewcomponents.menuboxes.AssortmentsMenu;
import viewcomponents.menuboxes.NumbersMenu;
import viewcomponents.menuboxes.TypesMenu;
import viewcomponents.staticelements.ApplicationView;
import viewcomponents.staticelements.Info;
import viewcomponents.textlabels.DimensionLabel;
import viewcomponents.textlabels.Message;
import viewcomponents.textlabels.Result;

public class ViewDispatcher {

    public void createVew(){
        Visitor visitor = new VisitorImpl();

        FactoryComponents calculatorComponents = new FactoryComponents();
        calculatorComponents.addComponent(new ComplexAreaCheckBox(), visitor);
        calculatorComponents.addComponent(new AssortmentsMenu(), visitor);
        calculatorComponents.addComponent(new TypesMenu(), visitor);
        calculatorComponents.addComponent(new NumbersMenu(), visitor);
        calculatorComponents.addComponent(new Length(), visitor);
        calculatorComponents.addComponent(new Width(), visitor);
        calculatorComponents.addComponent(new Result(), visitor);
        calculatorComponents.addComponent(new Message(), visitor);
        calculatorComponents.addComponent(new DimensionLabel(320, 22), visitor);
        calculatorComponents.addComponent(new DimensionLabel(320, 62), visitor);

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
