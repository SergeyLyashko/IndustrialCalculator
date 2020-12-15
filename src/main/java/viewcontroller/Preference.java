package viewcontroller;

import view.AppComponent;

import java.util.List;

public interface Preference {

    List<AppComponent> loadComponents();

    void saveComponents(List<AppComponent> components);

    boolean isSaved();
}
