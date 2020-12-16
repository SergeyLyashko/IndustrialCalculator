package model;

import controller.Detail;

import java.util.Arrays;

class DetailImpl implements Detail {

    private final double dataBaseValue;
    private final double reduceValue;

    DetailImpl(double dataBaseValue, double...values){
        this.dataBaseValue = dataBaseValue;
        this.reduceValue = Arrays.stream(values).reduce(1, (acc, element) -> acc * element);
    }

    @Override
    public double getFieldsValue() {
        return reduceValue;
    }

    @Override
    public double getDataBaseValue(){
        return dataBaseValue;
    }
}
