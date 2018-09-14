package core;

import org.junit.Test;

import junit.framework.TestCase;

public class InputType_Test extends TestCase{
	@Test
	public void testConsole() {
		InputType type = new InputType("c");
		
		assertEquals("c", type.getType());
	}
	@Test
	public void testFile() {
		InputType type = new InputType("f");
		
		assertEquals("f", type.getType());
	}
}
