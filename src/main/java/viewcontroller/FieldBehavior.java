package viewcontroller;

import viewcomponents.common.AppComponent;

public interface FieldBehavior {

    void fieldDeactivate();

    void fieldActivate();

    void areaActivate();

    void areaDeactivate();

    void setComponent(AppComponent component);

}
