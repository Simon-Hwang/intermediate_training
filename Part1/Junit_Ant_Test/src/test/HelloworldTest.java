import static org.junit.Assert.*;
import org.junit.Test;

public class HelloworldTest{
	public Helloworld test = new Helloworld();

	@Test
	public void testHello(){
		test.Hello();
		assertEquals("HelloWorld", test.getStr());
	}
}
