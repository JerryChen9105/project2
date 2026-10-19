

import org.junit.jupiter.api.Test;
import  static org.junit.jupiter.api.Assertions.*;



public class CalculatorJUT {

    @Test
    public void add_2PositiveNumber_returnSum() {
        Calculator jutCalculator = new Calculator();
        int actualResult = jutCalculator.add(10, 12);
        int expectedResult = 22;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void subtract_2PositiveNumber_returnSum() {
        Calculator jutCalculator = new Calculator();
        int actualResult = jutCalculator.subtract(10, 12);
        int expectedResult = -2;
        assertEquals(expectedResult, actualResult);
    }

}
