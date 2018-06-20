package com.codegang.pattern.singleton;

// This code is used to create Singleton in a multi-threaded environment.
// If multi-threads concurrently accessing the class, thread th1 gives the first call to getInstance() method,
// it checks if the static variable "instance" is null and get interrupted due to some reason. Another thread th2  
// calls the getInstance() method successfully passes the if check and instantiates the object. Then thread th1
// get awake and it also creates the object. At this time, there would be two object of this class.

// To avoid this, we will use the synchronized to the getInstance() method.

/**
*
* @author nguyendlp
*/

public class ThreadSafeSingleton {
	
	private volatile static ThreadSafeSingleton instance = null;

	private ThreadSafeSingleton() {
	}
	
	
	// The easier way to create a thread-safe singleton class is to make the global access method
	// synchronized, so that only one thread can execute this method at a time.
	// But it reduces the performance because of cost associated with the synchronized method, 
	// although we need it only for the first few threads who might create the separate instances
	// This way, we only synchronized the first time.
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
	
	public void doSomeThing() 
	{
		System.out.println("do some thing.");
	}
	

}

class ThreadSafeSingletonEx{
	
	public static void main(String[] args) {
		
		System.out.println("======ThreadSafeSingleton=====");
		ThreadSafeSingleton s1 = ThreadSafeSingleton.getInstance();
		ThreadSafeSingleton s2 = ThreadSafeSingleton.getInstance();
		
		if(s1 == s2) {
			System.out.println("s1 and s2 are same instance"); 
		}
	}
}