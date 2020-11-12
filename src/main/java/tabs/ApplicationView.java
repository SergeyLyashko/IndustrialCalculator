package tabs;

import appcomponents.*;
import appcomponents.ComponentsCollector;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.List;

public class ApplicationView implements Serializable {

    private static final long serialVersionUID = 1L;

    private final JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private final JFrame jFrame = new JFrame("Industrial calculator");

    public void createPanel(String type, ComponentsCollector collector, Visitor visitor){
        List<SwingComponent> components = collector.getComponents();
        SwingComponent panel = new PanelImpl().create(components, visitor);
        SwingComponent newPanel = panel.getFactory().createNewComponent(panel, visitor);
        addTab(type, newPanel);
    }

    private void addTab(String type, SwingComponent panel){
        jTabbedPane.add(type, panel.getParent());
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
