package appview;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Tabbed implements SwingComponent {

    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    void addTab(String type, JPanel panel) {
        tabbedPane.addTab(type, panel);
    }

    @Override
    public String getName() {
        return "tabbed pane";
    }

    // TODO не используется
    @Override
    public List<SwingComponent> getComponents(Visitor visitor) {
        return new ArrayList<>();
    }

    // TODO не используется
    @Override
    public void acceptVisitor(Visitor visitor) {
    }

    @Override
    public JComponent getParentsComponent() {
        return tabbedPane;
    }
}
