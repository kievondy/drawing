package com.cs.drawing.model;

import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

public class CanvasTest {

	Canvas testSubject = new Canvas();

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNullArgs() {
		testSubject.draw(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenInvalidArgsLength() {
		testSubject.draw(new String[] { "10", "10", "10" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNonNumericWidth() {
		testSubject.draw(new String[] { "a", "10" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNonNumericHeight() {
		testSubject.draw(new String[] { "10", "a" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNegativeNumericWidth() {
		testSubject.draw(new String[] { "-10", "10" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw_whenNegativeNumericHeight() {
		testSubject.draw(new String[] { "10", "-10" });
	}

	@Test
	public void testDraw_whenTypical() {
		testSubject.draw(new String[] { "10", "10" });
		char[][] canvasContent = testSubject.getCanvasContent();
		assertThat(canvasContent.length, Matchers.is(10));
		assertThat(canvasContent[0].length, Matchers.is(10));
		assertThat(Character.toString(canvasContent[0][0]), Matchers.is(StringUtils.SPACE));
	}

	@Test
	public void testDraw_whenTypicalAsPerRequirementSample() {
		testSubject.draw(new String[] { "20", "4" });
		char[][] canvasContent = testSubject.getCanvasContent();

		assertThat(canvasContent.length, Matchers.is(4));
		assertThat(canvasContent[0].length, Matchers.is(20));
		assertThat(Character.toString(canvasContent[0][0]), Matchers.is(StringUtils.SPACE));

		new Line().draw(testSubject, new String[] { "1", "2", "6", "2" });
		String printResult = testSubject.print();

		StringBuilder sb = new StringBuilder()
				.append("----------------------")
				.append(StringUtils.LF)
				.append("|                    |")
				.append(StringUtils.LF)
				.append("|xxxxxx              |")
				.append(StringUtils.LF)
				.append("|                    |")
				.append(StringUtils.LF)
				.append("|                    |")
				.append(StringUtils.LF)
				.append("----------------------");

		assertThat(printResult, Matchers.is(sb.toString()));

		new Line().draw(testSubject, new String[] { "6", "3", "6", "4" });
		printResult = testSubject.print();

		sb = new StringBuilder()
				.append("----------------------")
				.append(StringUtils.LF)
				.append("|                    |")
				.append(StringUtils.LF)
				.append("|xxxxxx              |")
				.append(StringUtils.LF)
				.append("|     x              |")
				.append(StringUtils.LF)
				.append("|     x              |")
				.append(StringUtils.LF)
				.append("----------------------");

		assertThat(printResult, Matchers.is(sb.toString()));

		new Rectangle().draw(testSubject, new String[] { "14", "1", "18", "3" });
		printResult = testSubject.print();

		sb = new StringBuilder()
				.append("----------------------")
				.append(StringUtils.LF)
				.append("|             xxxxx  |")
				.append(StringUtils.LF)
				.append("|xxxxxx       x   x  |")
				.append(StringUtils.LF)
				.append("|     x       xxxxx  |")
				.append(StringUtils.LF)
				.append("|     x              |")
				.append(StringUtils.LF)
				.append("----------------------");

		assertThat(printResult, Matchers.is(sb.toString()));

		new BucketFill().draw(testSubject, new String[] { "10", "3", "o" });
		printResult = testSubject.print();

		sb = new StringBuilder()
				.append("----------------------")
				.append(StringUtils.LF)
				.append("|oooooooooooooxxxxxoo|")
				.append(StringUtils.LF)
				.append("|xxxxxxooooooox   xoo|")
				.append(StringUtils.LF)
				.append("|     xoooooooxxxxxoo|")
				.append(StringUtils.LF)
				.append("|     xoooooooooooooo|")
				.append(StringUtils.LF)
				.append("----------------------");

		assertThat(printResult, Matchers.is(sb.toString()));
	}

	@Test
	public void testPrint() {
		StringBuilder expected = new StringBuilder()
				.append("-----")
				.append(StringUtils.LF)
				.append("|   |")
				.append(StringUtils.LF)
				.append("|   |")
				.append(StringUtils.LF)
				.append("|   |")
				.append(StringUtils.LF)
				.append("-----");

		testSubject.draw(new String[] { "3", "3" });
		String retval = testSubject.print();
		assertThat(retval, Matchers.is(expected.toString()));
	}

}
