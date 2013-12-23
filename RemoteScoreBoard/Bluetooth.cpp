#include "Bluetooth.h"

unsigned int Bluetooth::write(byte data) {
  return stream->write(data);
}

int Bluetooth::available() {
  return stream->available();
}

int Bluetooth::read() {
  return stream->read();
}

int Bluetooth::peek() {
  return stream->peek();
}

void Bluetooth::flush() {
  return stream->flush();
}

Bluetooth::Bluetooth( Stream* stream, byte atPin) :
  stream(stream),
  atPin(atPin)
{
  pinMode(atPin, OUTPUT);  
  
  while(stream->available()) {
    stream->read();
  }
}

void Bluetooth::beginSetup(sbyte retryTimes) {
  digitalWrite(atPin, HIGH);
  this->retry = retryTimes;
  setupResult = "\r\n== Bluetooth Setup ==\r\n\r\n";
}

void Bluetooth::setupPrint(String output) { setupResult += output; }
void Bluetooth::setupPrintln() { setupResult += "\r\n"; }
void Bluetooth::setupPrintln(String output) { 
  setupPrint(output);
  setupPrintln(); 
}
void Bluetooth::setupPrintln(String label, String value) {
  setupPrint(label);
  setupPrint(": ");
  setupPrint(value);
  setupPrintln();
}
void Bluetooth::setupPrintln(String label, int index, String value) {
  setupPrint(label);
  setupPrint("[");
  setupPrint(String(index, (byte)DEC));
  setupPrint("]: ");
  setupPrint(value);
  setupPrintln();
}


String Bluetooth::endSetup() {
  setupPrint("\r\n\r\n== Bluetooth Setup End ==\r\n");
  digitalWrite(atPin, LOW);
  return setupResult;
}

boolean Bluetooth::setupEcho(sbyte retryTimes) { return sendCommand("AT", retryTimes); }
boolean Bluetooth::setupBaund(int baund, sbyte retryTimes) { return sendCommand("AT+UART=" + String(baund, (byte)DEC) + ",0,0", retryTimes); }
boolean Bluetooth::setupRole(byte role, sbyte retryTimes) { return sendCommand("AT+ROLE=" + String(role, (byte)DEC), retryTimes); }
boolean Bluetooth::setupName(String name, sbyte retryTimes) { return sendCommand("AT+NAME=" + String(name), retryTimes); }
boolean Bluetooth::setupSecret(String secretPin, sbyte retryTimes) { return sendCommand("AT+PSWD=" + String(secretPin), retryTimes); }
boolean Bluetooth::setupRemoveParis(sbyte retryTimes) { return sendCommand("AT+RMAAD", retryTimes); }

boolean Bluetooth::sendCommand(char* command, sbyte retryTimes) {
  return sendCommand(String(command), retryTimes);
}

boolean Bluetooth::sendCommand(String command, sbyte retryTimes) {
  byte times;
  if(retryTimes == -1)
    retryTimes = retry;

    setupPrintln("Command", command);
  
  for(times = 0; times < retryTimes; times ++) {    
    stream->println(command); 
    stream->flush();
   
    String result = stream->readStringUntil('\n');
    if(result=="")
      result = "<TimeOut>";
      
    setupPrintln("Result", times, result);
    result.trim();
    if( result == "OK") {
      break;
    }
  }
  
  if(times == retryTimes) {
    setupPrint("Result: Failed\r\n\r\n");
    return false;
  }
  else {
    setupPrint("Result: Succeeded\r\n\r\n");
    return true;
  }
}



