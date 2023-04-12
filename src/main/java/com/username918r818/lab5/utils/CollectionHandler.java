package com.username918r818.lab5.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

import com.username918r818.lab5.models.MusicBand;

public class CollectionHandler {
	private java.time.ZonedDateTime initDate;
	LinkedList<MusicBand> collection = new LinkedList<MusicBand>();

	public CollectionHandler() {
		this(java.time.ZonedDateTime.now());
	}

	public CollectionHandler(java.time.ZonedDateTime initDate) {
		this.initDate = initDate;
	}

	public String show() {
		StringBuilder result = new StringBuilder();
		if (collection.isEmpty()) {
			return "The collection is empty";
		}
		for (var i : collection) {
			result.append(i.toString()).append('\n');
		}
		return result.toString();
	}

	public void add(MusicBand mb) {
		var uuid = mb.getUUID();
		for (var i : collection) {
			if (i.getUUID().equals(uuid)) {
				throw new IllegalArgumentException("object with the same UUID have already been added");
			}
		}
		collection.add(mb);
		this.sort();
	}

	public void update(MusicBand mb) {
		UUID uuid = mb.getUUID();
		boolean isThereAModel = false;
		for (var i : collection) {
			if (i.getUUID().equals(uuid)) {
				collection.remove(i);
				isThereAModel = true;
				break;
			}
		}
		if (isThereAModel == false) {
			throw new IllegalArgumentException("object with the UUID have not been added yet");
		}
		collection.add(mb);
		this.sort();
	}


	public void removeByID(UUID uuid) {
		boolean isThereAModel = false;
		for (var i : collection) {
			if (i.getUUID().equals(uuid)) {
				collection.remove(i);
				isThereAModel = true;
				break;
			}
		}
		if (isThereAModel == false) {
			throw new IllegalArgumentException("object with the UUID have not been added yet");
		}
	}

	public void clear() {
		this.collection = new LinkedList<MusicBand>();
	}

	public String save() {
		var result = new HashMap<>();
		result.put("initDate", initDate.toString());
		var models = new HashMap<>();

		for (var i : collection) {
			models.put(i.getUUID().toString(), ModelHandler.modelToMap(i));
		}
		result.put("models", models);
		var resultStr = JsonHandler.mapToJSON(result);

		return resultStr;
	}

	private void sort() {
		Collections.sort(this.collection, (m1, m2) -> {
			int result = m1.getName().compareTo(m2.getName());
			if (result != 0) {
				return result;
			}
			return m1.getUUID().compareTo(m2.getUUID());
		});
	}

	
}
