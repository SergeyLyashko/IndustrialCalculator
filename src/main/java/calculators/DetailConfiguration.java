package calculators;

import model.AbstractMassCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DetailConfiguration {

    @Bean(name = {"Лист тонколистовая", "Лист толстолистовая"})
    public AbstractMassCalculator sheetSteelMassCalculator(){
        return new SheetSteelMassCalculator();
    }

    @Bean(name = "Лист рифленая(ромб)")
    public AbstractMassCalculator riffledSteelSheetMassCalculator(){
        return new RiffledSteelSheetMassCalculator();
    }

    @Bean(name =
            {"Швеллер с уклоном", "Швеллер параллельные", "Швеллер экономичные", "Швеллер специальные", "Швеллер легкой серии",
                    "Уголок равнополочный", "Уголок неравнополочный",
                    "Двутавр нормальный", "Двутавр широкополочный", "Двутавр колонный"})
    public AbstractMassCalculator assortmentMassCalculator(){
        return new AssortmentMassCalculator();
    }

    @Bean(name = "Другое Круг")
    public AbstractMassCalculator circleSteelMassCalculator(){
        return new CircleSteelMassCalculator();
    }

    @Bean(name = "Другое Резиновая пластина")
    public AbstractMassCalculator sheetRubberMassCalculator(){
        return new SheetRubberMassCalculator();
    }

    @Bean(name = "Другое Квадрат")
    public AbstractMassCalculator squareSteelMassCalculator(){
        return new SquareSteelMassCalculator();
    }
}
