import businesslogic.Operations;
import datamodels.Polynomial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void testAddition() {
        Polynomial p1 = new Polynomial();
        p1.addTerm(2, 3.0);
        p1.addTerm(0, 5.0);

        Polynomial p2 = new Polynomial();
        p2.addTerm(1, -2.0);
        p2.addTerm(0, 1.0);

        Polynomial actualResult = Operations.add(p1, p2);
        String expectedResultStr = "6.0 + -2.0x^1 + 3.0x^2";

        assertEquals(expectedResultStr, actualResult.toString());

        p1 = new Polynomial();
        p1.addTerm(3, 2.0);  // Highest degree in p1
        p1.addTerm(1, 1.0);

        p2 = new Polynomial();
        p2.addTerm(2, 5.0);
        p2.addTerm(0, -3.0);   // Highest degree in p2

        actualResult = Operations.add(p1, p2);
        expectedResultStr = "-3.0 + 1.0x^1 + 5.0x^2 + 2.0x^3";

        assertEquals(expectedResultStr, actualResult.toString());


        p1 = new Polynomial();
        p1.addTerm(2, 1.0);
        p1.addTerm(0, 0.0); // Zero coefficient term

        p2 = new Polynomial();
        p2.addTerm(1, 4.0);
        p2.addTerm(0, -2.0);

        actualResult = Operations.add(p1, p2);
        expectedResultStr = "-2.0 + 4.0x^1 + 1.0x^2";

        assertEquals(expectedResultStr, actualResult.toString());

    }

    @Test
    void testSubtraction() {
        // Test 1: Positive coefficients
        Polynomial p1 = new Polynomial();
        p1.addTerm(1, 4.0);
        p1.addTerm(0, 2.5);

        Polynomial p2 = new Polynomial();
        p2.addTerm(1, 1.0);
        p2.addTerm(0, -1.0);

        assertEquals("3.5 + 3.0x^1", Operations.subtract(p1, p2).toString());

        // Test 2: Negative coefficients
        p1 = new Polynomial();
        p1.addTerm(2, -2.0);
        p1.addTerm(0, 3.0);

        p2 = new Polynomial();
        p2.addTerm(1, -1.0);
        p2.addTerm(0, 1.5);

        assertEquals("1.5 + 1.0x^1 + -2.0x^2", Operations.subtract(p1, p2).toString());

        // Test 3: Zero polynomial
        p1 = new Polynomial(); // Zero polynomial
        p2 = new Polynomial();
        p2.addTerm(3, 2.0);
        p2.addTerm(1, -5.0);

        assertEquals("-5.0x^1 + 2.0x^3", Operations.subtract(p2, p1).toString());
    }

    @Test
    void testMultiplication() {
        // Test 1: Simple polynomials
        Polynomial p1 = new Polynomial();
        p1.addTerm(1, 2.0);
        p1.addTerm(0, 1.0);

        Polynomial p2 = new Polynomial();
        p2.addTerm(1, 1.0);
        p2.addTerm(0, -3.0);

        assertEquals("-3.0 + -5.0x^1 + 2.0x^2", Operations.multiply(p1, p2).toString());

        // Test 2: Zero polynomial
        p1 = new Polynomial(); // Zero polynomial
        p2 = new Polynomial();
        p2.addTerm(2, 3.0);
        p2.addTerm(0, -2.0);

        assertEquals("", Operations.multiply(p1, p2).toString());

        // Test 3: Larger degrees
        p1 = new Polynomial();
        p1.addTerm(2, 3.0);
        p1.addTerm(0, 1.0);
        //System.out.printf(p1.toString() + "\n");
        p2 = new Polynomial();
        p2.addTerm(3, 2.0);
        p2.addTerm(1, 2.0);
        //System.out.printf(p2.toString() + "\n");
        assertEquals("2.0x^1 + 8.0x^3 + 6.0x^5", Operations.multiply(p1, p2).toString());
    }

    @Test
    void testDerivative() {
        // Test 1: Simple polynomial
        Polynomial p1 = new Polynomial();
        p1.addTerm(2, 3.0);
        p1.addTerm(1, -2.0);
        p1.addTerm(0, 4.0);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addTerm(1, 6.0);
        expectedResult.addTerm(0, -2.0);

        assertEquals("-2.0 + 6.0x^1", Operations.derivative(p1).toString());

        // Test 2: Zero Polynomial
        Polynomial p2 = new Polynomial(); // Zero polynomial

        Polynomial expectedResult2 = new Polynomial(); // Empty polynomial

        assertEquals("", Operations.derivative(p2).toString());

        // Test 3: Higher order polynomial
        Polynomial p3 = new Polynomial();
        p3.addTerm(3, 2.0);
        p3.addTerm(2, -5.0);
        p3.addTerm(0, 3.0);

        Polynomial expectedResult3 = new Polynomial();
        expectedResult3.addTerm(2, 6.0);
        expectedResult3.addTerm(1, -10.0);

        assertEquals("-10.0x^1 + 6.0x^2", Operations.derivative(p3).toString());
    }

    @Test
    void testIntegrate() {
        // Test 1: Simple polynomial
        Polynomial p1 = new Polynomial();
        p1.addTerm(1, 2.0);
        p1.addTerm(0, -3.0);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addTerm(2, 1.0);
        expectedResult.addTerm(1, -3.0);
        expectedResult.addTerm(0, 0.0); // Arbitrary constant

        assertEquals("-3.0x^1 + 1.0x^2", Operations.integrate(p1).toString());

        // Test 2: Zero Polynomial
        Polynomial p2 = new Polynomial();

        Polynomial expectedResult2 = new Polynomial();
        expectedResult2.addTerm(0, 0.0); // Constant term

        assertEquals("", Operations.integrate(p2).toString());

        // Test 3: Higher order polynomial
        Polynomial p3 = new Polynomial();
        p3.addTerm(2, 4.0);
        p3.addTerm(0, 1.0);

        Polynomial expectedResult3 = new Polynomial();
        expectedResult3.addTerm(3, 4.0 / 3.0);
        expectedResult3.addTerm(1, 0.5);
        expectedResult3.addTerm(0, 0.0);

        assertEquals("1.0x^1 + 1.3333333333333333x^3", Operations.integrate(p3).toString());
    }
}


