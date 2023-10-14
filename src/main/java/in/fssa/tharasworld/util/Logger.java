package in.fssa.tharasworld.util;

import java.util.Set;

import in.fssa.tharasworld.dto.ProductDetailDTO;

public class Logger {

	/**
	 * Logs an error message along with the exception's stack trace.
	 *
	 * @param e The exception for which the error message and stack trace will be
	 *          printed.
	 */

	public static void error(Exception e) {

		e.printStackTrace();

	}

	/**
	 * Logs an informational message to the console.
	 *
	 * @param msg The informational message to be logged.
	 */

	public static void info(String msg) {

		System.out.println(msg);

	}

	public static void info(Set<ProductDetailDTO> detail) {

		System.out.println(detail);
		
	}

}
