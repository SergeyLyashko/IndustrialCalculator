package view;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;

@Component("appFrame")
class AppFrame implements ApplicationContextAware {

    private JTabbedPane jTabbedPane;
    private ApplicationContext applicationContext;

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
        addPanel("Калькулятор");
        addPanel("Настройки");
        addPanel("Справка");
    }

    private void addPanel(String type){
        AppPanel appPanelBean = applicationContext.getBean(type, AppPanel.class);
        JComponent componentParent = appPanelBean.getComponentParent();
        CalculatorComponents calculatorComponentsBean = applicationContext.getBean(type+" компоненты", CalculatorComponents.class);
        List<AppComponent> components = calculatorComponentsBean.getComponents();
        components.forEach(appComponent -> componentParent.add(appComponent.getComponentParent()));
        jTabbedPane.add(type, componentParent);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
