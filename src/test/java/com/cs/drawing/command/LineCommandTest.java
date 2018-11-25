package com.cs.drawing.command;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.drawing.exception.ExitApplicationException;
import com.cs.drawing.model.Canvas;
import com.cs.drawing.model.Image;
import com.cs.drawing.model.Line;

@RunWith(MockitoJUnitRunner.class)
public class LineCommandTest {

	LineCommand testSubject = new LineCommand();

	@Mock
	Image image;

	@Mock
	Canvas canvas;

	@Mock
	Line line;

	@Test
	public void testGetCommandKey() {
		String commandKey = testSubject.getCommandKey();
		assertThat(commandKey, Matchers.is(Command.COMMAND_LINE));
	}

	@Test
	public void testExecute() throws ExitApplicationException {
		String arbitraryPrintValue = "arbitraryPrintValue";
		when(image.getCanvas()).thenReturn(canvas);
		when(canvas.print()).thenReturn(arbitraryPrintValue);
		String retval = testSubject.execute(null);
		verify(line).draw(canvas, null);
		assertThat(retval, Matchers.is(arbitraryPrintValue));
	}

	@Before
	public void prepare() {
		testSubject.image = image;
		testSubject.line = line;
	}

}
