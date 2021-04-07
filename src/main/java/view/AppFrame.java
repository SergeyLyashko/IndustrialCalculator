package view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component("appFrame")
public class AppFrame {

    private JTabbedPane jTabbedPane;
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
    }

    private void addPanel(String type, AppPanel panel){
        Container parentContainer = panel.getComponentParent();
        jTabbedPane.add(type, parentContainer);
    }
}
