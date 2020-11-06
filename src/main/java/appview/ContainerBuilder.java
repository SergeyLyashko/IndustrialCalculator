package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ContainerBuilder implements SwingComponent {

    private Container container;
    private List<SwingComponent> componentList;

    public SwingComponent build(String type, Visitor visitor){
        AbstractContainer abstractContainer = new AbstractContainer() {
            @Override
            public List<SwingComponent> createContentList(String type) {
                return getContentsList(type, visitor);
            }

            @Override
            public SwingComponent createContainer(List<SwingComponent> contentList) {
                return create(type, contentList);
            }
        };

        return abstractContainer.order(type);
    }

    private SwingComponent create(String type, List<SwingComponent> contentList) throws IllegalStateException {
        switch (type){
            case "Tab":
                return createTabContainer(contentList);
            case "Scroll":
                return createScrollContainer(contentList);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private SwingComponent createScrollContainer(List<SwingComponent> contentList){
        JScrollPane scrollPane = new JScrollPane();
        JViewport viewport = scrollPane.getViewport();
        contentList.forEach(content -> viewport.add(content.getParent()));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        container = scrollPane;
        return this;
    }

    private SwingComponent createTabContainer(List<SwingComponent> contentList){
        JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentList.forEach(content -> jTabbedPane.add(content.getName(), content.getParent()));
        container = jTabbedPane;
        return this;
    }

    private List<SwingComponent> getContentsList(String type, Visitor visitor) throws IllegalStateException {
        switch (type){
            case "Tab":
                PanelFactory panelFactory = new PanelFactory();
                return panelFactory.getPanelsList(visitor);
            case "Scroll":
                // TODO Переписать !
                return componentList;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public void setComponents(List<SwingComponent> componentList) {
        this.componentList = componentList;
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
}
