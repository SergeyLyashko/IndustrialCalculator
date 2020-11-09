package appcomponents;

public interface Factory {

    SwingComponent createNewComponent(SwingComponent swingComponent, Visitor visitor);
}
