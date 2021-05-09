package org.Programs;

import java.util.Scanner;

public class CheckPalindrome {

	public static void main(String[] args) {

		int reverse = 0;

		int remainder = 0;

		Scanner s = new Scanner(System.in);

		System.out.println("Enter Number: ");

		int inputNumber = s.nextInt();

		int number = inputNumber;

		while (number > 0) {

			remainder = number % 10;

			reverse = (reverse * 10) + remainder;

			number = number / 10;

		}

		if (inputNumber == reverse) {

			System.out.println("Number is Palindrome");

		} else {

			System.out.println("Number is not Panindrome");
		}

	}
}
