#include <Cubo.h>
#include <DRV8825.h>
#include <SPI.h>
#include <Ethernet.h>
#include <ArduinoJson.h>  

byte mac[] = {0x98, 0x4F, 0xEE, 0x01, 0x11, 0x90 };    
IPAddress ip(192,168,1,50);
EthernetServer server(8080);
EthernetClient client;
const size_t MAX_BODY_SIZE = 200;      
String respuesta = "";

const int fwdPin=52;
const int revPin=53;
const int pasos=200;
const int steps=100;
const int grados120=100;
const int grados90=50;
const int grados45=25;
const int tiempoLectora=10;
const int pausa=5000;

DRV8825 arriba(40,39,28);
DRV8825 abajo(45,44,42);
DRV8825 lateral(50,49,47);

Cubo cubo(arriba, abajo, lateral, fwdPin, revPin, pasos, steps, grados120, grados90, grados45, tiempoLectora, pausa);

void setup() {
  Serial.begin(9600);
  pinMode(3, OUTPUT);
  Ethernet.begin(mac, ip);
  server.begin();
  delay(100);
}


void loop() 
{
  client = server.available();
  if (client) 
  {
    Serial.print("Cliente Conectado...");
    boolean currentLineIsBlank = true;
    respuesta = "";
    while (client.connected()) 
    {
      if (client.available())
      {
        char c = client.read();
        respuesta += c;
        if (c == '\n' && currentLineIsBlank && respuesta.startsWith("POST")) 
        {
          analizarPost();
          break;
        }
        if (c == '\n')
        {
          currentLineIsBlank = true;
        } 
        else if (c != '\r')
        {
          currentLineIsBlank = false;
        }
      }
    }
   // delay(1);
    client.stop();
  }
}
bool analizarPost()
{
  char body_post[MAX_BODY_SIZE]="";
  char estado_led[10]="";
  String respuesta_aux = respuesta.substring(respuesta.indexOf("/led/"));
  int num_pin = '3';
  if(leerBodyPost(respuesta,body_post)==false)
  {
     Serial.println("Error al leer el campo Body");
     enviarHttpResponse_BadRequest(client);
     return false;
  }
  if(num_pin=='3')
  {
     if(parserBodyPost(body_post,estado_led)==false)
     {
        Serial.println("Error al realizar el parser");
        enviarHttpResponse_BadRequest(client);
        return false;
     }
     else
     {
       cubo.Mover(estado_led[0]);
      
       Serial.print("Moviendo: ");
       Serial.println(estado_led);
       delay(1000);
       enviarHttpResponse_OK(client);
       return true;
     }
     
  }
  Serial.println("Numero de Pin Incorrecto..");
  enviarHttpResponse_BadRequest(client);

  return false;
}
bool leerBodyPost(String respuesta,char * body_post)
{
  int data_length=-1;
  int i=0;
  char c;
  String body_tam = respuesta.substring(respuesta.indexOf("Content-Length:") + 15);
  body_tam.trim();
  data_length = 11;
  if(data_length>MAX_BODY_SIZE)
  {
    Serial.println("ERROR MAX_BODY_SIZE NO ES LO SUFICIENTEMENTE GRANDE PARA ALMACENAR EL CONTENIDO DE BODY");
    return false; 
  }
  while(i < data_length)
  {
    c = client.read();
    body_post[i] =c;
    i++;
  }
  //Serial.println(body_post);
  return true;
}
bool parserBodyPost(char * content, char * estado)
{
  const size_t BUFFER_SIZE = JSON_OBJECT_SIZE(1); 
  
  DynamicJsonBuffer jsonBuffer(BUFFER_SIZE);
  JsonObject& root = jsonBuffer.parseObject(content);
  if (!root.success()) 
  {
    Serial.println("JSON parsing failed!");
    return false;
  }
  strcpy(estado, root["MOV"]);
  //Serial.println("estado:");
  //Serial.println(estado);
  return true;
}


void enviarHttpResponse_OK(EthernetClient& client) 
{
  client.println("HTTP/1.1 200 OK");
  //client.println("Content-Type: application/json");
  client.println("Connection: close");
  client.println();
}

void enviarHttpResponse_BadRequest(EthernetClient& client) 
{
  client.println("HTTP/1.1 400 BAD REQUEST");
  client.println("Content-Type: text/html");
  client.println("Connection: close");
  client.println();
  client.println("<!DOCTYPE HTML>");
  client.println("<html> <body>");
  client.println("BAD REQUEST"); 
  client.println("</body> </html>");
  client.println();
}

