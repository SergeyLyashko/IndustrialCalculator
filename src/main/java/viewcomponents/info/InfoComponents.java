package viewcomponents.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import viewcomponents.common.AppComponent;
import viewcomponents.common.CalculatorComponents;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service("infoComponents")
class InfoComponents implements CalculatorComponents {

    private List<AppComponent> components;
    private AppComponent scrollWrapper;

    @Autowired
    @Qualifier("scroller")
    public void setScrollWrapper(AppComponent scrollWrapper){
        this.scrollWrapper = scrollWrapper;
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        components = new ArrayList<>();
        components.add(scrollWrapper);
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }
}
