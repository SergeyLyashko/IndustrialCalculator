package textlabels;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public interface AbstractLabel extends SwingComponent {

    default SwingComponent initialization(SwingComponent component, Visitor visitor){
        JLabel jLabel = getLabel();
        setLocation(component, jLabel);
        setText(component, jLabel);
        setSize(component, jLabel);
        setHorizontalAlignment(component, jLabel);
        component.setParent(jLabel);
        return component;
    }

    default void setHorizontalAlignment(SwingComponent component, JLabel jLabel){

    }

    default void setSize(SwingComponent component, JLabel jLabel){

    }

    default void setText(SwingComponent component, JLabel jLabel){

    }

    default void setLocation(SwingComponent component, JLabel jLabel){

    }



    default JLabel getLabel(){
        JLabel jLabel = new JLabel();
        jLabel.setVisible(true);
        return jLabel;
    }
}
