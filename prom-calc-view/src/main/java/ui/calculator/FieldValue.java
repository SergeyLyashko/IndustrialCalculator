package ui.calculator;

import controller.FieldsAction;
import controller.ViewController;
import lombok.Getter;
import model.KeyActionObserver;
import org.springframework.stereotype.Component;
import ui.UiComponent;

import javax.swing.*;

@Component
public class FieldValue extends JFormattedTextField implements UiComponent, Comparable<UiComponent> {

    private static final int WIDTH_SIZE = 125;
    private static final int HEIGHT_SIZE = 23;
    private final FocusRate focusRate;
    private final String fieldTitle;

    public FieldValue(String fieldDescription, String toolTipText, int locationX, int locationY, FocusRate focusRate) {
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

    @Override
    public boolean isTraversalPolicyFocused() {
        return true;
    }

    @Override
    public int compareTo(UiComponent component) {
        return focusRate.getRate() - component.getFocusRate();
    }

    private void addKeyActionUpdate(KeyActionObserver keyActionObserver) {
        keyActionObserver.keyActionUpdate();
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
        abstract void setActionComponent(FieldValue fieldValue, ViewController viewController, FieldsAction fieldsAction);
    }
}
