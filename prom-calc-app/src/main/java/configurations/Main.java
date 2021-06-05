package configurations;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import ui.ColorChanger;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            GenericApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
            ColorChanger colorized = context.getBean("colorChanger", ColorChanger.class);
            colorized.componentsRecolor();
            context.registerShutdownHook();

        });
    }
}
