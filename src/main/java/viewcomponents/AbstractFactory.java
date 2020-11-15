package viewcomponents;

@FunctionalInterface
public interface AbstractFactory {

    AppComponent createNewComponent(AppComponent component, Visitor visitor);
}
