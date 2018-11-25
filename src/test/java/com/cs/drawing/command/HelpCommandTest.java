package com.cs.drawing.command;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.cs.drawing.exception.ExitApplicationException;

public class HelpCommandTest {

	HelpCommand testSubject = new HelpCommand();

	@Test
	public void testGetCommandKey() {
		String commandKey = testSubject.getCommandKey();
		assertThat(commandKey, Matchers.is(Command.COMMAND_HELP));
	}

	@Test
	public void testExecute() throws ExitApplicationException {
		String retval = testSubject.execute(null);
		assertThat(retval, Matchers.is(HelpCommand.HELP_MESSAGE));
	}

}
