package viewmodel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import viewcomponents.common.AppComponent;
import viewcontroller.FocusBehavior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

@Service("focusBehavior")
@Scope("prototype")
class FocusBehaviorImpl implements FocusBehavior {

    private static final String EMPTY = "";
    private JFormattedTextField textField;
    private FocusActionObserver observer;

    @Override
    public void setComponent(AppComponent component){
        this.textField = (JFormattedTextField) component.getComponentParent();
    }

    @Override
    public void fieldActivate(){
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                fieldFocusGained();
                notifyObservers();
            }
        });
    }

    @Override
    public void registerFocusObserver(FocusActionObserver observer){
        this.observer = observer;
    }

    private void notifyObservers() {
        if(observer != null){
            observer.focusActionUpdate();
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
