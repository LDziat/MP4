package jenkins.model;

import hudson.model.AbstractItem;
import hudson.model.Action;
import java.util.Collection;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.TestExtension;
import  jenkins.model.IdStrategy;

public class IdStrategyTest {
	
	IdStrategy strategy;
	
	@Test public void test1() {
		String test = "helloWorld~test";
		assertEquals(strategy.idFromFilename(test),"helloWorldTest");
	}
	
	@Test public void test2() {
		String test = "h3110 w0r1d";
		assertEquals(strategy.idFromFilename(test),"helloWorld");
	}
	
	@Test public void test3() {
		String test = "helloWorld$000f";
		assertEquals(strategy.idFromFilename(test),"helloWorld15");
	}

}