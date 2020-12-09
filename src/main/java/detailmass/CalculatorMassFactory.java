package detailmass;

import model.CalculatorFactory;
import model.AbstractMassCalculator;

public class CalculatorMassFactory implements CalculatorFactory {

    @Override
    public AbstractMassCalculator createMassCalculator(String assortment, String type) {
        switch(assortment){
            case "Лист":
            case "Другое":
                return selectedType(type);
            case "Швеллер":
            case "Уголок":
            case "Двутавр":
                return new AssortmentMassCalculator();
            default:
                System.out.println("test null factory");
                return null;
        }
    }

    private AbstractMassCalculator selectedType(String type){
        switch (type){
            case "рифленая(ромб)":
                return new RiffledSteelSheetMassCalculator();
            case "тонколистовая":
            case "толстолистовая":
                return new SheetSteelMassCalculator();
            case "Круг":
                return new CircleSteelMassCalculator();
            case "Квадрат":
                return new SquareSteelMassCalculator();
            case "Резиновая пластина":
                return new SheetRubberMassCalculator();
            default:
                System.out.println("test null type factory");
                return null;
        }
    }
}