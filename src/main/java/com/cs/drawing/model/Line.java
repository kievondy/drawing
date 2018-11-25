package com.cs.drawing.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.cs.drawing.config.Const;

@Component
public class Line implements DrawableOnCanvas, Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * args length must be 4 - x1 y1 x2 y2. Currently only horizontal or vertical
	 * lines are supported.
	 */
	@Override
	public void draw(Canvas canvas, String[] args) {

		if (canvas == null) {
			throw new IllegalArgumentException("Must create a canvas first before creating a line.");
		}

		String errorMsg = "Incorrect arguments. Must specify numeric values x1, y1, x2 and y2 when creating line.";
		if (args == null || args.length != 4) {
			throw new IllegalArgumentException(errorMsg);
		}

		int x1;
		int y1;
		int x2;
		int y2;
		try {
			x1 = Integer.parseInt(args[0]);
			y1 = Integer.parseInt(args[1]);
			x2 = Integer.parseInt(args[2]);
			y2 = Integer.parseInt(args[3]);

		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(errorMsg);
		}

		if (invalidX(canvas, x1) || invalidX(canvas, x2) || invalidY(canvas, y1) || invalidY(canvas, y2)) {
			throw new IllegalArgumentException("Incorrect arguments. (x1,y1) to (x2,y2) must be within the canvas.");
		}

		boolean horizontal = (y1 == y2);
		boolean vertical = (x1 == x2);
		if (!horizontal && !vertical) {
			throw new IllegalArgumentException("Incorrect arguments. (x1,y1) to (x2,y2) must be a horizontal or vertical line.");
		}

		// reverse drawing
		if (x1 > x2 || y1 > y2) {
			int a = x1;
			int b = y1;
			x1 = x2;
			y1 = y2;
			x2 = a;
			y2 = b;
		}

		char[][] canvasContent = canvas.getCanvasContent();

		for (int i = y1 - 1; i < y2; i++) {
			char[] line = canvasContent[i];
			for (int j = x1 - 1; j < x2; j++) {
				line[j] = Const.LINE_CHAR;
			}
		}

		canvas.setCanvasContent(canvasContent);
	}

}
