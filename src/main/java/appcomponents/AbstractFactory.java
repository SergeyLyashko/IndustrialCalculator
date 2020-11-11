package appcomponents;

@FunctionalInterface
public interface AbstractFactory {

    SwingComponent createNewComponent(SwingComponent component, Visitor visitor);
}
