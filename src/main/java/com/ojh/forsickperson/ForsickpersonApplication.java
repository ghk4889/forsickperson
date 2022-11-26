package com.ojh.forsickperson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class ForsickpersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForsickpersonApplication.class, args);
	}

	//form 태그에서 PUT, PATCH, DELETE 메서드로 요청을 보내기 위함.
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
}
