package view.calculator.fields;

import view.AppComponent;
import view.Visitor;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class FieldBehavior implements FocusListener, KeyListener {

    private final Visitor visitor;
    private final AppComponent component;

    FieldBehavior(AppComponent component, Visitor visitor) {
        this.component = component;
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
            JTextField parent = (JFormattedTextField) component.getParent();
            parent.getText();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            JComponent parent = component.getParent();
            parent.transferFocus();
        }
    }
}
