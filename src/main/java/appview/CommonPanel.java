package appview;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
/*
class CommonPanel implements Serializable, SwingPanel {

    private static final long serialVersionUID = 1L;
    private JPanel jPanel;

    @Override
    public SwingPanel getPanel(Visitor visitor) {
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
                return new Tabbed();
            }
        };
        return abstractPanel.order(type, visitor);
    }

    @Override
    public String getName() {
        return "common";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public LayoutManager getLayout() {
        return new GridLayout(1, 1);
    }

    @Override
    public void setParent(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JComponent getParent() {
        return jPanel;
    }
}
*/