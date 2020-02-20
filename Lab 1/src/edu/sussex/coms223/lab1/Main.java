package edu.sussex.coms223.lab1;

public class Main {

	public static void main(String[] args) {
		System.out.println("starting...");
		List<String> l = new ArrayList<>();
		l.add("Hello");
		l.add("World");
		for (int i = 0; i < l.size(); i++)
			System.out.println(l.get(i));
	}

}
