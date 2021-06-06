package ui.calculator;

import controller.LabelBehavior;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.ColorChanger;
import ui.Colorizeble;
import ui.UiComponent;

import javax.swing.*;
import java.util.Arrays;

@Component
@Scope("prototype")
class TextLabel extends JLabel implements UiComponent, Colorizeble {

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
            private final int rate = 6;

            @Override
            void addColorChanger(TextLabel textLabel, ColorChanger colorChanger) {
                this.textLabel = textLabel;
                colorChanger.addColorizebleComponent(textLabel);
                // TODO ???
                actionComponent();
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

            private void actionComponent(){

                new UiComponent() {
                    @Override
                    public JComponent getComponentParent() {
                        return textLabel;
                    }

                    @Override
                    public boolean isTraversalPolicyFocused() {
                        return true;
                    }

                    @Override
                    public int getFocusRate() {
                        return rate;
                    }

                    @Override
                    public int compareTo(UiComponent component) {
                        return rate - component.getFocusRate();
                    }
                };
            }
        };

        static void changeColor(ColorChanger colorChanger) {
            Arrays.stream(Type.values()).forEachOrdered(type -> type.change(colorChanger));
        }

        abstract void addColorChanger(TextLabel textLabel, ColorChanger colorChanger);
        abstract void addBehavior(TextLabel textLabel, LabelBehavior labelBehavior);
        abstract void change(ColorChanger colorChanger);
    }
}
