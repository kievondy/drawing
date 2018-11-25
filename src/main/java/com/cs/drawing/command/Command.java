package com.cs.drawing.command;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs.drawing.exception.ExitApplicationException;

public abstract class Command implements InitializingBean {

	public static final String COMMAND_HELP = "H";
	public static final String COMMAND_QUIT = "Q";
	public static final String COMMAND_CANVAS = "C";
	public static final String COMMAND_LINE = "L";
	public static final String COMMAND_RECTANGLE = "R";
	public static final String COMMAND_BUCKETFILL = "B";
	public static final String COMMAND_PRINT = "P";

	@Autowired
	CommandFactory commandFactory;
	
	abstract String getCommandKey();

	public abstract String execute(String[] args) throws ExitApplicationException;

	@Override
	public void afterPropertiesSet() throws Exception {
		commandFactory.register(getCommandKey(), this);
	}

}
