package com.cs.drawing.command;

import org.springframework.stereotype.Component;

import com.cs.drawing.exception.ExitApplicationException;

@Component
public class QuitCommand extends Command {

	@Override
	public String execute(String[] args) throws ExitApplicationException {
		throw new ExitApplicationException("Exiting drawing application");
	}

	@Override
	String getCommandKey() {
		return COMMAND_QUIT;
	}

}
