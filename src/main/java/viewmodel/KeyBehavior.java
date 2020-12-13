package viewmodel;

import viewcontroller.KeyActionObserver;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

class KeyBehavior {

    private KeyActionObserver observer;

    void fieldActivate(JTextField parent) {
        parent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    notifyObservers();
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    JTextField source = (JFormattedTextField) event.getSource();
                    source.transferFocus();
                }
            }
        });
    }

    void fieldDeactivate(JTextField parent) {
        Arrays.stream(parent.getKeyListeners()).forEach(parent::removeKeyListener);
    }

    private void notifyObservers() {
        if(observer != null){
            observer.keyActionUpdate();
        }
    }

    void registerObserver(KeyActionObserver keyActionObserver) {
        this.observer = keyActionObserver;
    }
}
