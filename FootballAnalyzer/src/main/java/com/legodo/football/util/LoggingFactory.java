package com.legodo.football.util;

import org.slf4j.Logger;

/**
 * Creates <code>org.slf4j.Logger</code>s with the class name of the caller.
 * A helper class "inspired" by a JavaSpecialist article from Heinz Kabbutz.
 * 
 * @author ge_mu
 */
public class LoggingFactory {
	
	/**
	 * Creation method to create Loggers DRYly
	 * 
	 * @return
	 */
	public static Logger make() {
		Throwable t = new Throwable();
		StackTraceElement directCaller = t.getStackTrace()[1];
		return org.slf4j.LoggerFactory.getLogger(directCaller.getClassName());
	}
}
