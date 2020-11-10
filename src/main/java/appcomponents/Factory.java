package appcomponents;

public interface Factory {

    SwingComponent createNewComponent(SwingComponent component, Visitor visitor);
}
