package calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestCalculator {

    private Calculator calculator;

    @BeforeAll
    static void initBeforeAll() {
        System.out.println("Before all called");
    }

    @BeforeEach
    void init() {
        System.out.println("before each called");
        calculator = new Calculator();
    }

    @Test
    @DisplayName("test for sum")
    void testSum() {
        //GIVEN
        int first = 4;
        int second = 5;
        String operation = "+";

        String expectedResult = "9.00";

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);

        //THEN
        assertEquals(expectedResult, actualResult);
        //MatcherAssert.assertThat(actualResult, Matchers.is(expectedResult));
    }

    @Test
    @DisplayName("Test for division")
    void testDivision() {
        //GIVEN
        int first = 1;
        int second = 6;
        String operation = "/";
        String expectedResult = "0.17";
        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("test for minus")
    void testMinus() {
        //GIVEN
        int first = 7;
        int second = 5;
        String operation = "-";

        String expectedResult = "2.00";

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);

        //THEN
        assertEquals(expectedResult, actualResult);

    }

    @Test
    @DisplayName("test for multiply")
    void testMultiply() {
        //GIVEN
        int first = 5;
        int second = 7;
        String operation = "*";

        String expectedResult = "35.00";

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);

        //THEN
        assertEquals(expectedResult, actualResult);
    }



    @ParameterizedTest
    @ValueSource(ints = {1,3,Integer.MAX_VALUE})
     void testSum_param(int first) {
        //GIVEN
        int second = 6;
        String operation = "+";
        String expectedResult = prepareExpectedResult(first, second);

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    private String prepareExpectedResult(int first, int second) {
        double expectedResultDouble = first + second;
        String expectedResult = String.format("%1$,.2f", expectedResultDouble);
        return expectedResult;
    }

    @ParameterizedTest
    //@CsvSource(value = {"+:12.00", "/:1.00"}, delimiter = ':')
    @CsvFileSource(resources = "/testData.csv")
    void testSum_paramCsv(String operation, String expectedResult) {
        //GIVEN
        int second = 6;
        int first = 6;

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }



    @ParameterizedTest
    @MethodSource("provideValidData")
    void testSum_paramMethod(int first, int second, String operation, String expectedResult) {
        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideValidData() {
        return Stream.of(
                Arguments.of(1, 2, "+", "3.00"),
                Arguments.of(2,2,"/","1.00"),
                Arguments.of(30, 2, "*", "60.00"),
                Arguments.of(8, 9, "-", "-1.00")
        );
    }



    @TestFactory
    Iterable<DynamicTest> dynamicTestsWithIterable() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Sum test",
                        () -> assertEquals("2.00", calculator.calculate(1, 1, "+"))),
                DynamicTest.dynamicTest("Divide Test",
                        () -> assertEquals("0.00", calculator.calculate(0, 1, "/"))),
        DynamicTest.dynamicTest("Minus Test",
                () -> assertEquals("7.00", calculator.calculate(15, 8, "-"))),
        DynamicTest.dynamicTest("Multiply Test",
                () -> assertEquals("39.00", calculator.calculate(13, 3, "*"))));

    }

    @Test
    void testsIllegalArgumentException() {
        // GIVEN
        int first = 10;
        int second = 0;
        String operation = "/";
String expectedExceptionMessage = "I can not divide by 0.";
        //WHEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(first, second, operation));

    //THEN
        assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());
    }

    @Test
    void testIllegalStateException() {
        //GIVEN
        int first = 2;
        int second = 3;
        String operation = "1";
        String expectedExceptionMessage = "Operation '1' is not supported";

        //WHEN
IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
        () -> calculator.calculate(first, second, operation));
        //THEN
assertEquals(expectedExceptionMessage, illegalStateException.getMessage());

    }
}
