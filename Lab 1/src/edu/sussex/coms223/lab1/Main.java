package edu.sussex.coms223.lab1;

public class Main {

	public static void main(String[] args) {
		List<String> l = new LinkedList<>();
		l.add("0");
		l.add("1");
		l.add("2");
		for (int i = 0; i < l.size(); i++)
			System.out.println(l.get(i));
		l.remove("1");
		System.out.println("remove 1");
		for (int i = 0; i < l.size(); i++)
			System.out.println(l.get(i));
	}

}
