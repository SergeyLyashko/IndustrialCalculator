package viewcomponents.staticelements;

import viewcomponents.FactoryComponents;
import viewcomponents.SwingComponent;
import viewcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.List;

public class ApplicationView implements Serializable {

    private static final long serialVersionUID = 1L;

    private final JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private final JFrame jFrame = new JFrame("Industrial calculator");

    public void createPanel(String type, FactoryComponents factoryComponents, Visitor visitor){
        List<SwingComponent> components = factoryComponents.getComponents();
        AppPanel newPanel = new AppPanel(components, visitor);
        addTab(type, newPanel);
    }

    private void addTab(String type, AppPanel newPanel){
        Container parentContainer = newPanel.getParent();
        jTabbedPane.add(type, parentContainer);
    }

    public void createView(){
        jFrame.setContentPane(jTabbedPane);
        setFramePreference();
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
