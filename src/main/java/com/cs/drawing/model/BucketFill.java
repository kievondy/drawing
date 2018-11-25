package com.cs.drawing.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class BucketFill implements DrawableOnCanvas, Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * args' length must be 3 - x, y, c
	 */
	@Override
	public void draw(Canvas canvas, String[] args) {

		if (canvas == null) {
			throw new IllegalArgumentException("Must create a canvas first before creating a bucket fill.");
		}

		String errorMsg = "Incorrect arguments. Must specify numeric values x, y and colour-code c when creating bucket fill.";
		if (args == null || args.length != 3) {
			throw new IllegalArgumentException(errorMsg);
		}

		int x;
		int y;
		try {
			x = Integer.parseInt(args[0]);
			y = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(errorMsg);
		}
		String c = args[2];

		if (invalidX(canvas, x) || invalidY(canvas, y)) {
			throw new IllegalArgumentException("Incorrect arguments. (x,y) must be within the canvas.");
		}

		fillCell(canvas, null, x, y, c);
	}

	private void fillCell(Canvas canvas, String previousContent, int x, int y, String colour) {
		char[][] canvasContent = canvas.getCanvasContent();
		int i = x - 1;
		int j = y - 1;
		if (j >= 0 && j < canvas.getHeight() && i >= 0 && i < canvas.getWidth()) {
			char currentContent = canvasContent[j][i];
			if (StringUtils.isEmpty(previousContent) || StringUtils.equals(previousContent, Character.toString(currentContent))) {
				canvasContent[j][i] = colour.charAt(0);
				fillCell(canvas, Character.toString(currentContent), x + 1, y, colour);
				fillCell(canvas, Character.toString(currentContent), x - 1, y, colour);
				fillCell(canvas, Character.toString(currentContent), x, y + 1, colour);
				fillCell(canvas, Character.toString(currentContent), x, y - 1, colour);
			}
		}
	}

}
