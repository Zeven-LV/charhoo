package com.charhoo.construct.bridge;

public class BridgeTest {

	public static void main(String[] args) {  
        
        Bridge bridge = new MyBridge();  
          
        /*���õ�һ������*/  
        Place place1 = new PlaceOfTaiyuan();  
        bridge.setPlace(place1);  
        bridge.getPlaceName();  
          
        /*���õڶ�������*/  
        Place place2 = new PlaceOfBeijing();  
        bridge.setPlace(place2);  
        bridge.getPlaceName();  
    }  
	
}
