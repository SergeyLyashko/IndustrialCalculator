package calculator;

import appview.SwingComponent;
import appview.Visitor;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculatorPanel implements Serializable, SwingComponent {

    private static final long serialVersionUID = 1L;

    // TODO del test string
    private static final String PANEL_NAME = "Калькулятор";
    private JPanel jPanel;

    // TODO del test method
    public String getName() {
        return PANEL_NAME;
    }

    public List<SwingComponent> getComponents(Visitor visitor) {
        return new ArrayList<>();
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setParentComponent(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JComponent getParentsComponent() {
        return jPanel;
    }

}
