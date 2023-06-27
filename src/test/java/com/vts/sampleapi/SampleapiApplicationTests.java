package com.vts.sampleapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
class SampleapiApplicationTests {

	Calculator undertest = new Calculator();

	@Test
	void itShouldAddTwoNumbers(){
		//given
		int a = 59;
		int b = 11;

		//when
		int res = undertest.add(a,b);

		//then
		assertThat(res).isEqualTo(70);

	}
	void contextLoads() {
	}
	class Calculator{
		int add(int a, int b){
			return a+ b;
		}
	}
}
