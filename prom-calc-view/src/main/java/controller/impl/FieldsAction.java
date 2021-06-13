package controller.impl;

import model.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ui.UiComponent;

@Service
@Scope("prototype")
public class FieldsAction implements ApplicationContextAware {

    @Autowired
    private FieldBehavior fieldBehavior;
    @Autowired
    @Qualifier("defaultFilter")
    private Filter defaultFilter;
    private FocusBehavior focusBehavior;
    private UiComponent component;
    private boolean actionState;
    private ApplicationContext applicationContext;

    public void setComponent(UiComponent component){
        this.component = component;
        this.focusBehavior = applicationContext.getBean("focusBehavior", FocusBehavior.class);
        deactivate();
    }

    boolean isActionState(){
        return actionState;
    }

    void setAreaActionState(boolean status){
        this.actionState = status;
    }

    void deactivate(){
        defaultFilter.activateFilter(component);
        fieldBehavior.fieldDeactivate(component);
        focusBehavior.fieldDeactivate(component);
    }

    void activate(){
        defaultFilter.activateFilter(component);
        fieldBehavior.fieldActivate(component);
        focusBehavior.fieldActivate(component);
    }

    void areaActivate(){
        fieldBehavior.areaActivate(component);
    }

    void areaDeactivate(){
        fieldBehavior.areaDeactivate(component);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
