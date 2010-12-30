package com.tkorg.entities;

import java.util.ArrayList;

/**
 *
 * @author danhit
 */
public class MyStack {
    public static ArrayList < String > stack;

    public void init() {
        stack = new ArrayList < String >();
    }

    public static void add(String className) {
	stack.add(className);
    }

    public static String get() {
	String className = "";

	className = stack.get(0);

	for (int i = 1; i < stack.size(); i++) {
		stack.set(i - 1, stack.get(i));
	}
	stack.remove(stack.size() - 1);

	return className;
    }
}
