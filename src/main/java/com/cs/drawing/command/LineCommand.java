package com.cs.drawing.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.drawing.exception.ExitApplicationException;
import com.cs.drawing.model.Image;
import com.cs.drawing.model.Line;

@Component
public class LineCommand extends Command {

	@Autowired
	Image image;

	@Autowired
	Line line;

	@Override
	public String execute(String[] args) throws ExitApplicationException {
		line.draw(image.getCanvas(), args);
		return image.getCanvas().print();
	}

	@Override
	String getCommandKey() {
		return COMMAND_LINE;
	}

}
