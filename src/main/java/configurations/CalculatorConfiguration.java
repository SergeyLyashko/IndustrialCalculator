package configurations;

import controller.CalculatorControllerImpl;
import controller.CalculatorModel;
import database.DetailsDAO;
import model.CalculatorModelImpl;
import model.View;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import view.*;
import viewcomponents.calculator.CalculatorComponentsImpl;
import viewcomponents.info.InfoComponents;
import viewcomponents.settings.SettingsComponents;
import viewcontroller.CalculatorController;
import viewcontroller.ViewControllerImpl;
import viewcontroller.ViewModel;
import viewmodel.ColorVisitor;
import viewmodel.ViewModelImpl;

@ComponentScan(basePackages = {"viewcomponents.calculator", "viewcomponents.settings", "viewcomponents.info"})
@Configuration
public class CalculatorConfiguration {

    @Bean
    public DataReceiver dataReceiver(){
        return new DetailsDAO();
    }

    @Bean
    public ViewController viewController(){
        return new ViewControllerImpl();
    }

    @Bean
    public ViewModel viewModel(){
        return new ViewModelImpl();
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

    @Bean
    public View view(){
        return new ViewImpl();
    }

    @Bean
    public CalculatorModel calculatorModel(){
        return new CalculatorModelImpl();
    }

    @Bean
    public CalculatorController calculatorController(){
        return new CalculatorControllerImpl();
    }

    @Bean
    public Visitor colorVisitor(){
        return new ColorVisitor();
    }

}
