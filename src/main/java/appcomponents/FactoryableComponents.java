package appcomponents;

@FunctionalInterface
public interface FactoryableComponents {

    SwingComponent createNewComponent(SwingComponent component, Visitor visitor);
}
