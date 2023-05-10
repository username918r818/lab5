package com.username918r818.lab5.utils;

import java.util.ArrayList;
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

	/**
	 * Returns a string representation of the collection. If the collection is
	 * empty, returns "The collection is empty". Otherwise, returns a string
	 * containing the result
	 * of calling the toString() method on each element of the collection, separated
	 * by newline
	 * characters.
	 *
	 * @return a string representation of the collection
	 */
	public String show() {
		StringBuilder result = new StringBuilder();
		if (collection.isEmpty()) {
			return "The collection is empty";
		}
		boolean notFirst = false;
		for (var i : collection) {
			if (notFirst) {
				result.append("\n");
			}
			notFirst = true;
			result.append(i.toString());
		}

		return result.toString();
	}

	/**
	 * Adds a MusicBand object to the collection if it has a unique UUID.
	 * Throws an IllegalArgumentException if the collection already
	 * contains a MusicBand with the same UUID. The collection is then
	 * sorted.
	 * 
	 * @param mb the MusicBand object to be added to the collection.
	 * @throws IllegalArgumentException if the UUID of mb already exists
	 *                                  in the collection.
	 */
	public void add(MusicBand mb) {
		var uuid = mb.getUUID();
		for (var i : collection) {
			if (i.getUUID().equals(uuid)) {
				throw new IllegalArgumentException("Музыкальная группа с таким UUID уже существует");
			}
		}
		collection.add(mb);
		this.sort();
	}

	/**
	 * Updates the MusicBand object in the collection if it exists, otherwise throws
	 * an exception.
	 * 
	 * @param mb the MusicBand object to update
	 * @throws IllegalArgumentException if the MusicBand object with the same UUID
	 *                                  has not been added yet
	 */
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
			throw new IllegalArgumentException("Музыкальная группа с таким UUID не существует");
		}
		collection.add(mb);
		this.sort();
	}

	/**
	 * Removes the object with the given UUID from the collection.
	 * 
	 * @param uuid the UUID of the object to remove
	 * @throws IllegalArgumentException if there is no object with the given UUID in
	 *                                  the collection
	 */
	public void removeByUUID(UUID uuid) {
		boolean isThereAModel = false;
		for (var i : collection) {
			if (i.getUUID().equals(uuid)) {
				collection.remove(i);
				isThereAModel = true;
				break;
			}
		}
		if (isThereAModel == false) {
			throw new IllegalArgumentException("Музыкальная группа с таким UUID не существует");
		}
	}

	/**
	 * Clears the collection by creating a new empty LinkedList.
	 */
	public void clear() {
		this.collection = new LinkedList<MusicBand>();
	}

	/**
	 * Converts the collection of models into a JSON string and returns it.
	 * 
	 * @return A JSON string representing the collection of models and their UUIDs.
	 */
	public String save() {
		var result = new HashMap<String, String>();
		result.put("initDate", initDate.toString());
		var models = new HashMap<String, String>();

		for (var i : collection) {
			models.put(i.getUUID().toString(), JsonHandler.mapToJSON(ModelHandler.modelToMap(i)));
		}
		result.put("models", JsonHandler.mapToJSON(models));
		var resultStr = JsonHandler.mapToJSON(result);

		return resultStr;
	}

	/**
	 * Removes an element from the collection at the specified index.
	 * 
	 * @param index The index of the element to be removed.
	 */
	public void removeAtIndex(int index) {
		collection.remove(index);
	}

	public void removeLower(int index) {
		if (index < 0 || index >= collection.size()) {
			throw new IllegalArgumentException("index out of range");
		}
		for (int i = index - 1; i >= 0; i--) {
			collection.remove(i);
		}
	}

	public String group_counting_by_name() {
		HashMap<String, Integer> names = new HashMap<>();
		for (var i : collection) {
			if (names.containsKey(i.getName())) {
				names.put(i.getName(), names.get(i.getName()) + 1);
			} else {
				names.put(i.getName(), 1);
			}
		}
		var result = new HashMap<String, String>();
		for (var i : names.keySet()) {
			result.put(i, String.valueOf(names.get(i)));
		}
		return JsonHandler.mapToJSON(result);
	}

	public int count_by_number_of_participants(int numberOfParticipants) {
		int count = 0;
		for (var i : collection) {
			if (i.getNumberOfParticipants() == numberOfParticipants) {
				count++;
			}
		}
		return count;
	}

	public String print_unique_genre() {
		if (collection.isEmpty()) {
			return "The collection is empty";
		}
		ArrayList<String> genres = new ArrayList<>();
		StringBuilder result = new StringBuilder();
		boolean notFirst = false;
		for (var i : collection) {
			if (!genres.contains(i.getGenre().toString())) {
				if (notFirst) {
					result.append("\n");
				}
				notFirst = true;
				genres.add(i.getGenre().toString());
				result.append(i.getGenre().toString());
			}
			
		}
		return result.toString();
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




	public int getCount() {
		return collection.size();
	}

	public java.time.ZonedDateTime getInitDate() {
		return initDate;
	}
}
