# MatulaRubik
Proyecto SOA UNLAM 2018 1 C Martes Noche

### Colaboradores
Radice Adrian
Sanabria Facundo

### Introduccion
El proyecto es una implementacion de IOT, el mismo consiste en un SE que tendra el fin de resolver el cubo rubik. 

### Objetivo
Resolver el cubo rubik, mediante un sistema automatico.

### Limite
Desde que el cubo es analizado por la App Movil y el SE devuelve el cubo resuelto como fue indicado por la APP.

### Alcance
- La App Android 'MatrulaRubik' analizara el cubo, capturando la posicion de los colores con el uso de la libreria OpenCV 3.4.1, y entregara en un repositorio web la solución a ser tratada por el SE.
- La App Android 'MatrulaRubik' registra un historial de logros ( Tiempos previos logrados ) y configuración del Cubo.
- El sketch de Arduino retirara la solucion entregada por 'MatrulaRubik' del repositorio WEB.
- El sketch de Arduino interpretera la solucion del cubo y la traducira en los movimientos mecanicos.
