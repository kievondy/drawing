package com.cs.drawing.command;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandFactoryTest {

	CommandFactory testSubject = new CommandFactory();

	@Test
	public void testRegisterAndGet_whenTypical() {
		String arbitraryKey = "arbitraryKey";
		Command arbitraryCmd = Mockito.mock(Command.class);
		testSubject.register(arbitraryKey, arbitraryCmd);
		Command retval = testSubject.get(arbitraryKey);
		assertThat(retval, Matchers.is(arbitraryCmd));
	}

	@Test
	public void testGet_whenNotExist() {
		String arbitraryKey = "someKeyThatDoesNotExist";
		Command retval = testSubject.get(arbitraryKey);
		assertThat(retval, Matchers.nullValue());
	}

}
