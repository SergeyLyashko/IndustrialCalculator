package view.staticelements;

import view.ComponentsFactory;
import view.AppComponent;
import view.Visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.List;

public class AppFrame implements Serializable {

    private static final long serialVersionUID = 1L;

    private final JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private final JFrame jFrame = new JFrame("Industrial calculator");

    public void createPanel(String type, ComponentsFactory factory, Visitor visitor){
        List<AppComponent> components = factory.getComponents();
        AppPanel newPanel = new AppPanel(components, visitor);
        addTab(type, newPanel);
    }

    private void addTab(String type, AppPanel newPanel){
        Container parentContainer = newPanel.getParent();
        jTabbedPane.add(type, parentContainer);
    }

    public void create(){
        jFrame.setContentPane(jTabbedPane);
        setFramePreference();
    }

    private void setFramePreference(){
        jFrame.setSize(360, 220);
        jFrame.setLocationByPlatform(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void savePreferencesAndExit(){
        jFrame.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {}

            public void windowClosing(WindowEvent e) {
                // TODO saved preferences
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
