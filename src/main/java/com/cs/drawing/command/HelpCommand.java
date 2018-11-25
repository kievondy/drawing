package com.cs.drawing.command;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cs.drawing.exception.ExitApplicationException;

@Component
public class HelpCommand extends Command {

	static final String HELP_MESSAGE = new StringBuilder()
			.append(StringUtils.LF)
			.append("C w h           Creates a new canvas of width w and height h.")
			.append(StringUtils.LF)
			.append("")
			.append(StringUtils.LF)
			.append("L x1 y1 x2 y2   Creates a new line from (x1,y1) to (x2,y2). Currently only")
			.append(StringUtils.LF)
			.append("                horizontal or vertical lines are supported. Horizontal and vertical lines")
			.append(StringUtils.LF)
			.append("                will be drawn using the 'x' character.")
			.append(StringUtils.LF)
			.append("")
			.append(StringUtils.LF)
			.append("R x1 y1 x2 y2   Creates a new rectangle, whose upper left corner is (x1,y1) and")
			.append(StringUtils.LF)
			.append("                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn")
			.append(StringUtils.LF)
			.append("                using the 'x' character.")
			.append(StringUtils.LF)
			.append("")
			.append(StringUtils.LF)
			.append("B x y c         Fills the entire area connected to (x,y) with \"colour\" c. The")
			.append(StringUtils.LF)
			.append("                behaviour of this is the same as that of the \"bucket fill\" tool in paint")
			.append(StringUtils.LF)
			.append("                programs.")
			.append(StringUtils.LF)
			.append("")
			.append(StringUtils.LF)
			.append("P               re-Prints the created canvas (if exists).")
			.append(StringUtils.LF)
			.append("")
			.append(StringUtils.LF)
			.append("Q               Quits the program.")
			.append(StringUtils.LF)
			.append("")
			.append(StringUtils.LF)
			.append("H               Prints this screen.")
			.append(StringUtils.LF)
			.toString();

	@Override
	public String execute(String[] args) throws ExitApplicationException {
		return HELP_MESSAGE;
	}

	@Override
	String getCommandKey() {
		return COMMAND_HELP;
	}

}
