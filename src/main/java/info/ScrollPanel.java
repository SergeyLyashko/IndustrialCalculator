package info;

import appview.AbstractPanel;
import appview.SwingPanel;
import appview.Visitor;
import javax.swing.*;
import java.util.List;

public class ScrollPanel implements SwingPanel {

    private JScrollPane scrollPane;

    @Override
    public SwingPanel getPanel(Visitor visitor) {
        SwingPanel panel = createPanel("", visitor);
        createScrollPane(panel);
        return this;
    }

    @Override
    public List<SwingPanel> getComponents() {
        return null;
    }

    private SwingPanel createPanel(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingPanel createPanel(String type, Visitor visitor) {
                return new InfoText();
            }
        };
        return abstractPanel.order(type, visitor);
    }

    private void createScrollPane(SwingPanel panel){
        JComponent htmlText = panel.getParent();
        scrollPane = new JScrollPane(htmlText);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public JComponent getParent() {
        return scrollPane;
    }

    @Override
    public String getName() {
        return "scroll";
    }
}
