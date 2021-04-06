package controller;

import configurations.CalculatorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.Visitor;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            ApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
            Visitor colorized = context.getBean("colorVisitor", Visitor.class);
            colorized.raid();

        });
    }
}
