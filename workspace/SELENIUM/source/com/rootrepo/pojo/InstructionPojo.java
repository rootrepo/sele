package com.rootrepo.pojo;

public class InstructionPojo {

	public String action;
	public String value;
	
	@Override
	public String toString() {

		System.out.println("action = "+ action + "Value = "+ value );
		return super.toString();
	}
}
