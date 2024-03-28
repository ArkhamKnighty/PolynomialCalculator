package businesslogic;

import datamodels.Polynomial;

public class Operations {

    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();

        // Iterate through coefficients of p1
        for (int exponent : p1.getTerms().keySet()) {
            double coefficient = p1.getCoefficientForExponent(exponent).doubleValue();
            result.addTerm(exponent, coefficient);
        }

        // Iterate through coefficients of p2
        for (int exponent : p2.getTerms().keySet()) {
            double coefficient = p2.getCoefficientForExponent(exponent).doubleValue();
            double existingCoefficient = result.getCoefficientForExponent(exponent).doubleValue();
            result.addTerm(exponent, coefficient + existingCoefficient);
        }

        return result;
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();

        for (int exponent1 : p1.getTerms().keySet()) {
            for (int exponent2 : p2.getTerms().keySet()) {
                int newExponent = exponent1 + exponent2;
                double newCoefficient = p1.getCoefficientForExponent(exponent1).doubleValue() *
                        p2.getCoefficientForExponent(exponent2).doubleValue();

                double existingCoefficient = result.getCoefficientForExponent(newExponent).doubleValue();
                result.addTerm(newExponent, existingCoefficient + newCoefficient);
            }
        }

        return result;
    }

    // Method to subtract two polynomials
    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();

        // Iterate through coefficients of p1
        for (int exponent : p1.getTerms().keySet()) {
            double coefficient = p1.getCoefficientForExponent(exponent).doubleValue();
            result.addTerm(exponent, coefficient);
        }

        // Iterate through coefficients of p2 (subtract coefficients)
        for (int exponent : p2.getTerms().keySet()) {
            double coefficient = p2.getCoefficientForExponent(exponent).doubleValue();
            double existingCoefficient = result.getCoefficientForExponent(exponent).doubleValue();
            result.addTerm(exponent, existingCoefficient - coefficient);
        }

        return result;
    }

    public static Polynomial derivative(Polynomial p) {
        Polynomial result = new Polynomial();

        for (int exponent : p.getTerms().keySet()) {
            double coefficient = p.getCoefficientForExponent(exponent).doubleValue();

            if (exponent != 0) {
                int newExponent = exponent - 1;
                double newCoefficient = exponent * coefficient;
                result.addTerm(newExponent, newCoefficient);
            }
        }

        return result;
    }

    public static Polynomial integrate(Polynomial p) {
        Polynomial result = new Polynomial();

        for (int exponent : p.getTerms().keySet()) {
            double coefficient = p.getCoefficientForExponent(exponent).doubleValue();

            int newExponent = exponent + 1;
            double newCoefficient = coefficient / (newExponent);
            result.addTerm(newExponent, newCoefficient);
        }

        return result;
    }


}
