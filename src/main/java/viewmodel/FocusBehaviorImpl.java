package viewmodel;

import view.AppComponent;
import viewcontroller.FocusActionObserver;
import viewcontroller.FocusBehavior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

class FocusBehaviorImpl implements FocusBehavior {

    private static final String EMPTY = "";
    private final JFormattedTextField textField;
    private final AppComponent component;
    private FocusActionObserver observer;

    FocusBehaviorImpl(AppComponent component) {
        this.component = component;
        this.textField = (JFormattedTextField) component.getParent();
    }

    @Override
    public void fieldActivate(){
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                fieldFocusGained();
                notifyObservers();
            }
            // TODO DEL
            @Override
            public void focusLost(FocusEvent e) {
                // TODO ???
                //fieldBehaviorImpl.keyDeactivate(textField);

            }
        });
    }

    @Override
    public void registerFocusObserver(FocusActionObserver observer){
        this.observer = observer;
    }

    private void notifyObservers() {
        if(observer != null){
            observer.focusActionUpdate(component);
        }
    }

    @Override
    public void fieldDeactivate() {
        Arrays.stream(textField.getFocusListeners()).forEach(textField::removeFocusListener);
    }

    private void fieldFocusGained() {
        textField.setForeground(Color.BLACK);
        textField.setText(EMPTY);
    }
}
