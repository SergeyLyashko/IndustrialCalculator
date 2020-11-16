package view;

public interface Visitor {

    void raid();

    void addHost(Host host);

    void visit(Host host);

}
