package com.cs.drawing.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.drawing.exception.ExitApplicationException;
import com.cs.drawing.model.BucketFill;
import com.cs.drawing.model.Image;

@Component
public class BucketFillCommand extends Command {

	@Autowired
	Image image;

	@Autowired
	BucketFill bucketFill;

	@Override
	public String execute(String[] args) throws ExitApplicationException {
		bucketFill.draw(image.getCanvas(), args);
		return image.getCanvas().print();
	}

	@Override
	String getCommandKey() {
		return COMMAND_BUCKETFILL;
	}

}
