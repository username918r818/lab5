package com.username918r818.lab5.utils;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {

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
