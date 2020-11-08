package appcomponents;

public abstract class AbstractFactory {

    private SwingComponent swingComponent;

    public void createNewComponent(SwingComponent swingComponent){
        this.swingComponent = create(swingComponent);
    }

    public abstract SwingComponent create(SwingComponent swingComponent);

    public SwingComponent getComponent(){
        return swingComponent;
    }
}
