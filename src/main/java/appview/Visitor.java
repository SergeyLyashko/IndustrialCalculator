package appview;

public interface Visitor {

    void visit(SwingPanel panel);

    void raid();

    void addVisitorPanel(SwingPanel panel);

    void addVisitorComponent(SwingComponent component);

    void visit(SwingComponent component);
}
