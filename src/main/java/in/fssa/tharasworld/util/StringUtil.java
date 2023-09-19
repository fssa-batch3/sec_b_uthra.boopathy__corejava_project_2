package in.fssa.tharasworld.util;

import in.fssa.tharasworld.exception.ValidationException;

public class StringUtil {

	/**
	 * Validates a string input, throwing a ValidationException if it is null or
	 * empty.
	 *
	 * @param input     The string to be validated.
	 * @param inputName The name or description of the input, used in the exception
	 *                  message.
	 * @throws ValidationException If the input is null or empty, a
	 *                             ValidationException is thrown.
	 */

	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {

		if (input == null || "".equals(input.trim())) {

			throw new ValidationException(inputName.concat(" cannot be null or empty"));

		}

	}

	/**
	 * Checks if a string is valid (not null or empty).
	 *
	 * @param input The string to be checked.
	 * @return True if the string is valid (not null or empty), false otherwise.
	 */

	public static boolean isValidString(String input) {

		if (input == null || "".equals(input.trim())) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Checks if a string is invalid (null or empty).
	 *
	 * @param input The string to be checked.
	 * @return True if the string is invalid (null or empty), false if it is valid.
	 */

	public static boolean isInvalidString(String input) {

		if (input == null || "".equals(input.trim())) {
			return false;
		} else {
			return true;

		}

	}

}
