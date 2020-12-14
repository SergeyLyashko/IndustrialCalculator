package viewcontroller;

import view.AppComponent;

class FieldsAction {

    private final FieldBehavior fieldBehavior;
    private final FocusBehavior focusBehavior;
    private final KeyBehavior keyBehavior;

    FieldsAction(ViewModel viewModel, AppComponent component) {
        this.fieldBehavior = viewModel.getFieldBehavior(component);
        this.focusBehavior = viewModel.getFocusBehavior(component);
        this.keyBehavior = viewModel.getKeyBehavior(component);
    }

    void registerFocusObserver(FocusActionObserver observer){
        focusBehavior.registerFocusObserver(observer);
    }

    void registerKeyObserver(KeyActionObserver observer){
        keyBehavior.registerKeyObserver(observer);
    }

    void deactivate(){
        fieldBehavior.fieldDeactivate();
        focusBehavior.fieldDeactivate();
        keyBehavior.fieldDeactivate();
    }

    void activate(){
        fieldBehavior.fieldActivate();
        focusBehavior.fieldActivate();
    }

    void keyActivate(){
        keyBehavior.fieldActivate();
    }

    void areaActivate(){
        fieldBehavior.areaActivate();
    }

    void areaDeactivate(){
        fieldBehavior.areaDeactivate();
    }
}
