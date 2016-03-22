package com.xname.appname;

import org.slf4j.MDC;

// TODO: Auto-generated Javadoc
/**
 * The Class LogUtil.
 */
public class LogUtil {

	/**
	 * The Enum SERVICE.
	 */
	public static enum SERVICE {

		/** The upload. */
		UPLOAD,
		/** The download. */
		DOWNLOAD
	};

	/**
	 * The Enum KEYS.
	 */
	public static enum KEYS {

		/** The examid. */
		EXAMID,
		/** The api. */
		API,
		/** The response. */
		RESPONSE
	};

	/**
	 * Sets the log environment variables.
	 */
	// OVERRIDE THIS TO YOUR NEEDS
	/*
	 * public static void setEnvironment(OperatingSystem env, SERVICE srv,
	 * String api) { MDC.put("SERVICE", srv.toString()); MDC.put("ENV",
	 * env.toString()); MDC.put("API", api); }
	 */

	public static void clear() {
		MDC.clear();
	}

	/**
	 * Can add the context for the logger here.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public static void putContext(KEYS key, String value) {
		MDC.put(key.toString(), value);
	}

	/**
	 * Remove the log context.
	 *
	 * @param key
	 *            the key
	 */
	public static void removeContext(KEYS key) {
		MDC.remove(key.toString());
	}

}
