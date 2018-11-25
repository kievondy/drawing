package com.cs.drawing.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.cs.drawing.config.Const;

public class Canvas implements Serializable {

	private static final long serialVersionUID = 1L;

	char[][] canvasContent;

	int width;

	int height;

	/**
	 * @param args
	 *            length must be 2. args[0] is width. args[1] is height.
	 */
	public void draw(String[] args) {

		String errorMsg = "Incorrect arguments. Must specify numeric values greater than zero for width and height when creating canvas.";
		if (args == null || args.length != 2) {
			throw new IllegalArgumentException(errorMsg);
		}

		try {
			width = Integer.parseInt(args[0]);
			height = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(errorMsg);
		}

		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException(errorMsg);
		}

		canvasContent = new char[height][width];
		for (int i = 0; i < canvasContent.length; i++) {
			char[] line = canvasContent[i];
			for (int j = 0; j < line.length; j++) {
				line[j] = StringUtils.SPACE.charAt(0);
			}
		}
	}

	public String print() {
		StringBuilder sb = new StringBuilder();
		String verticalBorder = StringUtils.repeat(Const.BORDER_VERTICAL, width + 2);
		sb.append(verticalBorder);
		for (int i = 0; i < canvasContent.length; i++) {
			char[] line = canvasContent[i];
			sb.append(StringUtils.LF);
			sb.append(Const.BORDER_HORIZONTAL).append(new String(line)).append(Const.BORDER_HORIZONTAL);
		}
		sb.append(StringUtils.LF).append(verticalBorder);
		return sb.toString();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public char[][] getCanvasContent() {
		return canvasContent;
	}

	public void setCanvasContent(char[][] canvasContent) {
		this.canvasContent = canvasContent;
	}

}
