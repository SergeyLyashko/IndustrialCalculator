package appview;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ContainerBuilder implements SwingContainer {

    private Container container;

    public SwingContainer build(String type, Visitor visitor){
        AbstractContainer abstractContainer = new AbstractContainer() {
            @Override
            public SwingContainer create() {
                return createContainer();
            }

            @Override
            public List<SwingPanel> createContentList(String type) {
                return getPanelsList(visitor);
            }
        };
        return abstractContainer.order(type);
    }

    private SwingContainer createContainer() {
        container = new JTabbedPane(JTabbedPane.TOP);
        return this;
    }

    private List<SwingPanel> getPanelsList(Visitor visitor) {
        List<SwingPanel> panelList = new ArrayList<>();
        SwingPanel calc = createNewPanel("Калькулятор", visitor);
        SwingPanel settings = createNewPanel("Настройки", visitor);
        SwingPanel info = createNewPanel("Справка", visitor);
        panelList.add(calc);
        panelList.add(settings);
        panelList.add(info);
        return panelList;
    }

    @Override
    public Container getParent() {
        return container;
    }

    private SwingPanel createNewPanel(String type, Visitor visitor) {
        PanelBuilder panelBuilder = new PanelBuilder();
        switch (type){
            case "Калькулятор":
                return panelBuilder.build("Калькулятор", visitor);
            case "Настройки":
                return panelBuilder.build("Настройки", visitor);
            case "Справка":
                return panelBuilder.build("Справка", visitor);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
