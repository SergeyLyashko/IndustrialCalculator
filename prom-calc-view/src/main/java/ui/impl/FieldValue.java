package ui.impl;

import controller.FieldAction;
import controller.ViewController;
import lombok.Getter;
import model.DataManager;
import model.KeyActionObserver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.FocusPolicy;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Parameters details value input fields
 * @author Sergey Lyashko
 */
@Component
@Scope("prototype")
class FieldValue extends JFormattedTextField implements FocusPolicy {

    private static final int WIDTH_SIZE = 125;
    private static final int HEIGHT_SIZE = 23;
    private static final String EMPTY = "";
    private final FocusRate focusRate;
    private final String fieldTitle;

    /**
     * Constructor for create value field
     * @param fieldDescription inside field text description
     * @param toolTipText tooltip this field
     * @param locationX X-location on UI panel
     * @param locationY Y-location on UI panel
     * @param focusRate rate for focus traversal policy on panel
     */
    FieldValue(String fieldDescription, String toolTipText, int locationX, int locationY, FocusRate focusRate) {
        super.setSize(WIDTH_SIZE, HEIGHT_SIZE);
        super.setEditable(false);
        super.setText(fieldDescription);
        super.setToolTipText(toolTipText);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        super.setLocation(locationX, locationY);
        this.fieldTitle = fieldDescription;
        this.focusRate = focusRate;
    }

    @Override
    public String getName() {
        return fieldTitle;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public int getFocusRate() {
        return focusRate.getRate();
    }

    enum FocusRate{
        FOURTH(4),
        FIFTH(5);

        @Getter
        private final int rate;

        FocusRate(int rate) {
            this.rate = rate;
        }
    }

    enum Type {
        WIDTH{
            @Override
            void setActionComponent(FieldValue fieldValue, ViewController viewController, FieldAction fieldsAction) {
                fieldsAction.setComponent(fieldValue);
            }

            @Override
            void fieldActivate(FieldValue fieldValue, DataManager data, KeyActionObserver observer) {
                fieldValue.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent event) {
                        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                            String textValue = fieldValue.getText();
                            if(textValue.equals(fieldValue.getName())) {
                                textValue = EMPTY;
                            }
                            data.setWidthData(textValue);
                            fieldValue.transferFocus();
                        }
                    }
                });
            }
        },
        LENGTH{
            @Override
            void setActionComponent(FieldValue fieldValue, ViewController viewController, FieldAction fieldsAction) {
                fieldsAction.setComponent(fieldValue);
            }

            @Override
            void fieldActivate(FieldValue fieldValue, DataManager data, KeyActionObserver observer) {
                fieldValue.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent event) {
                        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                            String textValue = fieldValue.getText();
                            if(textValue.equals(fieldValue.getName())) {
                                textValue = EMPTY;
                            }
                            data.setLengthData(textValue);
                            observer.keyActionUpdate();
                            fieldValue.transferFocus();
                        }
                    }
                });
            }
        };

        /**
         * Add component for action in fields
         * @param fieldValue fields for action
         * @param viewController
         * @param fieldsAction
         */
        abstract void setActionComponent(FieldValue fieldValue, ViewController viewController, FieldAction fieldsAction);

        abstract void fieldActivate(FieldValue fieldValue, DataManager dataManager, KeyActionObserver observer);
    }
}
