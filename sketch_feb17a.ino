void setup() {
  // put your setup code here, to run once:
pinMode(3,OUTPUT);  //IN1 //Left Motor
pinMode(4,OUTPUT);  //IN2
pinMode(5,OUTPUT);  //IN3
pinMode(6,OUTPUT);  //IN4
pinMode(13,OUTPUT);
Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
while(Serial.available()){
  char c = Serial.read();
  if(c == 'f') {
    stopp();
    forward();  
  }
  if(c == 'b') {
      stopp();
      backward();  
   }
    if(c == 'r') {
      stopp();
      radialright();  
   }
    if(c == 'l') {
      stopp();
      radialleft();  
   }
    if(c == 's') {
      stopp();
   }
    Serial.write(c);
}
 
}

void forward(){
  digitalWrite(3,HIGH);
  digitalWrite(4,LOW);
  digitalWrite(5,HIGH);
  digitalWrite(6,LOW);
  
}


void backward(){
  digitalWrite(3,LOW);
  digitalWrite(4,HIGH);
  digitalWrite(5,LOW);
  digitalWrite(6,HIGH);
}

void stopp(){
  digitalWrite(3,LOW);
  digitalWrite(4,LOW);
  digitalWrite(5,LOW);
  digitalWrite(6,LOW);
  delay(100);
  
}

void radialright(){
  digitalWrite(3,HIGH);
  digitalWrite(4,LOW);
  digitalWrite(5,LOW);
  digitalWrite(6,LOW);
  
}

void radialleft(){
  digitalWrite(3,LOW);
  digitalWrite(4,LOW);
  digitalWrite(5,HIGH);
  digitalWrite(6,LOW);
  
}
