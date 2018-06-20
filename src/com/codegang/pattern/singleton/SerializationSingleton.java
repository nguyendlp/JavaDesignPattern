package com.codegang.pattern.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class SerializationSingleton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4835218177063433153L;

	private static SerializationSingleton instance = new SerializationSingleton();

	private SerializationSingleton() {
		if (instance != null)
			throw new IllegalStateException("Already created.");

	}

	public static SerializationSingleton getInstance() {
		return instance;
	}

	private Object readResolve() throws ObjectStreamException {
		return instance;
	}

	private Object writeReplace() throws ObjectStreamException {
		return instance;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singleton, cannot be clonned");
	}

	private static Class getClass(String classname) throws ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null)
			classLoader = SerializationSingleton.class.getClassLoader();
		return (classLoader.loadClass(classname));
	}

}
