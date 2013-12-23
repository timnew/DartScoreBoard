#ifndef __SEGMENT_DISPLAY_H__
#define __SEGMENT_DISPLAY_H__

#include "Arduino.h"

/*
This library is used to drive 74595 based 7 segment display
Datasheet can be referenced at https://www.sparkfun.com/datasheets/IC/SN74HC595.pdf
Component can be bought at http://item.taobao.com/item.htm?spm=a1z10.5.w4002-2177312894.12.jDNtYN&id=18406565619
*/

#ifndef byte
#define byte unsigned char
#endif byte

class SegmentDisplay {

private:
  
  byte dataPin, sckPin, rckPin;
  byte* buffer;
  byte index;

public:  
  /*
  * dataPin: Pin holds bit data
  * sckPin: Pin holds shift register clock
  * rckPin: Pin holds register clock
  */
  SegmentDisplay(byte dataPin, byte sckPin, byte rckPin);

  void setup();

  void write(String output);

  void write(char* output);

  void routine();

  ~SegmentDisplay();

private:

  void translateBuffer();
 
  void writeByte(byte data);

  void selectDigit(int index);
  
  void flush();
};

#endif __SEGMENT_DISPLAY_H__