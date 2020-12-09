package view.view;

public interface Visitor {

    void addHost(Host host);

    void visitComponent(Host host);

    void visitServiceLabel(Host host);

    void visitLabel(Host host);

    void deactivate();

    void activate();

    void visitScroll(Host host);

    void alertColor(AppComponent component);

    void resetColor(AppComponent component);
}
