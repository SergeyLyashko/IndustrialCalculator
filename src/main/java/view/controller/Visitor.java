package view.controller;

import view.view.AppComponent;

public interface Visitor {

    void addHost(Host host);

    void visitComponent(Host host);

    void visitResultLabel(Host host);

    void visitMessageLabel(Host host);

    void visitLabel(Host host);

    void deactivate();

    void activate();

    void visitScroll(Host host);

    void alertColor(AppComponent component);

    void resetColor(AppComponent component);
}
