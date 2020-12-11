package com.example.demo.util;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class SpringBeanUtil {
	// constructor that takes in the data source
	public static void mergeNotNull(Object source, Object target) {
		copyProperties(source, target, getNullPropertyName(source));
	}

	private static String[] getNullPropertyName(Object source) {
		final BeanWrapper wrappedSourceObject = new BeanWrapperImpl(source);
		// loop through data passed in.
		Set<String> propertyNames = new HashSet<>();
		for (PropertyDescriptor propertyDescriptor : wrappedSourceObject.getPropertyDescriptors()) {
			if (wrappedSourceObject.getPropertyValue(propertyDescriptor.getName()) == null)
				propertyNames.add(propertyDescriptor.getName());

		}
		return propertyNames.toArray(new String[propertyNames.size()]);
	}
}
