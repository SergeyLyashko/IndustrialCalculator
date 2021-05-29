package model;

import org.springframework.stereotype.Service;
import ui.UiComponent;
import controller.Filter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

@Service("defaultFilter")
class DefaultFilter extends DocumentFilter implements Filter {

    @Override
    public void activateFilter(UiComponent component) {
        JTextField textField = (JFormattedTextField) component.getComponentParent();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(this);
    }
}
