package appview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;

public class AppView implements Serializable {

    private static final long serialVersionUID = 1L;

    private final JFrame jFrame;
    private final Visitor visitor;

    public AppView(){
        jFrame = new JFrame("Industrial calculator");
        jFrame.setBounds(300, 300, 360, 220);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setVisible(true);
        visitor = new VisitorImpl();
    }

    public void create(){
        JPanel panel = createPanel();
        addContentPane(panel);
    }

    private JPanel createPanel(){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingComponent createPanel(String name, Visitor visitor) {
                return new CommonPanel();
            }
        };
        abstractPanel.order("", visitor);
        return abstractPanel.getAbstractComponent();
    }

    private void addContentPane(JPanel jPanel){
        Container contentPane = jFrame.getContentPane();
        contentPane.add(jPanel, BorderLayout.CENTER);
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