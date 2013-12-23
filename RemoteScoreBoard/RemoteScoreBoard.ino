#include "SegmentDisplay.h"
#include "StreamHelper.h"
#include "Bluetooth.h"

#define BUFFER_SIZE 16
#define DAT_PIN 6
#define SCK_PIN 7
#define RCK_PIN 8

#define AT_PIN  2
#define LED_PIN 13

SegmentDisplay display(DAT_PIN, SCK_PIN, RCK_PIN);
StreamHelper serialHelper(&Serial, BUFFER_SIZE);
Bluetooth bluetooth(&Serial, AT_PIN);

int i = 0;

void setup() {
  pinMode(LED_PIN, OUTPUT);

  display.setup();

  Serial.begin(9600);
  
  bluetooth.beginSetup(1);
 
  if(bluetooth.setupEcho()) { // Bluetooth board if found
    bluetooth.setupBaund(9600);
    bluetooth.setupRole(0);
    bluetooth.setupName("TimNew-BlueTooth");
    bluetooth.setupSecret("0000");
  }
  else {
    digitalWrite(LED_PIN, HIGH);
  }
  
  bluetooth.endSetup();
}

void loop() {
  if(bluetooth.available()) {
    display.write(serialHelper.readStringUntil('\n'));
  };

  display.routine();
}



