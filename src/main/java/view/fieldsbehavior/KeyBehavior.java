package view.fieldsbehavior;

import view.viewmodel.KeyActionObserver;
import view.viewmodel.KeyActionSubject;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

class KeyBehavior implements KeyActionSubject {

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

    @Override
    public void notifyObservers() {
        if(observer != null){
            observer.keyActionUpdate();
        }
    }

    @Override
    public void registerObserver(KeyActionObserver keyActionObserver) {
        this.observer = keyActionObserver;
    }
}
