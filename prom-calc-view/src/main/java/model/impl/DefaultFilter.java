package model.impl;

import org.springframework.stereotype.Service;
import model.Filter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

@Service("defaultFilter")
class DefaultFilter extends DocumentFilter implements Filter {

    @Override
    public void activateFilter(JComponent component) {
        if(component instanceof JFormattedTextField){
            ((AbstractDocument) ((JFormattedTextField)component).getDocument()).setDocumentFilter(this);
        }
    }
}
