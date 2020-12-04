package model;

import java.util.Arrays;

public class Detail {

    private final boolean isArea;
    private final double dataBaseValue;
    private final double reduceValue;

    public Detail(boolean isArea, double dataBaseValue, double...values){
        this.isArea = isArea;
        this.dataBaseValue = dataBaseValue;
        this.reduceValue = Arrays.stream(values).reduce(1, (acc, element) -> acc * element);
    }

    public double getFieldsValue() {
        return reduceValue;
    }

    public double getDataBaseValue(){
        return dataBaseValue;
    }

    // TODO ???? зачем
    public boolean isArea() {
        return isArea;
    }
}
