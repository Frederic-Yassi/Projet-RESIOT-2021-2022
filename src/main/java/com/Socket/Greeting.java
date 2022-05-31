package com.Socket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor

public class Greeting {
	private String message;

    public Greeting(String string) {
		message=string;
    	// TODO Auto-generated constructor stub
	}

	


}