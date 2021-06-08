package ui.impl;

import controller.FieldsAction;
import controller.ViewController;
import lombok.Getter;
import model.KeyActionObserver;
import org.springframework.stereotype.Component;
import ui.FocusPolicy;
import ui.UiComponent;

import javax.swing.*;

/**
 * Parameters details value input fields
 * @author Sergey Lyashko
 */
@Component
class FieldValue extends JFormattedTextField implements UiComponent, FocusPolicy {

    private static final int WIDTH_SIZE = 125;
    private static final int HEIGHT_SIZE = 23;
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
    public JComponent getComponentParent() {
        return this;
    }

    private void addKeyActionUpdate(KeyActionObserver keyActionObserver) {
        keyActionObserver.keyActionUpdate();
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
            void setActionComponent(FieldValue fieldValue, ViewController viewController, FieldsAction fieldsAction) {
                fieldsAction.setComponent(fieldValue);
            }
        },
        LENGTH{
            @Override
            void setActionComponent(FieldValue fieldValue, ViewController viewController, FieldsAction fieldsAction) {
                fieldsAction.setComponent(fieldValue);
                fieldValue.addKeyActionUpdate(viewController::keyActionUpdate);
            }
        };

        /**
         * Add component for action in fields
         * @param fieldValue fields for action
         * @param viewController
         * @param fieldsAction
         */
        abstract void setActionComponent(FieldValue fieldValue, ViewController viewController, FieldsAction fieldsAction);
    }
}
