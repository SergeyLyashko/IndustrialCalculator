package components.common;

public interface Visitor {

    void addHost(Host host);

    void visitComponent(Host host);

    void visitServiceLabel(Host host);

    void visitLabel(Host host);

    void deactivate();

    void activate();

    void visitScroll(Host host);

    void alert(AppComponent component);

    void reset(AppComponent component);

    void raid();
}
