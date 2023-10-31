package com.kosta.di.sample2;

public class MessageBeanImpl implements MessageBean {
	private String name;
	private String greeting;

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGreeting() {
		return greeting;
	}


	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}


	@Override
	public void sayHello() {
		System.out.println(greeting+", "+name+"!");
	}

}
