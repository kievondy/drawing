package com.cs.drawing.model;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BucketFillTest {

	BucketFill testSubject = new BucketFill();

	@Mock
	Canvas canvas;

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNullCanvas() {
		testSubject.draw(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenInvalidArgsLength() {
		testSubject.draw(canvas, new String[] { "2", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNonNumericX() {
		testSubject.draw(canvas, new String[] { "a", "2", "o" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNonNumericY() {
		testSubject.draw(canvas, new String[] { "2", "a", "o" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenXLessThanZero() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "-1", "2", "o" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenXGreaterThanWidth() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "11", "2", "o" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenYLessThanZero() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "2", "-1", "o" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenYGreaterThanHeight() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "2", "11", "o" });
	}

	@Test
	public void testDraw_whenTypical() {
		int width = 5;
		int height = 5;
		char[][] initialContent = new char[height][width];
		for (int i = 0; i < initialContent.length; i++) {
			char[] cs = initialContent[i];
			for (int j = 0; j < cs.length; j++) {
				cs[j] = StringUtils.SPACE.charAt(0);
			}
		}
		String colour = "o";
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		when(canvas.getCanvasContent()).thenReturn(initialContent);
		testSubject.draw(canvas, new String[] { "2", "2", colour });
		char[][] retval = canvas.getCanvasContent();
		assertThat(retval, Matchers.is(initialContent));

		assertThat(retval[0][0], Matchers.is(colour.charAt(0)));
		assertThat(retval[0][1], Matchers.is(colour.charAt(0)));
		assertThat(retval[0][2], Matchers.is(colour.charAt(0)));
		assertThat(retval[0][3], Matchers.is(colour.charAt(0)));
		assertThat(retval[0][4], Matchers.is(colour.charAt(0)));

		assertThat(retval[0][1], Matchers.is(colour.charAt(0)));
		assertThat(retval[1][1], Matchers.is(colour.charAt(0)));
		assertThat(retval[2][1], Matchers.is(colour.charAt(0)));
		assertThat(retval[3][1], Matchers.is(colour.charAt(0)));
		assertThat(retval[4][1], Matchers.is(colour.charAt(0)));

		assertThat(retval[0][2], Matchers.is(colour.charAt(0)));
		assertThat(retval[1][2], Matchers.is(colour.charAt(0)));
		assertThat(retval[2][2], Matchers.is(colour.charAt(0)));
		assertThat(retval[3][2], Matchers.is(colour.charAt(0)));
		assertThat(retval[4][2], Matchers.is(colour.charAt(0)));

		assertThat(retval[0][3], Matchers.is(colour.charAt(0)));
		assertThat(retval[1][3], Matchers.is(colour.charAt(0)));
		assertThat(retval[2][3], Matchers.is(colour.charAt(0)));
		assertThat(retval[3][3], Matchers.is(colour.charAt(0)));
		assertThat(retval[4][3], Matchers.is(colour.charAt(0)));

		assertThat(retval[0][4], Matchers.is(colour.charAt(0)));
		assertThat(retval[1][4], Matchers.is(colour.charAt(0)));
		assertThat(retval[2][4], Matchers.is(colour.charAt(0)));
		assertThat(retval[3][4], Matchers.is(colour.charAt(0)));
		assertThat(retval[4][4], Matchers.is(colour.charAt(0)));
	}

}
