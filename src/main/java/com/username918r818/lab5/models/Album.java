package com.username918r818.lab5.models;

public class Album {
	private String name; // Поле не может быть null, Строка не может быть пустой
	private Integer tracks; // Поле не может быть null, Значение поля должно быть больше 0
	private Integer sales; // Поле может быть null, Значение поля должно быть больше 0

	public Album(String name, Integer tracks, Integer sales) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Название альбома не может быть пустым");
		}
		if (tracks == null || tracks <= 0) {
			throw new IllegalArgumentException("Количество треков должно быть больше 0");
		}
		if (sales != null && sales <= 0) {
			throw new IllegalArgumentException("Количество продаж должно быть больше 0");
		}
		this.name = name;
		this.tracks = tracks;
		this.sales = sales;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Название альбома не может быть пустым");
		}
		this.name = name;
	}

	public Integer getTracks() {
		return tracks;
	}

	public void setTracks(Integer tracks) {
		if (tracks == null || tracks <= 0) {
			throw new IllegalArgumentException("Количество треков должно быть больше 0");
		}
		this.tracks = tracks;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		if (sales != null && sales <= 0) {
			throw new IllegalArgumentException("Количество продаж должно быть больше 0");
		}
		this.sales = sales;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Album{name='").append(name).append("', ");
		sb.append("tracks=").append(tracks).append(", ");
		if (sales == null) {
			sb.append("sales=null}");
		} else {
			sb.append("sales=").append(sales).append("}");
		}
		return sb.toString();
	}
}