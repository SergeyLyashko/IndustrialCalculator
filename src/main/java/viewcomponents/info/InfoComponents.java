package viewcomponents.info;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import view.ViewController;
import view.AppComponent;
import view.CalculatorComponents;

import java.util.ArrayList;
import java.util.List;

@Service("infoComponents")
public class InfoComponents implements CalculatorComponents, InitializingBean {

    private List<AppComponent> components;

    //private ViewController viewController;
    private AppComponent info;
    private AppComponent scrollWrapper;

    /*
    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }*/

    @Autowired
    @Qualifier("info")
    public void setInfo(AppComponent info){
        this.info = info;
    }

    @Autowired
    @Qualifier("scroller")
    public void setScrollWrapper(AppComponent scrollWrapper){
        this.scrollWrapper = scrollWrapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //AppComponent info = new Info(viewController);
        //AppComponent scrollWrapper = new ScrollWrapper(viewController, info);

        info.integrationToPanel();
        scrollWrapper.integrationToPanel();

        components = new ArrayList<>();
        components.add(scrollWrapper);
    }

    public InfoComponents(/*ViewController viewController*/) {
        /*
        AppComponent info = new Info(viewController);
        AppComponent scrollWrapper = new ScrollWrapper(viewController, info);

        info.integrationToPanel();
        scrollWrapper.integrationToPanel();

        components = new ArrayList<>();
        components.add(scrollWrapper);*/
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }
}
