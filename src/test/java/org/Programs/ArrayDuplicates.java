package org.Programs;

import java.util.Set;
import java.util.TreeSet;

//Print Array Duplicates

public class ArrayDuplicates {

	public static void main(String[] args) {

		int arr[] = { 1, 2, 3, 1, 0, 2, 4, 5, 7, 4, 8 };

		Set<Integer> set = new TreeSet<Integer>();

		for (int arr1 : arr) {

			boolean status = set.add(arr1);

			if (status == false) {

				System.out.println("Duplicate Value: " + arr1);

			}

		}

		System.out.println(set);

		// Adding element in array after removing duplicates

		int n = set.size();

		int arr2[] = new int[n];

		int i = 0;

		for (int x : set) {

			arr2[i++] = x;
		}

	}

}
