package com.cs.drawing.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	private Canvas canvas;

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

}
