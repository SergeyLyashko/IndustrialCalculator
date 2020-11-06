package appview;

public interface Visitor {

    void raid();

    void addVisitorComponent(SwingComponent component);

    void visit(SwingComponent component);
}
