package model;

import java.util.List;

public interface DataManager {

    List<Double> parseData();

    void checkDataWidth();

    boolean isAreaOn();

    boolean haveWidth();

    String getDataAssortment();

    String getDataType();

    String getDataNumber();

    void setIsComplexArea(boolean status);

    void setWidthData(String textValue);

    void setLengthData(String textValue);

    void setNumber(String selectedNumber);

    void setAssortment(String selectedAssortment);

    void setType(String selectedType);
}
