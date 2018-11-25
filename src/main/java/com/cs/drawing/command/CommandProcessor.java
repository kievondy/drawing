package com.cs.drawing.command;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.drawing.exception.ExitApplicationException;

@Service
public class CommandProcessor {

	static final String ERROR_MSG_UNKNOWN_CMD = "Unknown command!";

	@Autowired
	CommandFactory commandFactory;

	public String execute(String command) throws ExitApplicationException {
		String[] commandArray = StringUtils.split(command, StringUtils.SPACE);
		if (commandArray == null || commandArray.length <= 0) {
			return ERROR_MSG_UNKNOWN_CMD;
		}
		Command cmd = commandFactory.get(StringUtils.upperCase(commandArray[0]));
		if (cmd != null) {
			if (command.length() > 1) {
				return cmd.execute(Arrays.copyOfRange(commandArray, 1, commandArray.length));
			}
			return cmd.execute(null);
		}
		return ERROR_MSG_UNKNOWN_CMD;
	}

}
