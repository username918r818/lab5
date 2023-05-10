package com.username918r818.lab5.utils;

import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {

	/**
	 * Converts a Map object to a JSON string using the Jackson ObjectMapper.
	 * 
	 * @param m The Map object to be converted to JSON.
	 * @return The JSON string representation of the Map object.
	 */
	public static String mapToJSON(HashMap<String, String> m) {
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
	public static HashMap<String, String> JSONToMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> m;
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
		try { 
			m = mapper.readValue(json, typeRef);
			return m;
		} catch (Exception exception) {
			return null;
		}
	}

}
