package org.Programs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IterateHashmapAndAddToSet {

	public static void main(String[] args) {

		Map<Integer, String> map = new HashMap<Integer, String>();

		Set<Integer> keySet = new HashSet<Integer>();

		Set<String> valueSet = new HashSet<String>();

		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");
		map.put(6, "six");
		map.put(7, "seven");

		Set<Entry<Integer, String>> entrySet = map.entrySet();

		for (Entry<Integer, String> values : entrySet) {

			System.out.println(values);

			Integer keys = values.getKey();

			keySet.add(keys);

			String value = values.getValue();

			valueSet.add(value);

		}

		// Keys and Values added in set

		System.out.println("Keys added in set: " + keySet);

		System.out.println("Values added in set" + valueSet);

	}
}
