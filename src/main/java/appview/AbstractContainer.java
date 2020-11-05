package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AbstractContainer {

    public abstract SwingContainer create();
    public abstract List<SwingPanel> createContentList(String type);

    public SwingContainer order(String type){
        SwingContainer swingContainer = create();
        List<SwingPanel> panelList = createContentList(type);
        addPanelsTo(swingContainer, panelList);
        return swingContainer;
    }

    private void addPanelsTo(SwingContainer swingContainer, List<SwingPanel> panelList) {
        panelList.forEach(panel -> {
            String name = panel.getName();
            JComponent panelParent = panel.getParent();
            Container containerParent = swingContainer.getParent();
            containerParent.add(name, panelParent);
        });
    }
}
