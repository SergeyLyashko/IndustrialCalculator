package model;

import model.impl.Data;

import java.util.List;

public interface FieldsParser {

    List<Double> parseData(Data data);
}
