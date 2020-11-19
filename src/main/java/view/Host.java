package view;

import javax.swing.*;

public interface Host extends AppComponent{

    void acceptVisitor(Visitor visitor);

    default JComponent getScrollViewPort(){ return null; }
}
