package info;

import appview.ContainerBuilder;
import appview.SwingComponent;
import appview.Visitor;

import java.util.ArrayList;
import java.util.List;

public class InfoComponents {

    public SwingComponent getComponents(Visitor visitor){
        List<SwingComponent> scrollContentList = getScrollContentList(visitor);
        ContainerBuilder containerBuilder = new ContainerBuilder();
        containerBuilder.setComponents(scrollContentList);
        return containerBuilder.build("Scroll", visitor);
    }

    private List<SwingComponent> getScrollContentList(Visitor visitor) {
        List<SwingComponent> contentList = new ArrayList<>();
        InfoText infoText = new InfoText();
        contentList.add(infoText);
        return contentList;
    }
}
