package info;

import appview.SwingComponent;
import appview.Visitor;
import javax.swing.*;
import java.util.List;

class ScrollPanel implements SwingComponent {

    private JScrollPane scrollPane;

    // TODO перенести в getComponents
    public SwingComponent getComponent(Visitor visitor) {
        createScrollPane(new InfoText());
        return this;
    }

    @Override
    public List<SwingComponent> getComponents() {
        return null;
    }

    private void createScrollPane(SwingComponent panel){
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
