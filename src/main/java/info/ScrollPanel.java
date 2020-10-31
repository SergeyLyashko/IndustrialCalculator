package info;

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
        // TODO создается без AbstractPanel
        InfoText infoText = new InfoText(visitor);
        createScrollPane(infoText);
        componentList.add(this);
        return componentList;
    }

    private void createScrollPane(InfoText infoText){
        JComponent htmlText = infoText.getParentsComponent();
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
