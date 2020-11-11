package tabs;

import appcomponents.*;
import appcomponents.ComponentsCollector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationView implements Serializable {

    private static final long serialVersionUID = 1L;

    private final JFrame jFrame;
    private final List<SwingComponent> panelsList;

    public ApplicationView(){
        jFrame = new JFrame("Industrial calculator");
        panelsList = new ArrayList<>();
    }

    public void setSettingsComponents(ComponentsCollector settingsComponents, Visitor visitor) {
        List<SwingComponent> components = settingsComponents.getComponents();
        SwingComponent settings = new PanelImpl().create("Настройки", components, visitor);
        SwingComponent newComponent = settings.getFactory().createNewComponent(settings, visitor);
        panelsList.add(newComponent);
    }

    public void setInfoComponents(ComponentsCollector infoComponents, Visitor visitor){
        List<SwingComponent> components = infoComponents.getComponents();
        SwingComponent info = new PanelImpl().create("Справка", components, visitor);
        SwingComponent newComponent = info.getFactory().createNewComponent(info, visitor);
        panelsList.add(newComponent);
    }

    public void setCalculatorComponents(ComponentsCollector calculatorComponents, Visitor visitor){
        List<SwingComponent> components = calculatorComponents.getComponents();
        SwingComponent calc = new PanelImpl().create("Калькулятор", components, visitor);
        SwingComponent newComponent = calc.getFactory().createNewComponent(calc, visitor);
        panelsList.add(newComponent);
    }

    public void createView(){
        Container tabbedContainer = createTabbedContainer();
        jFrame.setContentPane(tabbedContainer);
        setFramePreference();
    }

    private Container createTabbedContainer(){
        JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        panelsList.forEach(panel -> jTabbedPane.add(panel.getName(), panel.getParent()));
        return jTabbedPane;
    }

    private void setFramePreference(){
        jFrame.setBounds(300, 300, 360, 220);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void savePreferencesAndExit(){
        jFrame.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {}

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            public void windowClosed(WindowEvent e) {}

            public void windowIconified(WindowEvent e) {}

            public void windowDeiconified(WindowEvent e) {}

            public void windowActivated(WindowEvent e) {}

            public void windowDeactivated(WindowEvent e) {}
        });
    }
}
