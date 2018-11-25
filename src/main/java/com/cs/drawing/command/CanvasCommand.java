package com.cs.drawing.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.drawing.exception.ExitApplicationException;
import com.cs.drawing.model.Canvas;
import com.cs.drawing.model.Image;

@Component
public class CanvasCommand extends Command {

	@Autowired
	Image image;

	@Override
	public String execute(String[] args) throws ExitApplicationException {
		image.setCanvas(new Canvas());
		image.getCanvas().draw(args);
		return image.getCanvas().print();
	}

	@Override
	String getCommandKey() {
		return COMMAND_CANVAS;
	}

}
