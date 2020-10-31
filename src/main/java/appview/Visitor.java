package appview;

public interface Visitor {

    void visit(SwingComponent components);

    void raid();

    void addVisitorComponent(SwingComponent components);
}
