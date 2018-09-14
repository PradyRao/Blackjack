package core;

import junit.framework.TestCase;

public class InputType_Test extends TestCase{
	public void TestConsole() {
		InputType type = new InputType("c");
		
		assert("c", type.getType());
	}
	public void TestFile() {
		InputType type = new InputType("f");
		
		assert("f", type.getType());
	}
}
