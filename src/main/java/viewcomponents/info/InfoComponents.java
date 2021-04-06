package viewcomponents.info;

import view.ViewController;
import view.AppComponent;
import view.CalculatorComponents;

import java.util.ArrayList;
import java.util.List;

public class InfoComponents implements CalculatorComponents {

    private final List<AppComponent> components;

    public InfoComponents(ViewController viewController) {

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
