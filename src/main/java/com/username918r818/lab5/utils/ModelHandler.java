package com.username918r818.lab5.utils;

import java.util.Map;
import java.util.UUID;
import java.time.ZonedDateTime;
import java.util.HashMap;

import com.username918r818.lab5.models.*;

public class ModelHandler {

	/**
	 * Converts a MusicBand object to a HashMap.
	 *
	 * @param mb the MusicBand object to convert
	 * @return a HashMap representing the MusicBand object
	 */
	public static HashMap<String, String> modelToMap(MusicBand mb) {
		HashMap<String, String> m = new HashMap<>();
		m.put("UUID", mb.getUUID().toString());
		m.put("name", mb.getName());
		m.put("x", mb.getCoordinates().getX().toString());
		m.put("y", mb.getCoordinates().getY().toString());
		m.put("creationDate", mb.getCreationDate().toString());
		m.put("numberOfParticipants", "" + mb.getNumberOfParticipants());
		m.put("genre", mb.getGenre().toString());
		if (mb.getBestAlbum() != null){
			m.put("aName", mb.getBestAlbum().getName());
			m.put("aTracks", mb.getBestAlbum().getTracks().toString());
			if (mb.getBestAlbum().getSales() != null){
				m.put("aSales", mb.getBestAlbum().getSales().toString());
			}
		}
		return m;
	}

	/**
	 * Maps a Map of Strings to a MusicBand object.
	 * 
	 * @param m the Map to map
	 * @return the mapped MusicBand object
	 */
	public static MusicBand mapToModel(Map<String, String> m) {
		Coordinates coordinates = new Coordinates(Integer.parseInt(m.get("x")), Double.parseDouble(m.get("y")));
		Album album = null;
		if (m.get("aName") != null && m.get("aTracks") != null){
			if(m.get("aSales") != null){
				album = new Album(m.get("aName"), Integer.parseInt(m.get("aTracks")), Integer.parseInt(m.get("aSales")));
			} else {
				album = new Album(m.get("aName"), Integer.parseInt(m.get("aTracks")), null);
			}
		}
		UUID uuid = UUID.fromString(m.get("UUID"));
		String name = m.get("name");
		ZonedDateTime zdt = ZonedDateTime.parse(m.get("creationDate"));
		long numberOfParticipants = Long.parseLong(m.get("numberOfParticipants"));
		MusicGenre genre = MusicGenre.valueOf(m.get("genre"));
		MusicBand mb = new MusicBand(uuid, name, coordinates, zdt, numberOfParticipants, genre, album);
		return mb;
	}

	// я же мог это все не писать...
}
