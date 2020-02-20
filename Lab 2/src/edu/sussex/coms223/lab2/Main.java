package edu.sussex.coms223.lab2;

public class Main {

	public static void main(String[] args) {
		Map<String, Integer> m = new BinaryTreeMap<>();
		m.put("1", 1);
		m.print();
		m.put("3", 3);
		m.print();
		m.put("2", 2);
		m.print();
	}

}
