package datamodels;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    private HashMap<Integer, Number> terms;

    // Constructor
    public Polynomial() {
        terms = new HashMap<>();
    }

    // Adds or updates a term in the polynomial
    public void addTerm(int exponent, Number coefficient) {
        terms.put(exponent, coefficient);
    }

    // Provides a formatted string representation of the polynomial
    @Override
    public String toString() {
        StringBuilder representation = new StringBuilder();

        for (Map.Entry<Integer, Number> termEntry : terms.entrySet()) {
            if (termEntry.getValue().doubleValue() != 0) {
                representation.append(termEntry.getValue()).append("x^").append(termEntry.getKey()).append(" + ");
            }
        }

        String formattedString = representation.toString();

        // Clean up formatting (if necessary)
        if (formattedString.endsWith(" + ")) {
            formattedString = formattedString.substring(0, formattedString.length() - 3);
        }

        if (formattedString.contains("x^0")) {
            formattedString = formattedString.replace("x^0", "");
        }

        return formattedString;
    }

    // Returns the highest exponent present in the polynomial
    // Returns the highest exponent present in the polynomial
    public int getDegree() {
        return terms.isEmpty() ? 0 : Collections.max(terms.keySet());
    }

    // Returns a copy of the underlying collection of terms
    public HashMap<Integer, Number> getTerms() {
        return new HashMap<>(terms);
    }

    // Returns the coefficient for a specific exponent, or 0 if the term is missing
    public Number getCoefficientForExponent(int exponent) {
        return terms.getOrDefault(exponent, 0.0);
    }
}

