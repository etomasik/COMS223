package edu.sussex.coms223.lab3;

/**
 * The Class Horner implements the PolynomialEvaluator interface and uses an
 * efficient approach by applying Horner's Rule. Horner's Rule approach requires
 * n basic multiplication operations to calculate the value of y given x and the
 * terms of an nth degree polynomial equation.
 */
public class Horner implements PolynomialEvaluator {

	/**
	 * Evaluate and return the value of y given an nth degree polynomial equation
	 * and the value of x.
	 *
	 * @param terms the coefficients of each term of an degree terms.length - 1
	 *              polynomial equation.
	 * @param x     the value of x
	 * @param inst  a structure to return both the predicted and measured number of
	 *              basic multiplication operations required to evaluate a
	 *              polynomial of degree terms.length - 1.
	 * @return the value of y
	 */
	@Override
	public int evaluate(int[] terms, int x, Instrumentation inst) {
		// Declare a constant integer value degree with the value terms.length - 1.
		final int degree = terms.length - 1;

		// The predicted number of basic multiplications of the Horner's Rule method is
		// n. Initialize the counter of measured basic operations to 0.
		inst.predictedBasicOperations = degree;
		inst.measuredBasicOperations = 0;

		// Declare and initialize to 0 an integer variable sum used to accumulate the
		// sum of each evaluated term. Initialize the sum to the constant term.
		int sum = terms[0];

		// Iterate through each non-constant term and accumulate the sum by
		// multiplying the sum by x and adding the term coefficient.
		for (int i = 1; i < terms.length; i++) {
			sum = sum * x + terms[i];
			inst.measuredBasicOperations++;
		}

		// Return the computed value of y.
		return sum;
	}

}
