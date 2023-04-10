package com.username918r818.lab5.utils;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {

	public static String mapToJSON(Map<String, String> m) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(m);
			return json;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> JSONToMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> m;
		try {
			m = mapper.readValue(json, Map.class);
			return m;
		} catch (Exception exception) {
			return null;
		}
	}

}
