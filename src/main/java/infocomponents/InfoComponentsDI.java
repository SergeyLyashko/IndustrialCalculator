package infocomponents;

import view.ViewController;
import view.AppComponent;
import view.ComponentsList;

import java.util.ArrayList;
import java.util.List;

public class InfoComponentsDI implements ComponentsList {

    private final List<AppComponent> components;

    public InfoComponentsDI(ViewController viewController) {

        AppComponent info = new Info(viewController);
        AppComponent scrollWrapper = new ScrollWrapper(viewController, info);

        info.integrationToPanel();
        scrollWrapper.integrationToPanel();

        components = new ArrayList<>();
        components.add(scrollWrapper);
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }
}
