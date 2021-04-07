package viewmodel;

import view.AppComponent;
import viewcontroller.KeyBehavior;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

class KeyBehaviorImpl implements KeyBehavior {

    private final JFormattedTextField textField;
    private KeyActionObserver observer;

    KeyBehaviorImpl(AppComponent component) {
        this.textField = (JFormattedTextField) component.getComponentParent();
    }

    @Override
    public void fieldActivate() {
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
    public void fieldDeactivate() {
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
