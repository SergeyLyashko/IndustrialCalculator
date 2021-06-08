package ui.impl;

import org.springframework.stereotype.Service;
import ui.Colorizeble;
import ui.ColorChanger;
import ui.UiComponent;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Change color theme of UI
 * @author Sergey Lyashko
 */
@Service("colorChanger")
class ColorChangerImpl implements ColorChanger {

    private final List<Colorizeble> componentsList = new CopyOnWriteArrayList<>();
    private Color backGround;
    private Color foreGround;
    private Color markerColor;
    private Color serviceStringColor;
    private final Color alertColor = Color.RED;

    @Override
    public void componentsRecolor() {
        componentsList.forEach(component -> component.acceptColorChanger(this));
    }

    @Override
    public void addColorizebleComponent(Colorizeble colorizeble) {
        componentsList.add(colorizeble);
    }

    @Override
    public void changeComponentColor(Colorizeble colorizeble) {
        JComponent parent = colorizeble.getComponentParent();
        parent.setBackground(backGround);
        parent.setForeground(foreGround);
    }

    @Override
    public void changeServiceLabelColor(Colorizeble colorizeble) {
        JComponent parent = colorizeble.getComponentParent();
        parent.setForeground(serviceStringColor);
    }

    @Override
    public void changeLabelColor(Colorizeble colorizeble) {
        JComponent parent = colorizeble.getComponentParent();
        parent.setForeground(markerColor);
    }

    @Override
    public void changeScrollColor(Colorizeble colorizeble) {
        JComponent scrollViewPort = colorizeble.getScrollViewPort();
        scrollViewPort.setBackground(backGround);
    }

    @Override
    public void setAlertColor(UiComponent component) {
        JComponent parent = component.getComponentParent();
        parent.setForeground(alertColor);
    }

    @Override
    public void setDefaultColor(UiComponent component) {
        JComponent parent = component.getComponentParent();
        parent.setForeground(serviceStringColor);
    }

    @Override
    public void activateLightScheme() {
        backGround = new Color(250, 236, 229);
        foreGround = Color.BLACK;
        markerColor = Color.BLACK;
        serviceStringColor = Color.BLUE;
    }

    @Override
    public void activateDarkScheme() {
        backGround = Color.BLACK;
        foreGround = Color.WHITE;
        markerColor = Color.WHITE;
        serviceStringColor = Color.GREEN;
    }
}
