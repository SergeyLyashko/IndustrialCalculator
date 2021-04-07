package viewcontroller;

import view.AppComponent;

public interface FieldBehavior {

    void fieldDeactivate();

    void fieldActivate();

    void areaActivate();

    void areaDeactivate();

    void setComponent(AppComponent component);

}
