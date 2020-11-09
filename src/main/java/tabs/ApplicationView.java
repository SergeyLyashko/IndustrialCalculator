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
    private final PanelFactory panelFactory;
    private final List<SwingComponent> panelsList;

    public ApplicationView(){
        jFrame = new JFrame("Industrial calculator");
        panelFactory = new PanelFactory();
        panelsList = new ArrayList<>();
    }

    public void setSettingsComponents(ComponentsCollector settingsComponents, Visitor visitor) {
        List<SwingComponent> components = settingsComponents.getComponents();
        SwingComponent settings = panelFactory.createPanel("Настройки", components, visitor);
        panelsList.add(settings);
    }

    public void setInfoComponents(ComponentsCollector infoComponents, Visitor visitor){
        List<SwingComponent> components = infoComponents.getComponents();
        SwingComponent info = panelFactory.createPanel("Справка", components, visitor);
        panelsList.add(info);
    }

    public void setCalculatorComponents(ComponentsCollector calculatorComponents, Visitor visitor){
        List<SwingComponent> components = calculatorComponents.getComponents();
        SwingComponent calc = panelFactory.createPanel("Калькулятор", components, visitor);
        panelsList.add(calc);
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
