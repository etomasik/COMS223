package edu.sussex.coms223.lab3;

/**
 * The Class BruteForce implements the PolynomialEvaluator interface and uses an
 * inefficient brute force approach. The brute force approach requires n**2/2 -
 * n/2 + n basic multiplication operations to calculate the value of y given x
 * and the terms of an nth degree polynomial equation.
 */
public class BruteForce implements PolynomialEvaluator {

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

		// The predicted number of basic multiplications of the brute force method is
		// n**2/2 - n/2 + n. Initialize the counter of measured basic operations to 0.
		inst.predictedBasicOperations = (int) Math.pow(degree, 2) / 2 - degree / 2 + degree;
		inst.measuredBasicOperations = 0;

		// Declare and initialize to 0 an integer variable sum used to accumulate the
		// sum of each evaluated term.
		int sum = 0;

		// Iterate through each term of the polynomial except the constant term at
		// terms[terms.length - 1].
		for (int i = 0; i < terms.length - 1; i++) {
			// Declare and initialize integer variables for the exponent and
			// coefficient of the ith term.
			final int exp = terms.length - i - 1;
			final int coeff = terms[i];

			// Declare and initialize an accumulator variable with x which will be
			// multiplied by x by the size of the terms exponent.
			int accum = x;

			// Iterate based on the size of the term exponent - 1 and multiply the
			// accumulated value by x each iteration. So for x**3, exp would be 3 and
			// the accumulated value would start with the value x and this loop would
			// iterate twice giving us x*x*x or x**3.
			for (int j = 0; j < exp - 1; j++) {
				accum *= x;
				inst.measuredBasicOperations++;
			}

			// Add to the running sum the product of the term coefficient and the
			// accumulated power of x.
			sum += coeff * accum;
			inst.measuredBasicOperations++;
		}

		// Return the running sum plus the final constant term.
		return sum + terms[terms.length - 1];
	}

}
