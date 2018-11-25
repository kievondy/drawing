package com.cs.drawing.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.drawing.exception.ExitApplicationException;
import com.cs.drawing.model.Image;
import com.cs.drawing.model.Rectangle;

@Component
public class RectangleCommand extends Command {

	@Autowired
	Image image;

	@Autowired
	Rectangle rectangle;

	@Override
	public String execute(String[] args) throws ExitApplicationException {
		rectangle.draw(image.getCanvas(), args);
		return image.getCanvas().print();
	}

	@Override
	String getCommandKey() {
		return COMMAND_RECTANGLE;
	}

}
