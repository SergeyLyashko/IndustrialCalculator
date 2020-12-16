package viewcontroller;

import controller.CalculatorData;
import model.View;

public interface Controller {

    void setCalculatorData(CalculatorData data);

    void registerObserver(View view);
}
