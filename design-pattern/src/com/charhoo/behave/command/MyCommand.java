package com.charhoo.behave.command;


public class MyCommand implements Command{

	private Receiver receiver;
	private String context;

	public MyCommand(Receiver receiver,String context) {
        this.receiver = receiver;
        this.context = context;
    }

	@Override
	public void exe() {
		// TODO Auto-generated method stub
		System.out.println("Command:"+context);
		receiver.action();
	}



}
