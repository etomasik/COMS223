package edu.sussex.coms223.lab3;

/**
 * The Interface PolynomialEvaluator. The interface declares an . The interface
 * also declares a single evaluate method that returns the value of y given
 */
public interface PolynomialEvaluator {

	/**
	 * The Class Instrumentation is used to collect both the predicted and measured
	 * count of basic multiplication operations required to evaluate a given
	 * polynomial.
	 */
	class Instrumentation {
		int predictedBasicOperations;
		int measuredBasicOperations;
	}

	/**
	 * Evaluate and return the y value of a given nth degree polynomial equation.
	 *
	 * @param terms the coefficients and constant term of the polynomial equation.
	 *              Missing terms should contain zeros. For example the polynomial y
	 *              = 7x**3 + 2x should have the terms (7, 0, 2, 0).
	 * @param x     the x value
	 * @param inst  the instrumentation structure used to capture both the predicted
	 *              and measured count of basic multiplication operations.
	 * @return the computed value of y given the terms of the equation and the value
	 *         of x
	 */
	int evaluate(int[] terms, int x, Instrumentation inst);
}
