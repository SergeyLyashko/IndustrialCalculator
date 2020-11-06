package appview;

import info.InfoText;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ContainerBuilder implements SwingComponent {

    private Container container;

    public SwingComponent build(String type, Visitor visitor){
        AbstractContainer abstractContainer = new AbstractContainer() {
            @Override
            public List<SwingComponent> createContentList(String type) {
                return getPanelsList(type, visitor);
            }

            @Override
            public SwingComponent create(List<SwingComponent> contentList) {
                return createContainer(type, contentList);
            }
        };
        return abstractContainer.order(type);
    }

    private SwingComponent createContainer(String type, List<SwingComponent> contentList) throws IllegalStateException {
        switch (type){
            case "Tab":
                return createTabPane(contentList);
            case "Scroll":
                return createScrollPane(contentList);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private SwingComponent createScrollPane(List<SwingComponent> contentList){
        JScrollPane scrollPane = new JScrollPane();
        JViewport viewport = scrollPane.getViewport();
        contentList.forEach(content -> viewport.add(content.getParent()));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        container = scrollPane;
        return this;
    }

    private List<SwingComponent> getScrollContentList(Visitor visitor) {
        List<SwingComponent> contentList = new ArrayList<>();
        InfoText infoText = new InfoText();
        contentList.add(infoText);
        return contentList;
    }

    private SwingComponent createTabPane(List<SwingComponent> contentList){
        JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentList.forEach(content -> jTabbedPane.add(content.getName(), content.getParent()));
        container = jTabbedPane;
        return this;
    }

    private List<SwingComponent> getTabPanelsList(Visitor visitor){
        List<SwingComponent> panelList = new ArrayList<>();
        SwingComponent calc = createContent("Калькулятор", visitor);
        SwingComponent settings = createContent("Настройки", visitor);
        SwingComponent info = createContent("Справка", visitor);
        panelList.add(calc);
        panelList.add(settings);
        panelList.add(info);
        return panelList;
    }

    private List<SwingComponent> getPanelsList(String type, Visitor visitor) throws IllegalStateException {
        switch (type){
            case "Tab":
                return getTabPanelsList(visitor);
            case "Scroll":
                return getScrollContentList(visitor);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }



    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<SwingComponent> getComponents() {
        return null;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.addVisitorComponent(this);
    }

    @Override
    public LayoutManager getLayout() {
        return null;
    }

    @Override
    public String getBorderLayout() {
        return null;
    }

    @Override
    public Container getParent() {
        return container;
    }

    @Override
    public void setParent(JComponent jPanel) {

    }

    private SwingComponent createContent(String type, Visitor visitor) throws IllegalStateException {
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
