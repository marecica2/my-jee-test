package org.bmsource;

public class Fibonacci {
	public static void main(String... args) {
		int iterations = 0;
		print(0, 1, iterations);
	}

	public static void print(int x1, int x2, int n) {
		int x3 = x1 + x2;
		System.err.print(x1 + ", ");
		while (n <= 4) {
			print(x2, x3, n++);
		}
	}
}
