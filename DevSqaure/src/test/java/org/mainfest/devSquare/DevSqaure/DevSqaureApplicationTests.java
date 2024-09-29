package org.mainfest.devSquare.DevSqaure;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevSqaureApplicationTests {

	@Test
	void contextLoads() {
		var obj = new Object(){
			int id=6;
		};
		System.out.println(new Gson().toJson(obj));
	}

}
