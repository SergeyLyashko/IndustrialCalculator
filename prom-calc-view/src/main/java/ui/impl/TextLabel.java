package ui.impl;

import controller.LabelBehavior;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.ColorChanger;
import ui.Colorizeble;
import ui.FocusPolicy;
import ui.UiComponent;

import javax.swing.*;
import java.util.Arrays;

/**
 * Text labels on calculator panel.
 * This label display service info:
 *      - dimension of value fields;
 *      - result of calculation;
 *      - service messages.
 * @author Sergey Lyashko
 */
@Component
@Scope("prototype")
class TextLabel extends JLabel implements UiComponent, Colorizeble, FocusPolicy {

    private final String defaultText;

    TextLabel(String defaultText, int locationX, int locationY, int sizeX, int sizeY, int swingConstants){
        super.setText(defaultText);
        super.setLocation(locationX, locationY);
        super.setSize(sizeX, sizeY);
        super.setVisible(true);
        super.setHorizontalAlignment(swingConstants);
        this.defaultText = defaultText;
    }

    @Override
    public int getFocusRate() {
        return Type.RESULT.getFocusRate();
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }

    @Override
    public String getName() {
        return defaultText;
    }

    @Override
    public void acceptColorChanger(ColorChanger colorChanger) {
        Type.changeColor(colorChanger);
    }

    enum Type{
        DIMENSION_WIDTH {

            private TextLabel textLabel;

            @Override
            void addColorChanger(TextLabel textLabel, ColorChanger colorChanger) {
                this.textLabel = textLabel;
                colorChanger.addColorizebleComponent(textLabel);
            }

            @Override
            void addBehavior(TextLabel textLabel, LabelBehavior labelBehavior) { }

            @Override
            protected void change(ColorChanger colorChanger) {
                if(textLabel != null) {
                    colorChanger.changeLabelColor(textLabel);
                }
            }
        },

        DIMENSION_LENGTH {

            private TextLabel textLabel;

            @Override
            void addColorChanger(TextLabel textLabel, ColorChanger colorChanger) {
                this.textLabel = textLabel;
                colorChanger.addColorizebleComponent(textLabel);
            }

            @Override
            void addBehavior(TextLabel textLabel, LabelBehavior labelBehavior) { }

            @Override
            protected void change(ColorChanger colorChanger) {
                if(textLabel != null) {
                    colorChanger.changeLabelColor(textLabel);
                }
            }
        },

        MESSAGE{

            private TextLabel textLabel;

            @Override
            void addColorChanger(TextLabel textLabel, ColorChanger colorChanger) {
                this.textLabel = textLabel;
                colorChanger.addColorizebleComponent(textLabel);
            }

            @Override
            void addBehavior(TextLabel textLabel, LabelBehavior labelBehavior) {
                labelBehavior.setComponent(textLabel);
            }

            @Override
            protected void change(ColorChanger colorChanger) {
                if(textLabel != null) {
                    colorChanger.changeServiceLabelColor(textLabel);
                }
            }
        },

        RESULT{

            private TextLabel textLabel;
            @Getter
            private final int focusRate = 6;

            @Override
            void addColorChanger(TextLabel textLabel, ColorChanger colorChanger) {
                this.textLabel = textLabel;
                colorChanger.addColorizebleComponent(textLabel);
            }

            @Override
            void addBehavior(TextLabel textLabel, LabelBehavior labelBehavior) {
                labelBehavior.setComponent(textLabel);
            }

            @Override
            protected void change(ColorChanger colorChanger) {
                if(textLabel != null) {
                    colorChanger.changeServiceLabelColor(textLabel);
                }
            }
        };

        static void changeColor(ColorChanger colorChanger) {
            Arrays.stream(Type.values()).forEachOrdered(type -> type.change(colorChanger));
        }

        abstract void addColorChanger(TextLabel textLabel, ColorChanger colorChanger);
        abstract void addBehavior(TextLabel textLabel, LabelBehavior labelBehavior);
        abstract void change(ColorChanger colorChanger);

        public int getFocusRate(){
            return RESULT.getFocusRate();
        }
    }
}
