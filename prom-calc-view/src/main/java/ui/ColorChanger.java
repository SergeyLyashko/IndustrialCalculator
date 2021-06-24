package ui;

import javax.swing.*;

/**
 * Interface for change color scheme of app.
 * Visitor pattern realization.
 * @author Sergey Lyashko
 */
public interface ColorChanger {

    /**
     * Add component that needs to change color
     * @param colorizeble component, implementation of the Colorizeble interface
     */
    void addColorizebleComponent(Colorizeble colorizeble);

    /**
     * Change color theme
     * @param //colorizeble component, implementation of the Colorizeble interface
     */
    void changeComponentColor(JComponent component);

    /**
     * Change color of service label component, implementation of the Colorizeble interface
     * @param component, implementation of the Colorizeble interface
     */
    void changeServiceLabelColor(JComponent component);

    /**
     * Change color of usual label component, implementation of the Colorizeble interface
     * @param component, implementation of the Colorizeble interface
     */
    void changeLabelColor(JComponent component);

    /**
     * Activation light color theme on UI app
     */
    void activateLightScheme();

    /**
     * Activation dark color theme on UI app
     */
    void activateDarkScheme();

    /**
     * Painted component in red color
     * @param component
     */
    void setAlertColor(JComponent component);

    /**
     * Painted component in default color
     * @param component
     */
    void setDefaultColor(JComponent component);

    /**
     * Recolor all colorizeble component
     */
    void componentsRecolor();
}
