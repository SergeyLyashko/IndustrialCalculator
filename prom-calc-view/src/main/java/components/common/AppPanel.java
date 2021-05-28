package components.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component
public class AppPanel extends JPanel implements Host {

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
        super.setLayout(null);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }
}
