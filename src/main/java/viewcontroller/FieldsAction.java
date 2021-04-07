package viewcontroller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import view.AppComponent;
import viewmodel.FocusActionObserver;
import viewmodel.KeyActionObserver;

@Service("fieldsAction")
@Scope("prototype")
public class FieldsAction implements FocusActionObserver, ApplicationContextAware {

    private FieldBehavior fieldBehavior;
    private FocusBehavior focusBehavior;
    private KeyBehavior keyBehavior;
    private Filter defaultFilter;
    private Filter digitalFilter;
    private AppComponent component;

    private boolean actionState;
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("defaultFilter")
    public void setDefaultFilter(Filter defaultFilter){
        this.defaultFilter = defaultFilter;
    }

    @Autowired
    @Qualifier("digitalFilter")
    public void setDigitalFilter(Filter digitalFilter){
        this.digitalFilter = digitalFilter;
    }

    public void setComponent(AppComponent component){
        this.component = component;

        FieldBehavior fieldBehaviorBean = applicationContext.getBean("fieldBehavior", FieldBehavior.class);
        fieldBehaviorBean.setComponent(component);
        this.fieldBehavior = fieldBehaviorBean;

        FocusBehavior focusBehaviorBean = applicationContext.getBean("focusBehavior", FocusBehavior.class);
        focusBehaviorBean.setComponent(component);
        focusBehaviorBean.registerFocusObserver(this);
        this.focusBehavior = focusBehaviorBean;

        KeyBehavior keyBehaviorBean = applicationContext.getBean("keyBehavior", KeyBehavior.class);
        keyBehaviorBean.setComponent(component);
        this.keyBehavior = keyBehaviorBean;

        deactivate();
    }

    boolean isActionState(){
        return actionState;
    }

    void setState(boolean status){
        this.actionState = status;
    }

    void registerKeyObserver(KeyActionObserver observer){
        keyBehavior.registerKeyObserver(observer);
    }

    void deactivate(){
        removeFilter();
        fieldBehavior.fieldDeactivate();
        focusBehavior.fieldDeactivate();
        keyBehavior.fieldDeactivate();
    }

    void activate(){
        removeFilter();
        fieldBehavior.fieldActivate();
        focusBehavior.fieldActivate();
    }

    void areaActivate(){
        fieldBehavior.areaActivate();
    }

    void areaDeactivate(){
        fieldBehavior.areaDeactivate();
    }

    private void removeFilter(){
        defaultFilter.setFilter(component);
    }

    private void setFilter(){
        digitalFilter.setFilter(component);
    }

    @Override
    public void focusActionUpdate(AppComponent component) {
        setFilter();
        keyBehavior.fieldActivate();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
