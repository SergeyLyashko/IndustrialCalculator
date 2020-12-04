package model;

import java.util.Arrays;

public class Detail {

    private final double dataBaseValue;
    private final double reduceValue;

    public Detail(double dataBaseValue, double...values){
        this.dataBaseValue = dataBaseValue;
        this.reduceValue = Arrays.stream(values).reduce(1, (acc, element) -> acc * element);
    }

    public double getFieldsValue() {
        return reduceValue;
    }

    public double getDataBaseValue(){
        return dataBaseValue;
    }
}
