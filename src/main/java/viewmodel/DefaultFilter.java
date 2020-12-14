package viewmodel;

import view.AppComponent;
import viewcontroller.Filter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

class DefaultFilter extends DocumentFilter implements Filter {

    @Override
    public void setFilter(AppComponent component) {
        JTextField textField = (JFormattedTextField) component.getParent();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(this);
    }
}
