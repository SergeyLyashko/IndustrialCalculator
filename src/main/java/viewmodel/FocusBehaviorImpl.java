package viewmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import viewcomponents.common.AppComponent;
import viewcontroller.Filter;
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
    private Filter digitalFilter;
    private AppComponent component;

    @Autowired
    @Qualifier("digitalFilter")
    public void setDigitalFilter(Filter digitalFilter){
        this.digitalFilter = digitalFilter;
    }

    @Override
    public void setComponent(AppComponent component){
        this.component = component;
        this.textField = (JFormattedTextField) component.getComponentParent();
    }

    @Override
    public void fieldActivate(){
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                digitalFilter.activateFilter(component);
                fieldFocusGained();
                notifyObservers();
            }
        });
    }

    // TODO
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
