package tabs;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class PanelBuilder implements SwingComponent {

    private JComponent jPanel;
    private LayoutManager layoutManager;
    private String borderLayout;
    private String panelName;
    private List<SwingComponent> componentList;

    SwingComponent build(Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingComponent createPanel() {
                return create();
            }
        };
        return abstractPanel.order(visitor);
    }

    private SwingComponent create() {
        return this;
    }

    @Override
    public List<SwingComponent> getComponents() {
        return componentList;
    }

    @Override
    public String getName() {
        return panelName;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public LayoutManager getLayout() {
        return layoutManager;
    }

    public String getBorderLayout(){
        return borderLayout;
    }

    @Override
    public void setParent(JComponent jPanel) {
        this.jPanel = jPanel;
    }

    public Container getParent() {
        return jPanel;
    }

    public void setName(String panelName) {
        this.panelName = panelName;
    }

    public void setComponentsList(List<SwingComponent> componentsList) {
        this.componentList = componentsList;
    }

    public void setLayout(LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void setBorderLayout(String borderLayout) {
        this.borderLayout = borderLayout;
    }
}
