package com.cs.drawing.command;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.drawing.exception.ExitApplicationException;

@RunWith(MockitoJUnitRunner.class)
public class CommandProcessorTest {

	CommandProcessor testSubject = new CommandProcessor();

	@Mock
	CommandFactory commandFactory;

	@Mock
	Command command;

	@Test
	public void testExecute_whenNullArg() throws ExitApplicationException {
		String retval = testSubject.execute(null);
		assertThat(retval, Matchers.is(CommandProcessor.ERROR_MSG_UNKNOWN_CMD));
	}

	@Test
	public void testExecute_whenEmptyArg() throws ExitApplicationException {
		String retval = testSubject.execute(StringUtils.EMPTY);
		assertThat(retval, Matchers.is(CommandProcessor.ERROR_MSG_UNKNOWN_CMD));
	}

	@Test
	public void testExecute_whenCommandNotFound() throws ExitApplicationException {
		String command = "X";
		Mockito.when(commandFactory.get(command)).thenReturn(null);
		String retval = testSubject.execute(command);
		assertThat(retval, Matchers.is(CommandProcessor.ERROR_MSG_UNKNOWN_CMD));
	}

	@Test
	public void testExecute_whenTypicalWithNoCommandParam() throws ExitApplicationException {
		String commandString = "H";
		String arbitraryExecutionResult = "arbitraryExecutionResult";
		when(commandFactory.get(commandString)).thenReturn(command);
		when(command.execute(null)).thenReturn(arbitraryExecutionResult);
		String retval = testSubject.execute(commandString);
		verify(command).execute(null);
		assertThat(retval, Matchers.is(arbitraryExecutionResult));
	}

	@Test
	public void testExecute_whenTypicalWithSomeCommandParam() throws ExitApplicationException {
		String commandString = "C";
		String arbitraryExecutionResult = "arbitraryExecutionResult";
		when(commandFactory.get(commandString)).thenReturn(command);
		when(command.execute(Mockito.any())).thenReturn(arbitraryExecutionResult);
		String retval = testSubject.execute("C 10 10");
		assertThat(retval, Matchers.is(arbitraryExecutionResult));
	}

	@Before
	public void prepare() {
		testSubject.commandFactory = commandFactory;
	}

}
