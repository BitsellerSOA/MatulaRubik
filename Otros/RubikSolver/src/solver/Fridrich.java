package solver;

import java.util.LinkedList;
import java.util.Queue;

public class Fridrich {
	public Queue<String> resultado = new LinkedList<>();

	private Cubo cubo;
	
	private void DantiHorario() {
		cubo.DantiHorario();
		resultado.add(cubo.D_ANTI_HORARIO);
	}
	private void Dhorario() {
		cubo.Dhorario();
		resultado.add(cubo.D_HORARIO);
	}
	private void RantiHorario() {
		cubo.RantiHorario();
		resultado.add(cubo.R_ANTI_HORARIO);
	}
	private void Rhorario() {
		cubo.Rhorario();
		resultado.add(cubo.R_HORARIO);
	}
	private void UantiHorario() {
		cubo.UantiHorario();
		resultado.add(cubo.U_ANTI_HORARIO);
	}
	private void LantiHorario() {
		cubo.LantiHorario();
		resultado.add(cubo.L_ANTI_HORARIO);
	}
	private void Lhorario() {
		cubo.Lhorario();
		resultado.add(cubo.L_HORARIO);
	}
	private void BantiHorario() {
		cubo.DantiHorario();
		resultado.add(cubo.B_ANTI_HORARIO);
	}
	private void Bhorario() {
		cubo.Bhorario();
		resultado.add(cubo.B_HORARIO);
	}
	private void FantiHorario() {
		cubo.FantiHorario();
		resultado.add(cubo.F_ANTI_HORARIO);
	}
	private void Fhorario() {
		cubo.Fhorario();
		resultado.add(cubo.F_HORARIO);
	}
	
	public void HacerCruz()
	{
		int posActual[] = new int[2];
		int cantPiezasPuestas = 0;

		while(cantPiezasPuestas < 4){

			posActual = HayBlancosEnElMedio(); // Para los blancos que halla en la posicion 3 y 5 de las capas F, L, R o D;
			if(  posActual[0] == -1){
				PonerAbajoDesdeElMedio(posActual);
				cantPiezasPuestas++;
			}
			else {
				posActual = HayBlancosArriba(); // Para los blancos que halla en U
				if(  posActual[0] == -1){
					PonerAbajoDesdeU(posActual);
					cantPiezasPuestas++;
				}
				else {
					posActual = HayBlancosEnD(); // Para los blancos que halla mal puestos en D; ya los pone arriba
					if(  posActual[0] == -1){
						PonerAbajoDesdeU(posActual); // Usa este porque HayBlancosEnD() ya te los deja puestos en U
						cantPiezasPuestas++;
					}
				}

			}		
		}
	}

	private void PonerAbajoDesdeElMedio( int[] posActual){
		int caraActualArista; // en que cara va  a estar la otra parte de la arista
		int caraDestino;	  // en que cara tiene que quedar la otra parte de la arista
		int distancia;		  // cuantos giros D va  atener q hacer
		int i;

		// determina en que cara va  a estar la otra parte de la arista
		switch(posActual[1])
		{
		case 1: caraActualArista = 0; // F
		break;
		case 3: caraActualArista = 3; // L
		break;
		case 5: caraActualArista = 2; // B
		break;
		case 7: caraActualArista = 1; // R
		break;
		default: System.out.println( "errror en el swtchde calcularcantidaddeD" );
		caraActualArista = -100; // R
		break;
		}		
		caraDestino = cubo.GetCara( cubo.GetColor(1, caraActualArista)); //determina en que cara tiene que quedar la otra parte de la arista

		distancia = caraDestino - caraActualArista;            // calcula la cantidad de giros en D que va  atener q hacer

		// hace q 3 giros para un lado los cambie por 1 giro para el otro
		if(distancia == 3){
			distancia = -1;
		}
		if(distancia == -3){
			distancia = 1;
		}

		// hace los giros en D correspondientes
		if(distancia < 0){
			for(i=0; i<distancia; i++){
				DantiHorario();
			}
		}
		else {
			for(i=0; i<distancia; i++){
				Dhorario();
			}
		}

		// Hace el giro doble para q quede bien puesta
		cubo.GirarHorario(caraActualArista);
		cubo.GirarHorario(caraActualArista);


		//rehace los giros en D (para el otro lado) para q quede todo bien
		if(distancia < 0){
			for(i=0; i<distancia; i++){
				Dhorario();
			}
		}
		else {
			for(i=0; i<distancia; i++){
				DantiHorario();
			}
		}
	}
	
	private void PonerAbajoDesdeU( int[] posActual){
		int caraActualArista; // en que cara va  a estar la otra parte de la arista
		int caraDestino;	  // en que cara tiene que quedar la otra parte de la arista
		int distancia;		  // cuantos giros D va  atener q hacer
		int i;


		// determina en que cara va  a estar la otra parte de la arista
		if( posActual[0] == 5 ){
			caraActualArista = posActual[1] + 1;
			if(caraActualArista == 4)
				caraActualArista = 0;
			caraDestino = cubo.GetCara( cubo.GetColor(3, caraActualArista)); //determina en que cara tiene que quedar la otra parte de la arista
		}
		else{
			caraActualArista= posActual[1] - 1;
			if(caraActualArista == -1)
				caraActualArista = 3;
			caraDestino = cubo.GetCara( cubo.GetColor(5, caraActualArista)); //determina en que cara tiene que quedar la otra parte de la arista
		}		

		distancia = caraDestino - caraActualArista;   				// calcula la cantidad de giros en D que va  atener q hacer         

		// hace q 3 giros para un lado los cambie por 1 giro para el otro
		if(distancia == 3){
			distancia = -1;
		}
		if(distancia == -3){
			distancia = 1;
		}

		// Hace los giros en D necesarios
		if(distancia < 0){
			for(i=0; i<distancia; i++){
				DantiHorario();
			}
		}
		else {
			for(i=0; i<distancia; i++){
				Dhorario();
			}
		}

		// Hace el giro(L, R F o B) para q quede bien puesta
		if( posActual[1] == 3){
			cubo.GirarHorario(caraActualArista);
		}
		else{
			cubo.GirarAntiHorario(caraActualArista);
		}

		//rehace los giros de abajo (para el otro lado) para q quede todo bien puesto
		if(distancia < 0){
			for(i=0; i<distancia; i++){
				Dhorario();
			}
		}
		else {
			for(i=0; i<distancia; i++){
				DantiHorario();
			}
		}

	}

	// para blancos que esten mal puestos en D. solo los q estan mal puestos
	public int[] HayBlancosEnD(){
		int posActual[] = new int[2];
		char ColorAbajo = cubo.GetColor(4, cubo.D); 

		if( cubo.GetColor(1, cubo.D) == ColorAbajo){
			if( cubo.GetColor(1,cubo.F) != cubo.GetColor(4,cubo.F)){
				// gira dos veces para ponerlo arriba y devuelve la posicion nueva
				Fhorario();
				Fhorario();
				posActual[0] = cubo.U;
				posActual[1] = 7;
				return posActual;
			}
		}
		if( cubo.GetColor(3, cubo.D) == ColorAbajo){
			if( cubo.GetColor(3,cubo.L) != cubo.GetColor(4,cubo.L)){
				// gira dos veces para ponerlo arriba y devuelve la posicion nueva
				Lhorario();
				Lhorario();
				posActual[0] = cubo.U;
				posActual[1] = 3;
				return posActual;
			}
		}
		if( cubo.GetColor(5, cubo.D) == ColorAbajo){
			if( cubo.GetColor(5,cubo.R) != cubo.GetColor(4,cubo.R)){
				// gira dos veces para ponerlo arriba y devuelve la posicion nueva
				Rhorario();
				Rhorario();
				posActual[0] = cubo.U;
				posActual[1] = 5;
				return posActual;
			}
		} 
		if( cubo.GetColor(7, cubo.D) == ColorAbajo){
			if( cubo.GetColor(7,cubo.B) != cubo.GetColor(4,cubo.B)){
				// gira dos veces para ponerlo arriba y devuelve la posicion nueva
				this.Bhorario();
				this.Bhorario();
				posActual[0] =  cubo.U;
				posActual[1] = 1;
				return posActual;
			}
		}

		posActual[0] = -1;
		return posActual;
	}


	//Busca los blancos en la posicion 3 y 5 de las capas F, L, R o D;
	public int[] HayBlancosEnElMedio(){
		int i;
		int posActual[] = new int[2];
		char ColorAbajo =  cubo.GetColor(4, cubo.D); 
		for(i=0; i<4; i++){
			if( cubo.GetColor(7,i) == ColorAbajo){				
				posActual[0] = i;
				posActual[1] = 5;
				return posActual;
			}
		}
		for(i=0; i<4; i++){
			if( cubo.GetColor(1,i) == ColorAbajo){
				posActual[0] = i;
				posActual[1] = 3;
				return posActual;
			}
		}
		posActual[0] = -1;
		return posActual;
	}

	//Busca los blancos en las pociciones 1, 3, 5 o 7 de la capa U
	public int[] HayBlancosArriba(){
		int i;
		int posActual[] = new int[2];
		char ColorAbajo = cubo.GetColor(4, cubo.D);

		for(i=1; i <= 7; i+=2){ //mira las posiciones 1 3 5 y 7 (las aristas)

			if( cubo.GetColor(i, 4) == ColorAbajo){				
				posActual[0] = 4;
				posActual[1] = i;
				return posActual;
			}
		}

		posActual[0] = -1;
		return posActual;
	}



}
