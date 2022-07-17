package com.example.javaSurvey;

import com.example.javaSurvey.constant.Const;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;

@SpringBootApplication
public class SurveyApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
	}

}
