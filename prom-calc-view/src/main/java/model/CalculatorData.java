package model;

import java.util.List;
import java.util.Map;

public interface CalculatorData {

    List<String> getFieldsData();

    Map<String, String> getSelectedMenuItems();

    void add(Map<String, String> selectedMenuItems);
}
