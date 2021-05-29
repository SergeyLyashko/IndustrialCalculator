package model;

import org.springframework.stereotype.Service;
import ui.AppComponent;
import controller.Filter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

@Service("digitalFilter")
class DigitalFilter extends DocumentFilter implements Filter {

    @Override
    public void activateFilter(AppComponent component) {
        JTextField textField = (JFormattedTextField) component.getComponentParent();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(this);
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String str, AttributeSet attr)
            throws BadLocationException {
        StringBuilder stringBuilder = new StringBuilder(str);
        for(int i=stringBuilder.length()-1; i>=0; i--){
            int codePoint = stringBuilder.codePointAt(i);
            if(!Character.isDigit(codePoint)){
                stringBuilder.deleteCharAt(i);
                if(Character.isSupplementaryCodePoint(codePoint)){
                    i--;
                    stringBuilder.deleteCharAt(i);
                }
            }
        }
        super.insertString(fb, offset, stringBuilder.toString(), attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if(text != null){
            StringBuilder stringBuilder = new StringBuilder(text);
            for(int i=stringBuilder.length()-1; i>=0; i--){
                int codePoint = stringBuilder.codePointAt(i);
                if(!Character.isDigit(codePoint)){
                    stringBuilder.deleteCharAt(i);
                    if(Character.isSupplementaryCodePoint(codePoint)){
                        i--;
                        stringBuilder.deleteCharAt(i);
                    }
                }
            }
            text = stringBuilder.toString();
        }
        super.replace(fb, offset, length, text, attrs);
    }
}
