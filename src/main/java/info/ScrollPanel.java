package info;

import appview.SwingComponent;
import appview.Visitor;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ScrollPanel implements SwingComponent {

    private JScrollPane scrollPane;
    private JComponent componentSwing;

    @Override
    public List<SwingComponent> getComponents(Visitor visitor) {
        visitor.addVisitorComponent(this);
        List<SwingComponent> componentList = new ArrayList<>();
        InfoText infoText = new InfoText(visitor);
        createScrollPane(infoText);
        componentList.add(infoText);
        return componentList;
    }

    private void createScrollPane(InfoText infoText){
        JComponent htmlText = infoText.getSwingComponent();
        scrollPane = new JScrollPane(htmlText);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
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
    public JComponent getSwingComponent() {
        return scrollPane;
    }
/*
    @Override
    public void setComponentSwing(JComponent componentSwing) {
        this.componentSwing = componentSwing;
    }
*/
    @Override
    public String getName() {
        return "scroll";
    }
}
