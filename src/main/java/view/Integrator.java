package view;

@FunctionalInterface
public interface Integrator {

    AppComponent integration(AppComponent component, Visitor visitor);
}
