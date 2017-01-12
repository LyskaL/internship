package llyska.services;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
	
	private static Map<Class<?>, Object> _services;
	
	static {
		_services = new HashMap<>();
		_services.put(CalculatorService.class, new CalculatorServiceImpl());
	}
	
	public static <T> T getService(Class<T> serviceType) {
		return null;
	}
	
}
