package view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@Component("appFrame")
public class AppFrame implements WinCloseSubject {

    private JTabbedPane jTabbedPane;
    private List<WinCloseObserver> observers;
    private AppPanel calculatorPanel;
    private AppPanel settingsPanel;
    private AppPanel infoPanel;

    @Autowired
    @Qualifier("calculatorPanel")
    public void setCalculatorPanel(AppPanel calculatorPanel){
        this.calculatorPanel = calculatorPanel;
    }

    @Autowired
    @Qualifier("settingsPanel")
    public void setSettingsPanel(AppPanel settingsPanel){
        this.settingsPanel = settingsPanel;
    }

    @Autowired
    @Qualifier("infoPanel")
    public void setInfoPanel(AppPanel infoPanel){
        this.infoPanel = infoPanel;
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JFrame jFrame = new JFrame("Industrial calculator");
        jFrame.setSize(370, 230);
        jFrame.setLocationByPlatform(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setContentPane(jTabbedPane);
        addPanel("Калькулятор", calculatorPanel);
        addPanel("Настройки", settingsPanel);
        addPanel("Справка", infoPanel);
        addListener(jFrame);
        observers = new ArrayList<>();
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
        if(!observers.isEmpty()) {
            observers.forEach(WinCloseObserver::winCloseUpdate);
        }
        System.exit(0);
    }

    private void addPanel(String type, AppPanel panel){
        Container parentContainer = panel.getParent();
        jTabbedPane.add(type, parentContainer);
    }

    // TODO написать spring @PreDestroy
    @Override
    public void registerWinCloseObserver(WinCloseObserver observer) {
        observers.add(observer);
    }
}
