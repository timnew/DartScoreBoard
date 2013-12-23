#include "SegmentDisplay.h"

byte digitSegments[] = {0xc0, 0xf9, 0xa4, 0xb0, 0x99, 0x92, 0x82, 0xf8, 0x80, 0x90};

SegmentDisplay::SegmentDisplay(byte dataPin, byte sckPin, byte rckPin):
  dataPin(dataPin),
  sckPin(sckPin),
  rckPin(rckPin) { 
    buffer = new byte[8];
    memset(buffer, 0xFF, 8);
    index = 0;
}

SegmentDisplay::~SegmentDisplay() {
  delete[] buffer;
}

void SegmentDisplay::setup() {
  pinMode(dataPin, OUTPUT);     
  pinMode(sckPin, OUTPUT);     
  pinMode(rckPin, OUTPUT);   

  digitalWrite(dataPin, LOW);
  digitalWrite(sckPin, LOW);
  digitalWrite(rckPin, LOW);
}

void SegmentDisplay::write(String output) {
  output.getBytes(buffer, 8);

  translateBuffer();
}

void SegmentDisplay::write(char* output) {
  memcpy(buffer, output, 8);

  translateBuffer();
}

void SegmentDisplay::translateBuffer() {
  for(int i = 0; i < 8; i++) {
    char current = buffer[i];
    
    if(current >= '0' && current <= '9')  
      buffer[i] = digitSegments[current - '0'];  
    else 
      buffer[i] = 0xFF;
  }
}

void SegmentDisplay::routine() {
  selectDigit(index);
  writeByte(buffer[index]);
  flush();

  index = (index + 1) % 8;
}

void SegmentDisplay::writeByte(byte data) {
  for(int i = 0; i < 8; i++) {
    digitalWrite(sckPin, LOW);
    digitalWrite(dataPin, data & 0x80);
    digitalWrite(sckPin, HIGH);    
    data <<=1;    
  }
}

void SegmentDisplay::selectDigit(int index) {
  byte digitMask = 0x01 << index;
  writeByte(digitMask);
}


void SegmentDisplay::flush() {
  digitalWrite(rckPin, HIGH);
  digitalWrite(rckPin, LOW);
}