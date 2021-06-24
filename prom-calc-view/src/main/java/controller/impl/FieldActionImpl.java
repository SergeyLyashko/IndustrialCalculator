package controller.impl;

import controller.FieldAction;
import model.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
@Scope("prototype")
class FieldActionImpl implements FieldAction, ApplicationContextAware {

    @Autowired
    private FieldBehavior fieldBehavior;
    @Autowired
    @Qualifier("defaultFilter")
    private Filter defaultFilter;
    private FocusBehavior focusBehavior;
    private JComponent component;
    private ApplicationContext applicationContext;

    @Override
    public void setComponent(JComponent component){
        this.component = component;
        this.focusBehavior = applicationContext.getBean("focusBehavior", FocusBehavior.class);
        deactivate();
    }

    @Override
    public void deactivate(){
        defaultFilter.activateFilter(component);
        fieldBehavior.fieldDeactivate(component);
        focusBehavior.fieldDeactivate(component);
    }

    @Override
    public void activate(){
        defaultFilter.activateFilter(component);
        fieldBehavior.fieldActivate(component);
        focusBehavior.fieldActivate(component);
    }

    @Override
    public void areaActivate(){
        fieldBehavior.areaActivate(component);
    }

    @Override
    public void areaDeactivate(){
        fieldBehavior.areaDeactivate(component);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
