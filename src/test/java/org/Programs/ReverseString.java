package org.Programs;

import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {

		String reverse = "";

		Scanner s = new Scanner(System.in);

		System.out.println("Enter String: ");

		String stringInput = s.next();

		for (int i = stringInput.length() - 1; i >= 0; i--) {

			char character = stringInput.charAt(i);

			reverse = reverse + character;

		}

		System.out.println("Given String:" + stringInput);

		System.out.println("Reversed String: " + reverse);

	}
}
