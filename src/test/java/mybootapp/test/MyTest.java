package mybootapp.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import mybootapp.Starter;

@SpringBootTest
@ContextConfiguration(classes = Starter.class)
public class MyTest {

//	@Autowired
//	CourseRepository cr;
//
//	@Test
//	public void testSave() {
//		var c = cr.save(Course.builder().name("Test 1").build());
//		var c2 = cr.findById(c.getId());
//		assertEquals(c2.get().getName(), "Test 1");
//		System.err.printf("course id = %d\n", c.getId());
//	}

}
