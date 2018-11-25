package com.cs.drawing.command;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.cs.drawing.exception.ExitApplicationException;

public class QuitCommandTest {

	QuitCommand testSubject = new QuitCommand();

	@Test
	public void testGetCommandKey() {
		String commandKey = testSubject.getCommandKey();
		assertThat(commandKey, Matchers.is(Command.COMMAND_QUIT));
	}

	@Test(expected = ExitApplicationException.class)
	public void testExecute() throws ExitApplicationException {
		testSubject.execute(null);
	}

}
