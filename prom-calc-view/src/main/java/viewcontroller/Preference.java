package viewcontroller;

import viewcomponents.common.AppComponent;

import java.util.List;

public interface Preference {
    /*
    List<AppComponent> loadComponents();

    void saveComponents(List<AppComponent> components);

    boolean isSaved();*/

    void save();

    void loadProperties();
}
