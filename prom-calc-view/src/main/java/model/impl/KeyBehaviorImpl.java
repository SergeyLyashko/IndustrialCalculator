package model.impl;

import model.KeyActionObserver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ui.UiComponent;
//import model.KeyBehavior;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
/*
@Service("keyBehavior")
@Scope("prototype")
class KeyBehaviorImpl implements KeyBehavior {

    private KeyActionObserver observer;

    @Override
    public void fieldActivate(UiComponent component) {
        JFormattedTextField textField = (JFormattedTextField) component.getComponentParent();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    notifyObservers();
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.transferFocus();
                }
            }
        });
    }

    @Override
    public void fieldDeactivate(UiComponent component) {
        JFormattedTextField textField = (JFormattedTextField) component.getComponentParent();
        Arrays.stream(textField.getKeyListeners()).forEach(textField::removeKeyListener);
    }

    private void notifyObservers() {
        if(observer != null){
            observer.keyActionUpdate();
        }
    }

    @Override
    public void registerKeyObserver(KeyActionObserver keyActionObserver) {
        this.observer = keyActionObserver;
    }
}
*/