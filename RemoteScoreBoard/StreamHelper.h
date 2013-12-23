#ifndef _STREAM_HELPER_H_
#define _STREAM_HELPER_H_

#include "Arduino.h"

class StreamHelper {

private:
	
	Stream* stream;
	int size;
	char* buffer;
	int bufferSize;

public:

	StreamHelper(Stream* stream, int bufferSize);
	~StreamHelper();

	byte readByte();

	byte skipUntil(char terminator);
	char* readStringUntil(char terminator);
	String* readNewStringUntil(char terminator);

	byte skipUntil(char terminator1, char terminator2);
	char* readStringUntil(char terminator1, char terminator2);
	String* readNewStringUntil(char terminator1, char terminator2);

};

#endif _STREAM_HELPER_H_