package model;

import controller.CalculatorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.ViewController;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;

@Service("calculatorModel")
class CalculatorModelImpl implements CalculatorModel {

    private static final String RESULT_MESSAGE = "Результат скопирован в буфер обмена";
    private static final boolean CALM = false;
    private DecimalFormat decimalFormat;
    private ViewController viewController;

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    @Autowired
    public void setDecimalFormat(DecimalFormat decimalFormat){
        this.decimalFormat = decimalFormat;
    }

    @Override
    public void executeCalculation(AbstractMassCalculator abstractMassCalculator) {
        double mass = abstractMassCalculator.calculation();
        if(mass > 0) {
            String formattedResult = decimalFormat.format(mass);
            notifyObservers(formattedResult);
        }
    }

    private void notifyObservers(String result){
        viewController.setResult(result, CALM);
        viewController.setMessage(RESULT_MESSAGE, CALM);
        setResultToSystemClipboard(result);
    }

    // метод копирования в буфер обмена при выводе результата
    private void setResultToSystemClipboard(String value){
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(value), null);
    }
}
