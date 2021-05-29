package ui.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ui.UiComponent;
import ui.CalculatorComponents;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;

@Component("appFrame")
public class AppFrame extends JFrame implements ApplicationContextAware {

    private final JTabbedPane jTabbedPane;
    private ApplicationContext applicationContext;

    public AppFrame(){
        super("Industrial calculator");
        super.setSize(370, 230);
        super.setLocationByPlatform(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        super.setContentPane(jTabbedPane);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        addPanel("Калькулятор");
        addPanel("Настройки");
        addPanel("Справка");
    }

    private void addPanel(String type){
        AppPanel appPanelBean = applicationContext.getBean(type, AppPanel.class);
        JComponent componentParent = appPanelBean.getComponentParent();
        CalculatorComponents componentsBean = applicationContext.getBean(type+" компоненты", CalculatorComponents.class);
        List<UiComponent> components = componentsBean.getComponents();
        components.forEach(appComponent -> componentParent.add(appComponent.getComponentParent()));
        jTabbedPane.add(type, componentParent);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
