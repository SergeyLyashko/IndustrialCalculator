package appcomponents;

public interface Visitor {

    void raid();

    void addHost(Host host);

    void visit(Host host);

}
