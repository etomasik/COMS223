package edu.sussex.coms223.lab3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import edu.princeton.cs.algs4.Polynomial;

class PolynomialEvaluatorTest {

	public static Stream<PolynomialEvaluator> evaluators() {
		return Stream.of(new BruteForce(), new Horner());
	}

	@ParameterizedTest
	@MethodSource("evaluators")
	void testEval0(PolynomialEvaluator evaluator) {
		int[] terms = { 0 };
		int x = 999;
		
		assertEquals(0, evaluator.evaluate(terms, x, new PolynomialEvaluator.Instrumentation()));
	}

	@ParameterizedTest
	@MethodSource("evaluators")
	void testEval4(PolynomialEvaluator evaluator) {
		int[] terms = { 4, -2, 5, 10, -15 };
		int x = 2;
		
		PolynomialEvaluator.Instrumentation inst = new PolynomialEvaluator.Instrumentation();
		
		assertEquals(73, evaluator.evaluate(terms, x, inst));
		
		System.out.print("degree 4: predicted: " + inst.predictedBasicOperations);
		System.out.println(" measured: " + inst.measuredBasicOperations);
	}

	@ParameterizedTest
	@MethodSource("evaluators")
	void testRandom(PolynomialEvaluator evaluator) {
		Random rng = new Random();

		final int degree = rng.nextInt(10);
		int[] terms = new int[degree + 1];
		final int x = rng.nextInt(10);

		for (int j = 1; j < degree; j++)
			terms[j] = rng.nextInt() + 1;

		terms[0] = rng.nextInt();

		Polynomial expected = new Polynomial(terms[0], terms.length - 1);

		for (int j = 1; j < terms.length; j++)
			expected = expected.plus(new Polynomial(terms[j], terms.length - 1 - j));

		PolynomialEvaluator.Instrumentation inst = new PolynomialEvaluator.Instrumentation();

		assertEquals(expected.evaluate(x), evaluator.evaluate(terms, x, inst));
		
		System.out.print("degree " + degree + ": predicted: " + inst.predictedBasicOperations);
		System.out.println(" measured: " + inst.measuredBasicOperations);
	}

}
