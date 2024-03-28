package graphicaluserinterface;

import businesslogic.Operations;
import datamodels.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements ActionListener {

    private View view;
    private Operations operations = new Operations();

    public Controller(View v) {
        this.view = v;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("COMPUTE")) {

            String polynomial1Str = view.getFirstNumberTextField().getText();
            String polynomial2Str = view.getSecondNumberTextField().getText();

            Polynomial p1 = createPolynomialFromString(polynomial1Str);
            Polynomial p2 = createPolynomialFromString(polynomial2Str);

            String operation = (String) view.getOperationsComboBox().getSelectedItem();
            Polynomial result = null;

            switch (operation) {
                case "Add":
                    result = Operations.add(p1, p2);
                    break;
                case "Subtract":
                    result = Operations.subtract(p1, p2);
                    break;
                case "Multiply":
                    result = Operations.multiply(p1, p2);
                    break;
                case "Derive":
                    result = Operations.derivative(p1);
                    break;
                case "Integrate":
                    result = Operations.integrate(p1);
                    break;
            }

            if (result != null) {
                view.getResultValueLabel().setText(result.toString());
            }
        }
    }



    private Polynomial createPolynomialFromString(String input) {
        Polynomial polynomial = new Polynomial();
        input = input.trim();

        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String termStr = matcher.group(1);
            Term term = parseTerm(termStr);
            polynomial.addTerm(term.exponent, term.coefficient);
        }

        return polynomial;
    }

    private Term parseTerm(String termStr) {
        double coefficient = 1.0;
        int exponent = 1;

        if (termStr.contains("x")) {
            coefficient = extractCoefficient(termStr);
            exponent = extractExponent(termStr);
        } else {
            coefficient = Double.parseDouble(termStr);
            exponent = 0;
        }

        return new Term(coefficient, exponent);
    }

    private double extractCoefficient(String termStr) {

        double sign = 1.0;
        if (termStr.startsWith("-")) {
            termStr = termStr.substring(1);
            sign = -1.0;
        } else if (termStr.startsWith("+")) {
            termStr = termStr.substring(1);
            sign = 1.0;
        }

        String coeffString = "";
        for (int i = 0; termStr.charAt(i) != 'x' ; i++) {
            coeffString += termStr.charAt(i);
        }

        return coeffString.isEmpty() ? 1.0 : (double)(sign * Double.parseDouble(coeffString));
    }

    private int extractExponent(String termStr) {
        if (!termStr.contains("^")) {
            return 1;
        }

        String exponentString = "";
        for (int i = termStr.length() - 1; termStr.charAt(i) != '^'; i--) {
            exponentString = termStr.charAt(i) + exponentString; // Build string in reverse
        }

        return Integer.parseInt(exponentString);
    }


}

// Simple Term class
class Term {
    public double coefficient;
    public int exponent;

    public Term(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }
}
