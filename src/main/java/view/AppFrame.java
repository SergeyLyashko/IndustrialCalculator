package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class AppFrame implements WinCloseSubject {

    private final JTabbedPane jTabbedPane;
    private WinCloseObserver observer;

    AppFrame(AppPanel calculatorPanel, AppPanel settingsPanel, AppPanel infoPanel) {
        jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JFrame jFrame = new JFrame("Industrial calculator");
        jFrame.setSize(360, 220);
        jFrame.setLocationByPlatform(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setContentPane(jTabbedPane);
        addPanel("Калькулятор", calculatorPanel);
        addPanel("Настройки", settingsPanel);
        addPanel("Справка", infoPanel);
        addListener(jFrame);
    }

    private void addListener(JFrame jFrame){
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                notifyObserver();
                System.exit(0);
            }
        });
    }

    private void notifyObserver() {
        if(observer != null) {
            observer.winCloseUpdate();
        }
    }

    private void addPanel(String type, AppPanel panel){
        Container parentContainer = panel.getParent();
        jTabbedPane.add(type, parentContainer);
    }

    @Override
    public void registerWinCloseObserver(WinCloseObserver observer) {
        this.observer = observer;
    }
}
