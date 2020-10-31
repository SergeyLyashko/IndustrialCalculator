package appview;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class CommonPanel implements Serializable, SwingComponent {

    private static final long serialVersionUID = 1L;

    @Override
    public List<SwingComponent> getComponents(Visitor visitor) {
        List<SwingComponent> componentPanel = new ArrayList<>();
        SwingComponent panel = createPanel("", visitor);
        componentPanel.add(panel);
        return componentPanel;
    }

    private SwingComponent createPanel(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingComponent createPanel(String type, Visitor visitor) {
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
}
