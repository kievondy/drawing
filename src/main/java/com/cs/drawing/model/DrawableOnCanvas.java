package com.cs.drawing.model;

public interface DrawableOnCanvas {

	void draw(Canvas canvas, String[] args);

	default boolean invalidY(Canvas canvas, int y) {
		return y <= 0 || y > canvas.getHeight();
	}

	default boolean invalidX(Canvas canvas, int x) {
		return x <= 0 || x > canvas.getWidth();
	}

}
