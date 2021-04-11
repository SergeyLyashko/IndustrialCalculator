package view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component
public class AppPanel implements Host {

    private final JPanel jPanel;
    private Visitor colorVisitor;

    @PostConstruct
    private void afterPropertiesSet() {
        colorVisitor.addHost(this);
    }

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    public AppPanel(){
        jPanel = new JPanel();
        jPanel.setLayout(null);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getComponentParent() {
        return jPanel;
    }
}
