#include "Bluetooth.h"
#include "StreamHelper.h"

Bluetooth bluetooth(&Serial3, 16);

void setup() {
	Serial.begin(9600);
  Serial3.begin(9600);

	bluetooth.beginSetup(1);
 
  if(bluetooth.setupEcho()) { // Bluetooth board if found
    bluetooth.setupBaund(9600);
    bluetooth.setupRole(0);
    bluetooth.setupName("TimNew-BlueTooth");
    bluetooth.setupSecret("0000");
  }
  else {
    Serial.println("Bluetooth Adapter not found");
  }
  
  Serial.println(bluetooth.endSetup());
}

void loop() {
	if(Serial.available()) {
		bluetooth.write(Serial.read());
    bluetooth.flush();
	}

	if(bluetooth.available()) {
		Serial.write(bluetooth.read());
    Serial.flush();
	}
}