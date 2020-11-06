package appview;

import java.awt.*;
import java.util.List;

public abstract class AbstractContainer {

    public abstract SwingComponent create(List<SwingComponent> contentList);
    public abstract List<SwingComponent> createContentList(String type);

    public SwingComponent order(String type){
        List<SwingComponent> contentList = createContentList(type);
        SwingComponent container = create(contentList);
        //addPanelsTo(container, contentList);
        return container;
    }

    private void addPanelsTo(SwingComponent swingContainer, List<SwingComponent> componentList) {
        componentList.forEach(component -> {
            String name = component.getName();
            Container componentParent = component.getParent();
            Container containerParent = swingContainer.getParent();
            containerParent.add(name, componentParent);
        });
    }
}
