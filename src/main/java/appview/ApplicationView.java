package appview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;

public class ApplicationView implements Serializable {

    private static final long serialVersionUID = 1L;

    private final JFrame jFrame;
    private final Visitor visitor;

    public ApplicationView(){
        jFrame = new JFrame("Industrial calculator");
        visitor = new VisitorImpl();
    }

    public void create(){
        ContainerBuilder containerBuilder = new ContainerBuilder();
        SwingComponent build = containerBuilder.build("Tab", visitor);
        setContentPane(build);
        setFramePreference();
    }

    private void setFramePreference(){
        jFrame.setBounds(300, 300, 360, 220);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private void setContentPane(SwingComponent swingContainer) {
        Container containerParent = swingContainer.getParent();
        jFrame.setContentPane(containerParent);
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
