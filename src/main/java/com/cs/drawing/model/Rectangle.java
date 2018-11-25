package com.cs.drawing.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.cs.drawing.config.Const;

@Component
public class Rectangle implements DrawableOnCanvas, Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * args length must be 4 - x1 y1 x2 y2. upper left corner is (x1,y1). lower
	 * right corner is (x2,y2).
	 */
	@Override
	public void draw(Canvas canvas, String[] args) {

		if (canvas == null) {
			throw new IllegalArgumentException("Must create a canvas first before creating a rectangle.");
		}

		String errorMsg = "Incorrect arguments. Must specify numeric values x1, y1, x2 and y2 when creating rectangle.";
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

		if (x1 > x2 || y1 > y2) {
			throw new IllegalArgumentException("Incorrect arguments. (x1, y1) must be upper left corner. (x2, y2) must be lower right corner.");
		}

		char[][] canvasContent = canvas.getCanvasContent();

		for (int i = y1 - 1; i < y2; i++) {
			char[] line = canvasContent[i];
			boolean topOrBottom = (i == y1 - 1) || (i == y2 - 1);
			for (int j = x1 - 1; j < x2; j++) {
				if (topOrBottom) {
					// horizontal line
					line[j] = Const.LINE_CHAR;
					continue;
				}
				if (j == x1 - 1 || j == x2 - 1) {
					// vertical line, do not fill the middle part
					line[j] = Const.LINE_CHAR;
				}
			}
		}

		canvas.setCanvasContent(canvasContent);
	}

}
