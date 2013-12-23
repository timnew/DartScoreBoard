#include "StreamHelper.h"

StreamHelper::StreamHelper(Stream* stream, int bufferSize) :
	stream(stream),
	size(0),
	buffer(new char[bufferSize]),
	bufferSize(bufferSize)
 { }

StreamHelper::~StreamHelper() {
	delete[] buffer;
}

byte StreamHelper::StreamHelper::readByte() {
	int data;
	while((data = stream->read()) == -1) ;
	return (byte)data;
}

byte StreamHelper::skipUntil(char terminator) {
	for(size = 0; size < bufferSize; size++)
		buffer[size] = 0;

	for(size = 0; size < bufferSize; size++) {
		buffer[size] = (char)readByte();
				
		if(buffer[size] == terminator) {
			size++;
			break;
		}		
	}

	return size;
}

char* StreamHelper::readStringUntil(char terminator) {
	skipUntil(terminator);
	buffer[size] = 0;
	return buffer;
}

String* StreamHelper::readNewStringUntil(char terminator) {
	readStringUntil(terminator);
	return new String(buffer);
}

byte StreamHelper::skipUntil(char terminator1, char terminator2) {	
	for(size = 0; size < bufferSize; size++)
		buffer[size] = 0;

	for(size = 0; size < bufferSize; size++) {
		buffer[size] = (char)readByte();
				
		if(buffer[size] == terminator1 || buffer[size] == terminator2) {
			size++;
			break;
		}		
	}

	return size;
}

char* StreamHelper::readStringUntil(char terminator1, char terminator2) {	
	skipUntil(terminator1, terminator2);
	buffer[size] = 0;
	return buffer;
}

String* StreamHelper::readNewStringUntil(char terminator1, char terminator2) {
	readStringUntil(terminator1, terminator2);
	return new String(buffer);
}

