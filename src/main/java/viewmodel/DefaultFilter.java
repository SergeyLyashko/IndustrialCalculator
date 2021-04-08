package viewmodel;

import org.springframework.stereotype.Service;
import view.AppComponent;
import viewcontroller.Filter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.io.Serializable;

@Service("defaultFilter")
public class DefaultFilter extends DocumentFilter implements Filter, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void activateFilter(AppComponent component) {
        JTextField textField = (JFormattedTextField) component.getComponentParent();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(this);
    }
}
