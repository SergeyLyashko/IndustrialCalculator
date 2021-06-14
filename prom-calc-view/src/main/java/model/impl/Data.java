package model.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
class Data {

    @Setter
    @Getter
    private String assortment;
    @Setter
    @Getter
    private String type;
    @Setter
    @Getter
    private String number;
    @Getter
    @Setter
    private String widthData;
    @Getter
    @Setter
    private String lengthData;
    @Getter
    @Setter
    private boolean isComplexArea;
    @Getter
    @Setter
    private boolean isDetailHaveWidth;

    Data(){}

    @Override
    public String toString() {
        return "Data{" +
                "assortment='" + assortment + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", widthData='" + widthData + '\'' +
                ", lengthData='" + lengthData + '\'' +
                ", isComplexArea=" + isComplexArea +
                ", isDetailHaveWidth=" + isDetailHaveWidth +
                '}';
    }
}
