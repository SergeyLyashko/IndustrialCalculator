package viewcomponents.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import view.AppComponent;
import view.CalculatorComponents;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service("infoComponents")
public class InfoComponents implements CalculatorComponents {

    private List<AppComponent> components;
    private AppComponent info;
    private AppComponent scrollWrapper;

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

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
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
