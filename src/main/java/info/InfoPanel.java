package info;

import appview.AbstractPanel;
import appview.SwingComponent;
import appview.Visitor;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InfoPanel implements Serializable, SwingComponent {

    private static final long serialVersionUID = 1L;

    // TODO del test string
    private static final String PANEL_NAME = "Справка";
    // TODO del test method
    public String getName() {
        return PANEL_NAME;
    }


    public List<SwingComponent> getComponents(Visitor visitor){
        List<SwingComponent> componentList = new ArrayList<>();
        SwingComponent panel = createPanel("", visitor);
        componentList.add(panel);
        return componentList;
    }

    private SwingComponent createPanel(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingComponent createPanel(String type, Visitor visitor) {
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

}
