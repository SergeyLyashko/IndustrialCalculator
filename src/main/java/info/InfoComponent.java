package info;

import appview.SwingPanel;
import appview.Visitor;

import java.util.ArrayList;
import java.util.List;

public class InfoComponent {

    public SwingPanel getComponent(Visitor visitor){
        return new ScrollPanel();
    }

    /*
    private final List<SwingPanel> components = new ArrayList<>();

    public List<SwingPanel> getComponents(Visitor visitor){
        ScrollPanel scrollPanel = new ScrollPanel();
        components.add(scrollPanel);
        return components;
    }*/
}
