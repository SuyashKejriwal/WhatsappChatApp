package com.example.whatsapp_backend.shared.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class State<T,V> {
	
	private StatusNotification status;
	private T value;
	private V error;
	
	
	
	
}
