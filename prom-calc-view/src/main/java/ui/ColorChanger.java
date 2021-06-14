package ui;

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
     * @param colorizeble component, implementation of the Colorizeble interface
     */
    void changeComponentColor(Colorizeble colorizeble);

    /**
     * Change color of service label component, implementation of the Colorizeble interface
     * @param colorizeble component, implementation of the Colorizeble interface
     */
    void changeServiceLabelColor(Colorizeble colorizeble);

    /**
     * Change color of usual label component, implementation of the Colorizeble interface
     * @param colorizeble component, implementation of the Colorizeble interface
     */
    void changeLabelColor(Colorizeble colorizeble);

    /**
     * Activation light color theme on UI app
     */
    void activateLightScheme();

    /**
     * Activation dark color theme on UI app
     */
    void activateDarkScheme();

    /**
     * Change color of scroll pane of info panel
     * @param colorizeble component, implementation of the Colorizeble interface
     */
    void changeScrollColor(Colorizeble colorizeble);

    /**
     * Painted component in red color
     * @param component
     */
    void setAlertColor(UiComponent component);

    /**
     * Painted component in default color
     * @param component
     */
    void setDefaultColor(UiComponent component);

    /**
     * Recolor all colorizeble component
     */
    void componentsRecolor();
}
