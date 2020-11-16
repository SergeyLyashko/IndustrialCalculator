package view;

@FunctionalInterface
public interface Initializer {

    AppComponent initComponent(AppComponent component, Visitor visitor);
}
