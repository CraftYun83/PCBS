package com.fourmc.computers.functions;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCollection<E> {
	
	private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
	private double total = 0;

	public void add(double weight, E result) {
		if (weight <= 0 || map.containsValue(result))
			return;
	    total += weight;
	    map.put(total, result);
	}

	public String next() {
		double value = ThreadLocalRandom.current().nextDouble() * total;
	    return (String) map.ceilingEntry(value).getValue();
	  
	  }
}