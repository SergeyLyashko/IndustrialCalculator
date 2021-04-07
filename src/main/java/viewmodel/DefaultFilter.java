package viewmodel;

import view.AppComponent;
import viewcontroller.Filter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.io.Serializable;

class DefaultFilter extends DocumentFilter implements Filter, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void setFilter(AppComponent component) {
        JTextField textField = (JFormattedTextField) component.getComponentParent();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(this);
    }
}
