package viewcomponents;

@FunctionalInterface
public interface AbstractFactory {

    SwingComponent createNewComponent(SwingComponent component, Visitor visitor);
}
