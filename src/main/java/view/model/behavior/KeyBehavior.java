package view.model.behavior;

import view.model.KeyActionObserver;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KeyBehavior implements KeyActionSubject {

    private final List<KeyActionObserver> observer = new ArrayList<>();

    void fieldActivate(JTextField parent) {
        parent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                pressed(event);
            }

            @Override
            public void keyReleased(KeyEvent event) {
                released(event);
            }
        });
    }

    private void pressed(KeyEvent event){
        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            //JTextField source = (JFormattedTextField) event.getSource();
            //String text = source.getText();
            //System.out.println("test press: "+text);
            notifyObservers();
        }
    }

    private void released(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            JTextField source = (JFormattedTextField) event.getSource();
            source.transferFocus();
        }
    }

    void fieldDeactivate(JTextField parent) {
        Arrays.stream(parent.getKeyListeners()).forEach(parent::removeKeyListener);
    }

    @Override
    public void notifyObservers() {
        observer.forEach(KeyActionObserver::keyActionUpdate);
    }

    @Override
    public void registerObserver(KeyActionObserver keyActionObserver) {
        observer.add(keyActionObserver);
    }
}
