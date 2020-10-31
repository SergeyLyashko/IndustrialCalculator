package calculator;

import appview.SwingComponent;
import appview.Visitor;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculatorPanel implements Serializable, SwingComponent {

    private static final long serialVersionUID = 1L;

    // TODO del test string
    private static final String PANEL_NAME = "Калькулятор";
    // TODO del test method
    public String getName() {
        return PANEL_NAME;
    }

    private JComponent componentSwing;

    public List<SwingComponent> getComponents(Visitor visitor) {
        visitor.addVisitorComponent(this);
        return new ArrayList<>();
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public LayoutManager getLayout() {
        return null;
    }

    @Override
    public String getBorderLayout() {
        return null;
    }

    // TODO не используется
    @Override
    public JComponent getSwingComponent() {
        return componentSwing;
    }
}
