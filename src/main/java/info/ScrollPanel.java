package info;

import appview.AbstractPanel;
import appview.SwingComponent;
import appview.Visitor;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class ScrollPanel implements SwingComponent {

    private JScrollPane scrollPane;

    @Override
    public List<SwingComponent> getComponents(Visitor visitor) {
        List<SwingComponent> componentList = new ArrayList<>();
        SwingComponent panel = createPanel("", visitor);
        createScrollPane(panel);
        componentList.add(this);
        return componentList;
    }

    private SwingComponent createPanel(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingComponent createPanel(String type, Visitor visitor) {
                return new InfoText();
            }
        };
        return abstractPanel.order(type, visitor);
    }

    private void createScrollPane(SwingComponent panel){
        JComponent htmlText = panel.getParentsComponent();
        scrollPane = new JScrollPane(htmlText);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public JComponent getParentsComponent() {
        return scrollPane;
    }

    @Override
    public String getName() {
        return "scroll";
    }
}
