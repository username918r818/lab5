package com.username918r818.lab5.models;

public class Coordinates {
	private Integer x; // Поле не может быть null
	private Double y; // Поле не может быть null

	public Coordinates(Integer x, Double y) {
		if (x == null || y == null) {
			throw new IllegalArgumentException("null argument!!");
		}
		this.x = x;
		this.y = y;
	}

	public void setX(Integer x) {
		if (x == null) {
			throw new IllegalArgumentException("null argument!!");
		}
		this.x = x;
	}

	public void setY(Double y) {
		if (y == null) {
			throw new IllegalArgumentException("null argument!!");
		}
		this.y = y;
	}

	public Integer getX() {
		return this.x;
	}

	public Double getY() {
		return this.y;
	}
}