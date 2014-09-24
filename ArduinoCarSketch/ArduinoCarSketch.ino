
/**
 * Systems Technology Group, Inc. (STG) 
 * http://www.stgit.com 
 * Arduino Powered Car with Mobile Control
 *
 * Sakthi Dasan Sekar 
 * sakthidasans@stgit.com 
 */
 
 
int maxspeed = 255;

void setup()
{
  
   Serial.begin(9600);
   //BTSerial.begin(9600);
   
    //Setup Channel A
    pinMode(12, OUTPUT); //Initiates Motor Channel A pin
    pinMode(9, OUTPUT); //Initiates Brake Channel A pin
  
    //Setup Channel B
    pinMode(13, OUTPUT); //Initiates Motor Channel A pin
    pinMode(8, OUTPUT);  //Initiates Brake Channel A pin
}

void loop()
{
  
   if (Serial.available() > 0) {
        char ch = Serial.read();
        
        Serial.print("Received: ");        
        Serial.println(ch);     
     
       if (ch == '1') {
          // Forward control
          //Motor A forward at full speed
          digitalWrite(12, HIGH);  // Establishes forward direction 
          digitalWrite(9, LOW);    // Disble the brake for Motor A
          analogWrite(3, maxspeed);     // Spins the motor on Channel A at full speed
          //Motor B backward at half speed
          digitalWrite(13, HIGH);  // Establishes forward direction 
          digitalWrite(8, LOW);    // Disable the Brake for Motor B
          analogWrite(11, maxspeed);    // Spins the motor on Channel B at half speed
          //delay(6000);
       } else if(ch == '2'){
          // Turn Left  control
          //Motor A 
          digitalWrite(9, HIGH);  // Brake for Channel A
          //Motor B backward at half speed
          digitalWrite(13, HIGH);  // Establishes backward direction 
          digitalWrite(8, LOW);    // Disable the Brake for Motor B
          analogWrite(11, maxspeed);    // Spins the motor on Channel B at half speed
          //delay(6000);
       } else if(ch == '3'){
          // Stop Control
          digitalWrite(9, HIGH);  //Brake for Channel A
          digitalWrite(8, HIGH);  //Brake for Channel B
          //delay(2000);
       }else if(ch == '4'){
          // Turn Right  control
          //Motor A forward at full speed
          digitalWrite(12, HIGH);  // Establishes backward direction 
          digitalWrite(9, LOW);    // Disble the brake for Motor A
          analogWrite(3, maxspeed);     // Spins the motor on Channel A at full speed
          //Motor B 
          digitalWrite(8, HIGH);  //Brake for Channel B
          //delay(6000);
       } else if(ch == '5'){
          // Backward  control
          //Motor A forward at full speed
          digitalWrite(12, LOW);  // Establishes backward direction 
          digitalWrite(9, LOW);    // Disble the brake for Motor A
          analogWrite(3, maxspeed);     // Spins the motor on Channel A at full speed
          //Motor B backward at half speed
          digitalWrite(13, LOW);  // Establishes backward direction 
          digitalWrite(8, LOW);    // Disable the Brake for Motor B
          analogWrite(11, maxspeed);    // Spins the motor on Channel B at half speed
         // delay(6000);         
       }
    }
    
}


