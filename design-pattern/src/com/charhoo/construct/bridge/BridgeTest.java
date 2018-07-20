package com.charhoo.construct.bridge;

public class BridgeTest {

	public static void main(String[] args) {  
        
        Bridge bridge = new MyBridge();  
          
        /*调用第一个对象*/  
        Place place1 = new PlaceOfTaiyuan();  
        bridge.setPlace(place1);  
        bridge.getPlaceName();  
          
        /*调用第二个对象*/  
        Place place2 = new PlaceOfBeijing();  
        bridge.setPlace(place2);  
        bridge.getPlaceName();  
    }  
	
}
