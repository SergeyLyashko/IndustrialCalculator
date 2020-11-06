package appview;

import java.awt.*;
import java.util.List;

public abstract class AbstractContainer {

    public abstract List<SwingComponent> createContentList(String type);
    public abstract SwingComponent createContainer(List<SwingComponent> contentList);

    public SwingComponent order(String type){
        List<SwingComponent> contentList = createContentList(type);
        SwingComponent container = createContainer(contentList);
        //addPanelsTo(container, contentList);
        return container;
    }
/*
    private void addPanelsTo(SwingComponent swingContainer, List<SwingComponent> componentList) {
        componentList.forEach(component -> {
            String name = component.getName();
            Container componentParent = component.getParent();
            Container containerParent = swingContainer.getParent();
            containerParent.add(name, componentParent);
        });
    }

 */
}
