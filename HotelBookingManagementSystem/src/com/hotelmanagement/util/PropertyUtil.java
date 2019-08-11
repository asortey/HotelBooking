package com.hotelmanagement.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.hotelmanagement.exception.BookingException;

public class PropertyUtil {
	private Properties props;

	public PropertyUtil() throws BookingException{
		props = new Properties();
		try (FileInputStream fis = new FileInputStream("Projectprop.properties")){
			props.load(fis);
		} catch (IOException e) {
			throw new BookingException();
		}
	}
	
	public String getPropertyValue(String prop) {
		return props.getProperty(prop);
	}
	
}
