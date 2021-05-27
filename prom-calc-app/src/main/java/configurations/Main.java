package configurations;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import viewcomponents.common.Visitor;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            GenericApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
            Visitor colorized = context.getBean("colorVisitor", Visitor.class);
            colorized.raid();
            context.registerShutdownHook();

        });
    }
}
