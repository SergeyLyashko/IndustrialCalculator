package details;

import model.CalculatorFactory;
import model.AbstractMassCalculator;

public class CalculatorFactoryImpl implements CalculatorFactory {

    private final String assortment;
    private final String type;

    public CalculatorFactoryImpl(String assortment, String type) {
        this.assortment = assortment;
        this.type = type;
    }

    @Override
    public AbstractMassCalculator createMassCalculator() {
        switch(assortment){
            case "Лист":
            case "Другое":
                return selectedType();
            case "Швеллер":
            case "Уголок":
            case "Двутавр":
                return new AssortmentMassCalculator();
            default:
                System.out.println("test null factory: "+assortment);
                return null;
        }
    }

    private AbstractMassCalculator selectedType(){
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