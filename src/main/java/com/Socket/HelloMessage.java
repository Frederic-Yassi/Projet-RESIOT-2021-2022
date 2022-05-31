package com.Socket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloMessage {
    private String name;

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}