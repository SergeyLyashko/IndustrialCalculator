package infocomponents;

import appcomponents.Factory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

class InfoFactory implements Factory {

    public SwingComponent createNewComponent(SwingComponent swingComponent, Visitor visitor) {
        Scroll scroll = new Scroll();
        return scroll.createContainer(swingComponent, visitor);
    }
}
