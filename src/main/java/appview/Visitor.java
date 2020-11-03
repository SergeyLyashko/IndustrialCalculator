package appview;

public interface Visitor {

    void visit(SwingPanel components);

    void raid();

    void addVisitorComponent(SwingPanel components);
}
