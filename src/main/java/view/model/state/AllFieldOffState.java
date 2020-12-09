package view.model.state;

public class AllFieldOffState implements WidthFieldState {

    private final FieldState fieldState;

    public AllFieldOffState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
    }
}
