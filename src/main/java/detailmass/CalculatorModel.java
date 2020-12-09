package detailmass;

import controller.ViewSubject;

import java.util.Queue;

public interface CalculatorModel extends ViewSubject {

    void setData(Queue<String> detailData);

    void calculation();
}
