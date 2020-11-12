package fields;

import appcomponents.SelectableComponent;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class FieldState implements FocusListener, KeyListener {

    private final SelectableComponent selectableField;
    private final Visitor visitor;

    FieldState(SwingComponent selectableField, Visitor visitor) {
        this.selectableField = (SelectableComponent) selectableField;
        this.visitor = visitor;
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            selectableField.activate(visitor);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            selectableField.deactivate(visitor);
        }
    }
}
