package com.example.spring_project;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.spring_project.Crud;

@SpringBootApplication
@RestController
public class SpringProjectApplication {
	
	@GetMapping("/get_score")
    public Object getData() {
		// Crud.getData();
    	return Crud.getData();
    }

	@PostMapping("/post_score")
	public String postData(@RequestParam("score") Integer score){
		String result = Crud.insertData(score);
		String str = "";
		if(result == "ok"){
			str = "{\"result\":\"ok\"}";
		}
		else{
			str = "{\"result\":\"error\"}";
		}
		return str;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}

}
