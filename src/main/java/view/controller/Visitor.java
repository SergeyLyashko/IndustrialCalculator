package view.controller;

public interface Visitor {

    void addHost(Host host);

    void visitComponent(Host host);

    void visitResultLabel(Host host);

    void visitMessageLabel(Host host);

    void visitLabel(Host host);

    void deactivate();

    void activate();

    void visitScroll(Host host);

}
