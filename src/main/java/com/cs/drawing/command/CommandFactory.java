package com.cs.drawing.command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class CommandFactory {

	Map<String, Command> commands = new ConcurrentHashMap<String, Command>();

	public void register(String key, Command cmd) {
		commands.put(key, cmd);
	}

	public Command get(String key) {
		return commands.get(key);
	}
}
