package appview;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Tabbed implements SwingComponent {

    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    @Override
    public String getName() {
        return "tabbed pane";
    }

    // TODO не используется
    @Override
    public List<SwingComponent> getComponents(Visitor visitor) {
        return new ArrayList<>();
    }

    void addTab(String type, JPanel panel) {
        tabbedPane.addTab(type, panel);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public JComponent getParentsComponent() {
        return tabbedPane;
    }
}
