package controller;

import configurations.CalculatorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.Visitor;
import viewcontroller.CalculatorController;
import view.ViewController;
import viewcontroller.ViewControllerImpl;
import viewcontroller.ViewModel;
import viewmodel.ViewModelImpl;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            //DataReceiver dataReceiver = new DetailsDAO();
            ApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
            Visitor colorized = context.getBean("colorVisitor", Visitor.class);
            colorized.raid();

            //CalculatorModel model = new CalculatorModelImpl();
            //CalculatorController controller = new CalculatorControllerImpl(/*model, dataReceiver*/);

            //ViewModel viewModel = new ViewModelImpl();
            //ViewController viewController = new ViewControllerImpl(/*viewModel, controller*/);
            //View view = new ViewImpl(/*dataReceiver, viewController*/);

            //model.registerObserver(view);
            //controller.registerObserver(view);
        });
    }
}
