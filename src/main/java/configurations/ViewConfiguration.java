package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import view.*;
import viewcomponents.calculator.CalculatorComponentsImpl;
import viewcomponents.info.InfoComponents;
import viewcomponents.settings.SettingsComponents;
import viewcontroller.ViewControllerImpl;

@ComponentScan(basePackages = {"viewcomponents.settings", "viewcomponents.info", "viewcontroller", "viewmodel"})
@Configuration
@Import({ViewCalculatorConfiguration.class, ViewSettingsConfiguration.class, ViewModelConfiguration.class})
public class ViewConfiguration {

    @Bean
    public ViewController viewController(){
        return new ViewControllerImpl();
    }

    @Bean
    public CalculatorComponents calculatorComponents(){
        return new CalculatorComponentsImpl();
    }

    @Bean
    public CalculatorComponents settingsComponents(){
        return new SettingsComponents();
    }

    @Bean
    public CalculatorComponents infoComponents(){
        return new InfoComponents();
    }

    @Bean
    public CalculatorFocusTraversalPolicy focusTraversalPolicy(){
        return new CalculatorFocusTraversalPolicy();
    }

    @Bean
    public AppPanel calculatorPanel(){
        AppPanel panel = new AppPanel(calculatorComponents());
        panel.addFocusPolicy(focusTraversalPolicy());
        return panel;
    }

    @Bean
    public AppPanel settingsPanel(){
        return new AppPanel(settingsComponents());
    }

    @Bean
    public AppPanel infoPanel(){
        return new AppPanel(infoComponents());
    }

    @Bean
    public AppFrame appFrame(){
        return new AppFrame();
    }
}
