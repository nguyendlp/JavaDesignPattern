package com.codegang.pattern.singleton;

public class ThreadSafeSingleton {
	
	private volatile static ThreadSafeSingleton instance = null;

	private ThreadSafeSingleton() {
	}
	
	
	// The easier way to create a thread-safe singleton class is to make the global access method
	// synchronized, so that only one thread can execute this method at a time.
	// But it reduces the performance because of cost associated with the synchronized method, 
	// although we need it only for the first few threads who might create the separate instances
	public static synchronized ThreadSafeSingleton getInstance() {
		if (instance == null) {
			instance = new ThreadSafeSingleton();
		}
		return instance;
	}
	// To avoid this extra overhead every time, double checked locking principle is used. 
	// In this approach, the synchronized block is used inside the if condition with an additional 
	// check to ensure that only one instance of singleton class is created.
	public static ThreadSafeSingleton getInstanceUsingDoubleLocking() {
		if (instance == null) {
			//increasing performance
			synchronized (ThreadSafeSingleton.class) {
				if (instance == null) {
					instance = new ThreadSafeSingleton();
				}
			}
		}
		return instance;
	}
}
