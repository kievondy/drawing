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

@RunWith(MockitoJUnitRunner.class)
public class CanvasCommandTest {

	CanvasCommand testSubject = new CanvasCommand();

	@Mock
	Image image;

	@Mock
	Canvas canvas;

	@Test
	public void testGetCommandKey() {
		String commandKey = testSubject.getCommandKey();
		assertThat(commandKey, Matchers.is(Command.COMMAND_CANVAS));
	}

	@Test
	public void testExecute() throws ExitApplicationException {
		String arbitraryPrintValue = "arbitraryPrintValue";
		when(image.getCanvas()).thenReturn(canvas);
		when(canvas.print()).thenReturn(arbitraryPrintValue);
		String retval = testSubject.execute(null);
		verify(canvas).draw(null);
		assertThat(retval, Matchers.is(arbitraryPrintValue));
	}

	@Before
	public void prepare() {
		testSubject.image = image;
	}

}
