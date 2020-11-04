package info;

import appview.AbstractPanel;
import appview.SwingPanel;
import appview.Visitor;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
/*
public class InfoPanel implements Serializable, SwingPanel {

    private static final long serialVersionUID = 1L;

    // TODO del test string
    private static final String PANEL_NAME = "Справка";
    private JPanel jPanel;

    // TODO del test method
    public String getName() {
        return PANEL_NAME;
    }


    public SwingPanel getPanel(Visitor visitor){
        return createPanel("", visitor);
    }

    @Override
    public List<SwingPanel> getComponents() {
        return null;
    }

    private SwingPanel createPanel(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingPanel createPanel(String type, Visitor visitor) {
                return new ScrollPanel();
            }
        };
        return abstractPanel.order(type, visitor);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public LayoutManager getLayout() {
        return new BorderLayout();
    }

    public String getBorderLayout(){
        return BorderLayout.CENTER;
    }

    @Override
    public void setParentComponent(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JComponent getParentsComponent() {
        return jPanel;
    }

}*/
