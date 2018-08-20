package com.employee.service.utils;

import static java.util.stream.Collectors.toList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.springframework.validation.BindingResult;

import com.employee.service.exceptions.GenericException;

public class EmployeeUtils {

	public static void throwBeanValidations(BindingResult bindingResult, String methodName) throws GenericException {

		if (bindingResult.hasErrors()) {
			throw new GenericException(methodName + "001",
					bindingResult.getErrorCount() + " Errors --> " + bindingResult.getFieldErrors().stream()
							.map(t -> t.getDefaultMessage()).collect(toList()).toString());

		}
	}

	public static void throwValidationsException(String validatorException, String methodName) throws GenericException {

			throw new GenericException(methodName + "003",
					validatorException);
	}
	
	
	public static class FormatValidator {

		private final static Pattern wholeNumberPattern = Pattern.compile("[0-9]*");

		public static boolean isDateValidMMddyyyy(String date) {
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			df.setLenient(false);
			try {
				df.parse(date);
			} catch (ParseException e) {
				return false;
			}
			return true;
		}

		public static boolean isWholeNumberValid(String number) {

			return wholeNumberPattern.matcher(number).matches();

		}

	}
}
