package com.charhoo.behave.mediator;

public abstract class Custmer {
	
	private Mediator mediator;  
    
    public Mediator getMediator(){  
        return mediator;  
    }  
      
    public Custmer(Mediator mediator) {  
        this.mediator = mediator;  
    }  
  
    public abstract void work();  

}
