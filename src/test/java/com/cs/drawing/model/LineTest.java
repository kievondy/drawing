package com.cs.drawing.model;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.drawing.config.Const;

@RunWith(MockitoJUnitRunner.class)
public class LineTest {

	Line testSubject = new Line();

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
	public void testDraw_whenNonNumericX1() {
		testSubject.draw(canvas, new String[] { "a", "2", "10", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNonNumericY1() {
		testSubject.draw(canvas, new String[] { "2", "a", "10", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNonNumericX2() {
		testSubject.draw(canvas, new String[] { "2", "2", "a", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNonNumericY2() {
		testSubject.draw(canvas, new String[] { "2", "2", "10", "a" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenX1LessThanZero() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "-1", "2", "8", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenX1GreaterThanWidth() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "11", "2", "8", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenY1LessThanZero() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "2", "-1", "8", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenY1GreaterThanHeight() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "2", "11", "8", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenX2LessThanZero() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "2", "2", "-1", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenX2GreateerThanWidth() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "2", "2", "11", "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenY2LessThanZero() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "2", "2", "8", "-1" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenY2GreaterThanHeight() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "2", "2", "8", "11" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNeitherHorizontalNorVertical() {
		int width = 10;
		int height = 10;
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		testSubject.draw(canvas, new String[] { "2", "2", "8", "8" });
	}

	@Test
	public void testDraw_whenTypicalHorizontal() {
		int width = 10;
		int height = 10;
		char[][] initialContent = new char[height][width];
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		when(canvas.getCanvasContent()).thenReturn(initialContent);
		testSubject.draw(canvas, new String[] { "2", "2", "8", "2" });
		char[][] retval = canvas.getCanvasContent();
		assertThat(retval, Matchers.is(initialContent));

		// Use index [1][1] because array starts from 0
		assertThat(retval[1][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][2], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][3], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][4], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][5], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][6], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][7], Matchers.is(Const.LINE_CHAR));

		// line 2
		assertThat(retval[1][0], Matchers.not(Const.LINE_CHAR));
		assertThat(retval[1][8], Matchers.not(Const.LINE_CHAR));
		assertThat(retval[1][9], Matchers.not(Const.LINE_CHAR));

		for (int i = 0; i <= 9; i++) {
			// line 1
			assertThat(retval[0][i], Matchers.not(Const.LINE_CHAR));

			// line 3 to 10
			for (int j = 2; j <= 9; j++) {
				assertThat(retval[j][i], Matchers.not(Const.LINE_CHAR));
			}
		}
	}

	@Test
	public void testDraw_whenTypicalVertical() {
		int width = 10;
		int height = 10;
		char[][] initialContent = new char[height][width];
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		when(canvas.getCanvasContent()).thenReturn(initialContent);
		testSubject.draw(canvas, new String[] { "2", "2", "2", "8" });
		char[][] retval = canvas.getCanvasContent();
		assertThat(retval, Matchers.is(initialContent));

		// Use index [1][1] because array starts from 0
		assertThat(retval[1][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[2][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[3][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[4][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[5][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[6][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[7][1], Matchers.is(Const.LINE_CHAR));

		// line 2
		assertThat(retval[0][1], Matchers.not(Const.LINE_CHAR));
		assertThat(retval[8][1], Matchers.not(Const.LINE_CHAR));
		assertThat(retval[9][1], Matchers.not(Const.LINE_CHAR));

		for (int i = 0; i <= 9; i++) {
			// line 1
			assertThat(retval[i][0], Matchers.not(Const.LINE_CHAR));

			// line 3 to 10
			for (int j = 2; j <= 9; j++) {
				assertThat(retval[i][j], Matchers.not(Const.LINE_CHAR));
			}
		}
	}

	@Test
	public void testDraw_whenTypicalHorizontalX1GreaterThanX2() {
		int width = 10;
		int height = 10;
		char[][] initialContent = new char[height][width];
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		when(canvas.getCanvasContent()).thenReturn(initialContent);
		testSubject.draw(canvas, new String[] { "8", "2", "2", "2" });
		char[][] retval = canvas.getCanvasContent();
		assertThat(retval, Matchers.is(initialContent));

		// Use index [1][1] because array starts from 0
		assertThat(retval[1][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][2], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][3], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][4], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][5], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][6], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[1][7], Matchers.is(Const.LINE_CHAR));

		// line 2
		assertThat(retval[1][0], Matchers.not(Const.LINE_CHAR));
		assertThat(retval[1][8], Matchers.not(Const.LINE_CHAR));
		assertThat(retval[1][9], Matchers.not(Const.LINE_CHAR));

		for (int i = 0; i <= 9; i++) {
			// line 1
			assertThat(retval[0][i], Matchers.not(Const.LINE_CHAR));

			// line 3 to 10
			for (int j = 2; j <= 9; j++) {
				assertThat(retval[j][i], Matchers.not(Const.LINE_CHAR));
			}
		}

	}

	@Test
	public void testDraw_whenTypicalVerticalX1GreaterThanX2() {
		int width = 10;
		int height = 10;
		char[][] initialContent = new char[height][width];
		when(canvas.getWidth()).thenReturn(width);
		when(canvas.getHeight()).thenReturn(height);
		when(canvas.getCanvasContent()).thenReturn(initialContent);
		testSubject.draw(canvas, new String[] { "2", "8", "2", "2" });
		char[][] retval = canvas.getCanvasContent();
		assertThat(retval, Matchers.is(initialContent));

		// Use index [1][1] because array starts from 0
		assertThat(retval[1][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[2][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[3][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[4][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[5][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[6][1], Matchers.is(Const.LINE_CHAR));
		assertThat(retval[7][1], Matchers.is(Const.LINE_CHAR));

		// line 2
		assertThat(retval[0][1], Matchers.not(Const.LINE_CHAR));
		assertThat(retval[8][1], Matchers.not(Const.LINE_CHAR));
		assertThat(retval[9][1], Matchers.not(Const.LINE_CHAR));

		for (int i = 0; i <= 9; i++) {
			// line 1
			assertThat(retval[i][0], Matchers.not(Const.LINE_CHAR));

			// line 3 to 10
			for (int j = 2; j <= 9; j++) {
				assertThat(retval[i][j], Matchers.not(Const.LINE_CHAR));
			}
		}
	}

}
