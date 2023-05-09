package com.username918r818.lab5.utils;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {

	/**
	 * Converts a Map object to a JSON string using the Jackson ObjectMapper.
	 * 
	 * @param m The Map object to be converted to JSON.
	 * @return The JSON string representation of the Map object.
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToJSON(Map m) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(m);
			return json;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Converts a JSON string to a Map object using the Jackson library.
	 * 
	 * @param json A JSON string to be converted.
	 * @return A Map object representing the JSON data, or null if the conversion
	 *         fails.
	 */
	@SuppressWarnings("rawtypes")
	public static Map JSONToMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map m;
		try {
			m = mapper.readValue(json, Map.class);
			return m;
		} catch (Exception exception) {
			return null;
		}
	}

}
