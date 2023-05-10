package com.username918r818.lab5.models;

import java.util.UUID;

public class MusicBand {
	private UUID uuid; // Значение этого поля должно генерироваться автоматически
	private String name; // Поле не может быть null, Строка не может быть пустой
	private Coordinates coordinates; // Поле не может быть null
	private java.time.ZonedDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
													// автоматически
	private long numberOfParticipants; // Значение поля должно быть больше 0
	private MusicGenre genre; // Поле не может быть null
	private Album bestAlbum; // Поле может быть null

	public MusicBand(String name, Coordinates coordinates, long numberOfParticipants, MusicGenre genre,
			Album bestAlbum) {
		this(UUID.randomUUID(), name, coordinates, java.time.ZonedDateTime.now(), numberOfParticipants, genre,
				bestAlbum);
	}

	public MusicBand(UUID uuid, String name, Coordinates coordinates, java.time.ZonedDateTime creationDate,
			long numberOfParticipants, MusicGenre genre,
			Album bestAlbum) {
		this.setUUID(uuid);
		this.setName(name);
		this.setCoordinates(coordinates);
		this.setCreationDate(creationDate);
		this.setNumberOfParticipants(numberOfParticipants);
		this.setGenre(genre);
		this.setBestAlbum(bestAlbum);
	}

	public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Имя не может быть null или пустым");
		}
		this.name = name;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		if (coordinates == null) {
			throw new IllegalArgumentException("Координаты не могут быть null");
		}
		this.coordinates = coordinates;
	}

	public java.time.ZonedDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.time.ZonedDateTime creationDate) {
		if (creationDate == null) {
			throw new IllegalArgumentException("Дата создания не может быть null");
		}
		this.creationDate = creationDate;
	}

	public long getNumberOfParticipants() {
		return numberOfParticipants;
	}

	public void setNumberOfParticipants(long numberOfParticipants) {
		if (numberOfParticipants <= 0) {
			throw new IllegalArgumentException("Количество участников должно быть больше 0");
		}
		this.numberOfParticipants = numberOfParticipants;
	}

	public MusicGenre getGenre() {
		return genre;
	}

	public void setGenre(MusicGenre genre) {
		if (genre == null) {
			throw new IllegalArgumentException("Жанр не может быть null");
		}
		this.genre = genre;
	}

	public Album getBestAlbum() {
		return bestAlbum;
	}

	public void setBestAlbum(Album bestAlbum) {
		this.bestAlbum = bestAlbum;
	}

	@Override
	public String toString() {
		return "MusicBand [uuid=" + uuid + ", name=" + name + ", coordinates=" + coordinates + ", creationDate="
				+ creationDate + ", numberOfParticipants=" + numberOfParticipants + ", genre=" + genre + ", bestAlbum="
				+ bestAlbum + "]";
	}
}