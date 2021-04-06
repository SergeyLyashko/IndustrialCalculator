package controller;

import configurations.CalculatorConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import view.Visitor;

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
