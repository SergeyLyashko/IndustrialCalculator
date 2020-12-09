package model;

import java.util.Arrays;

class Detail {

    private final double dataBaseValue;
    private final double reduceValue;

    Detail(double dataBaseValue, double...values){
        this.dataBaseValue = dataBaseValue;
        this.reduceValue = Arrays.stream(values).reduce(1, (acc, element) -> acc * element);
    }

    double getFieldsValue() {
        return reduceValue;
    }

    double getDataBaseValue(){
        return dataBaseValue;
    }
}
