package com.charhoo.construct.facade;

public class Computer {
	
	private Cpu cpu;
	private Memory memory;
	private Disk disk;
	
	public Computer(){
		cpu = new Cpu();
		memory = new Memory();
		disk = new Disk();
	}
	
	public void startup(){
		System.out.println("computer starting...");
		cpu.startup();
		memory.startup();
		disk.startup();
		System.out.println("computer has started");
	}
	
	public void shutdown(){
		System.out.println("computer shutdown...");
		cpu.shutdown();
		memory.shutdown();
		disk.shutdown();
		System.out.println("computer has shutdown");
	}

}
