package cubo;

import java.util.LinkedList;
import java.util.Queue;

public class Cubo {

	public final char AMARILLO = 'Y';
	public final char AZUL = 'B';
	public final char BLANCO = 'W';
	public final char ROJO = 'R';
	public final char VERDE = 'V';
	public final char NARANJA = 'N';

	public final int F = 0;
	public final int R = 1;
	public final int B = 2;
	public final int L = 3;
	public final int U = 4;
	public final int D = 5;
	
	public int caraAmarrilla;
	public int caraAzul;
	public int caraBlanca;
	public int caraRoja;
	public int caraVerde;
	public int caraNaranja;
	
	public Queue<String> resultado = new LinkedList<>();
	
	
	
	public Cubo()
	{
		for (int i = 0; i < 9; i++)
		{
			cubo[F][i] = ROJO;
			cubo[R][i] = AZUL;
			cubo[B][i] = NARANJA;
			cubo[L][i] = VERDE;
			cubo[D][i] = AMARILLO;
			cubo[U][i] = BLANCO;
		}
		
		caraAmarrilla = D;
		caraAzul = R;
		caraBlanca = U;
		caraRoja = F ;
		caraVerde = L;
		caraNaranja = B;
		
		
	}

	private char[][] cubo = new char[6][9];

	private int getIndex(int x, int y, int cara)
	{
		switch (cara)
		{
		case F:
		case D:
		case R:
		case L:
			return x * 3 + y;

		case B:
			return x * 3 + 2 - y;

		case U:
			return (2 - x) * 3 + y;
		}
		return -1;
	}

	private void SetColor(char color, int x, int y, int cara)
	{
		switch (cara)
		{
		case F:
		case D:
		case R:
		case L:
			cubo[cara][this.getIndex(x, y, cara)] = color;
			break;

		case B:
			cubo[B][this.getIndex(x, y, cara)] = color;
			break;

		case U:
			cubo[cara][this.getIndex(x, y, cara)] = color;
			break;
		}
	}
	
	private void SetColor(char color, int index, int cara)
	{
		switch (cara)
		{
		case F:
		case D:
		case R:
		case L:
			cubo[cara][index] = color;
			break;

		case B:
			cubo[B][index] = color;
			break;

		case U:
			cubo[cara][index] = color;
			break;
		}
	}

	public void SetColorEnU(char color, int fil, int col)
	{
		SetColor(color, fil, col, U);
	}
	public void SetColorEnD(char color, int fil, int col)
	{
		SetColor(color, fil, col, D);
	}
	public void SetColorEnB(char color, int fil, int col)
	{
		SetColor(color, fil, col, B);
	}
	public void SetColorEnL(char color, int fil, int col)
	{
		SetColor(color, fil, col, L);
	}
	public void SetColorEnF(char color, int fil, int col)
	{
		SetColor(color, fil, col, F);
	}
	public void SetColorEnR(char color, int fil, int col)
	{
		SetColor(color, fil, col, R);
	}
	
	public char GetColor(int x, int y, int cara)
	{
		switch (cara)
		{
		case F:
		case D:
		case R:
		case L:
			return cubo[cara][getIndex(x, y, cara)];
		case B:
			return cubo[B][getIndex(x, y, cara)];
		case U:
			return cubo[cara][getIndex(x, y, cara)];
		}
		return 'E';
	}
	public char GetColor(int index, int cara)
	{
		switch (cara)
		{
		case F:
		case D:
		case R:
		case L:
			return cubo[cara][index];
		case B:
			return cubo[B][index];
		case U:
			return cubo[cara][index];
		}
		return 'E';
	}
	public char GetColorEnU(int fil, int col)
	{
		return this.GetColor(fil, col, U);
	}
	public char GetColorEnB(int fil, int col)
	{
		return this.GetColor(fil, col, B);
	}
	public char GetColorEnD(int fil, int col)
	{
		return this.GetColor(fil, col, D);
	}
	public char GetColorEnF(int fil, int col)
	{
		return this.GetColor(fil, col, F);
	}
	public char GetColorEnL(int fil, int col)
	{
		return this.GetColor(fil, col, L);
	}
	public char GetColorEnR(int fil, int col)
	{
		return this.GetColor(fil, col, R);
	}

	private void RotarCaraSentidoHorario(int c)
	{
		char temp = cubo[c][2];
		cubo[c][2] = cubo[c][0];
		cubo[c][0] = cubo[c][6];
		cubo[c][6] = cubo[c][8];
		cubo[c][8] = temp;

		temp = cubo[c][5];
		cubo[c][5] = cubo[c][1];
		cubo[c][1] = cubo[c][3];
		cubo[c][3] = cubo[c][7];
		cubo[c][7] = temp;
	}
	private void RotarCaraSentidoAntiHorario(int c)
	{
		char temp = cubo[c][2];
		cubo[c][2] = cubo[c][8];
		cubo[c][8] = cubo[c][6];
		cubo[c][6] = cubo[c][0];
		cubo[c][0] = temp;

		temp = cubo[c][1];
		cubo[c][1] = cubo[c][5];
		cubo[c][5] = cubo[c][7];
		cubo[c][7] = cubo[c][3];
		cubo[c][3] = temp;
	}

	public void Uhorario()
	{
		char temp;
		for (int i = 0; i < 3; i++)
		{
			temp = cubo[F][i];
			cubo[F][i] = cubo[R][i];
			cubo[R][i] = cubo[B][i];
			cubo[B][i] = cubo[L][i];
			cubo[L][i] = temp;
		}

		RotarCaraSentidoHorario(U);
		
		System.out.println(" U ");
		resultado.add("U");
	}
	public void UantiHorario()
	{
		char temp;
		for (int i = 0; i < 3; i++)
		{
			temp = cubo[F][i];
			cubo[F][i] = cubo[L][i];
			cubo[L][i] = cubo[B][i];
			cubo[B][i] = cubo[R][i];
			cubo[R][i] = temp;
		}
		RotarCaraSentidoAntiHorario(U);
		
		System.out.println(" Ua ");
		resultado.add("Ua");
	}

	public void DantiHorario()
	{
		char temp;
		for (int i = 6; i < 8; i++)
		{
			temp = cubo[F][i];
			cubo[F][i] = cubo[R][i];
			cubo[R][i] = cubo[B][i];
			cubo[B][i] = cubo[L][i];
			cubo[L][i] = temp;
		}
		
		RotarCaraSentidoHorario(D); /////esta bien esto?
		
		System.out.println(" Da ");
		resultado.add("Da");
	}
	/// <summary>
	/// Rotar cara inferior (Ej. Amarillo) sentido antihorario
	/// </summary>
	public void Dhorario()
	{
		char temp;
		for (int i = 6; i < 8; i++)
		{
			temp = cubo[F][i];
			cubo[F][i] = cubo[L][i];
			cubo[L][i] = cubo[B][i];
			cubo[B][i] = cubo[R][i];
			cubo[R][i] = temp;
		}
		RotarCaraSentidoHorario(D);
		
		System.out.println(" D ");
		resultado.add("D");
	}

	public void Rhorario()
	{
		char temp = cubo[F][2];
		cubo[F][2] = cubo[D][2];
		cubo[D][2] = cubo[B][6];
		cubo[B][6] = cubo[U][8];
		cubo[U][8] = temp;

		temp = cubo[F][5];
		cubo[F][5] = cubo[D][5];
		cubo[D][5] = cubo[B][3];
		cubo[B][3] = cubo[U][5];
		cubo[U][5] = temp;

		temp = cubo[F][8];
		cubo[F][8] = cubo[D][8];
		cubo[D][8] = cubo[B][0];
		cubo[B][0] = cubo[U][2];
		cubo[U][2] = temp;

		temp = cubo[R][2];
		cubo[R][2] = cubo[R][0];
		cubo[R][0] = cubo[R][6];
		cubo[R][6] = cubo[R][8];
		cubo[R][8] = temp;

		temp = cubo[R][5];
		cubo[R][5] = cubo[R][1];
		cubo[R][1] = cubo[R][3];
		cubo[R][3] = cubo[R][7];
		cubo[R][7] = temp;
		
		System.out.println(" R ");
		resultado.add("R");
	}
	public void RantiHorario()
	{
		char temp = cubo[F][2];
		cubo[F][2] = cubo[U][8];
		cubo[U][8] = cubo[B][6];
		cubo[B][6] = cubo[D][2];
		cubo[D][2] = temp;

		temp = cubo[F][5];
		cubo[F][5] = cubo[U][5];
		cubo[U][5] = cubo[B][3];
		cubo[B][3] = cubo[D][5];
		cubo[D][5] = temp;

		temp = cubo[F][8];
		cubo[F][8] = cubo[U][2];
		cubo[U][2] = cubo[B][0];
		cubo[B][0] = cubo[D][8];
		cubo[D][8] = temp;

		temp = cubo[R][2];
		cubo[R][2] = cubo[R][8];
		cubo[R][8] = cubo[R][6];
		cubo[R][6] = cubo[R][0];
		cubo[R][0] = temp;

		temp = cubo[R][1];
		cubo[R][1] = cubo[R][5];
		cubo[R][5] = cubo[R][7];
		cubo[R][7] = cubo[R][3];
		cubo[R][3] = temp;
		
		System.out.println("Ra");
		resultado.add(" Ra ");
	}

	/// <summary>
	/// Rotar cara lateral izq (Ej. Verde) sentido horario
	/// </summary>
	public void Lhorario()
	{
		char temp = cubo[F][0];
		cubo[F][0] = cubo[U][6];
		cubo[U][6] = cubo[B][8];
		cubo[B][8] = cubo[D][0];
		cubo[D][0] = temp;

		temp = cubo[F][3];
		cubo[F][3] = cubo[U][3];
		cubo[U][3] = cubo[B][5];
		cubo[B][5] = cubo[D][3];
		cubo[D][3] = temp;

		temp = cubo[F][6];
		cubo[F][6] = cubo[U][0];
		cubo[U][0] = cubo[B][2];
		cubo[B][2] = cubo[D][6];
		cubo[D][6] = temp;

		temp = cubo[L][2];
		cubo[L][2] = cubo[L][0];
		cubo[L][0] = cubo[L][6];
		cubo[L][6] = cubo[L][8];
		cubo[L][8] = temp;

		temp = cubo[L][5];
		cubo[L][5] = cubo[L][1];
		cubo[L][1] = cubo[L][3];
		cubo[L][3] = cubo[L][7];
		cubo[L][7] = temp;
		
		System.out.println(" L ");
		resultado.add("L");
	}

	public void LantiHorario()
	{
		char temp = cubo[F][0];
		cubo[F][0] = cubo[D][0];
		cubo[D][0] = cubo[B][8];
		cubo[B][8] = cubo[U][6];
		cubo[U][6] = temp;

		temp = cubo[F][3];
		cubo[F][3] = cubo[D][3];
		cubo[D][3] = cubo[B][5];
		cubo[B][5] = cubo[U][3];
		cubo[U][3] = temp;

		temp = cubo[F][6];
		cubo[F][6] = cubo[D][6];
		cubo[D][6] = cubo[B][2];
		cubo[B][2] = cubo[U][0];
		cubo[U][0] = temp;

		temp = cubo[L][2];
		cubo[L][2] = cubo[L][8];
		cubo[L][8] = cubo[L][6];
		cubo[L][6] = cubo[L][0];
		cubo[L][0] = temp;

		temp = cubo[L][1];
		cubo[L][1] = cubo[L][5];
		cubo[L][5] = cubo[L][7];
		cubo[L][7] = cubo[L][3];
		cubo[L][3] = temp;
		
		System.out.println(" La ");
		resultado.add(" La ");
	}

	public void Fhorario()
	{
		char temp = cubo[U][0];
		cubo[U][0] = cubo[L][8];
		cubo[L][8] = cubo[D][2];
		cubo[D][2] = cubo[R][0];
		cubo[R][0] = temp;

		temp = cubo[U][1];
		cubo[U][1] = cubo[L][5];
		cubo[L][5] = cubo[D][1];
		cubo[D][1] = cubo[R][3];
		cubo[R][3] = temp;

		temp = cubo[U][2];
		cubo[U][2] = cubo[L][2];
		cubo[L][2] = cubo[D][0];
		cubo[D][0] = cubo[R][6];
		cubo[R][6] = temp;
		/*
            temp = cubo[F][2];
            cubo[F][2] = cubo[F][0];
            cubo[F][0] = cubo[F][6];
            cubo[F][6] = cubo[F][8];
            cubo[F][8] = temp;

            temp = cubo[F][5];
            cubo[F][5] = cubo[F][1];
            cubo[F][1] = cubo[F][3];
            cubo[F][3] = cubo[F][7];
            cubo[F][7] = temp;*/

		RotarCaraSentidoAntiHorario(F);
		System.out.println(" F ");
		resultado.add("F");
	}

	public void FantiHorario()
	{
		char temp = cubo[U][0];
		cubo[U][0] = cubo[R][0];
		cubo[R][0] = cubo[D][2];
		cubo[D][2] = cubo[L][8];
		cubo[L][8] = temp;

		temp = cubo[U][1];
		cubo[U][1] = cubo[R][3];
		cubo[R][3] = cubo[D][1];
		cubo[D][1] = cubo[L][5];
		cubo[L][5] = temp;

		temp = cubo[U][2];
		cubo[U][2] = cubo[R][6];
		cubo[R][6] = cubo[D][0];
		cubo[D][0] = cubo[L][2];
		cubo[L][2] = temp;
		/*
            temp = cubo[F][2];
            cubo[F][2] = cubo[F][8];
            cubo[F][8] = cubo[F][6];
            cubo[F][6] = cubo[F][0];
            cubo[F][0] = temp;

            temp = cubo[F][1];
            cubo[F][1] = cubo[F][5];
            cubo[F][5] = cubo[F][7];
            cubo[F][7] = cubo[F][3];
            cubo[F][3] = temp;*/

		RotarCaraSentidoAntiHorario(F);
		System.out.println(" Fa ");
		resultado.add("Fa");
	}

	public void Bhorario()
	{
		char temp = cubo[U][8];
		cubo[U][8] = cubo[R][8];
		cubo[R][8] = cubo[D][6];
		cubo[D][6] = cubo[L][0];
		cubo[L][0] = temp;

		temp = cubo[U][7];
		cubo[U][7] = cubo[R][5];
		cubo[R][5] = cubo[D][7];
		cubo[D][7] = cubo[L][3];
		cubo[L][3] = temp;

		temp = cubo[U][6];
		cubo[U][6] = cubo[R][2];
		cubo[R][2] = cubo[D][8];
		cubo[D][8] = cubo[L][6];
		cubo[L][6] = temp;

		RotarCaraSentidoHorario(B);
		
		System.out.println(" B ");
		resultado.add("B");
	}
	public void BantiHorario()
	{
		char temp = cubo[U][6];
		cubo[U][6] = cubo[L][6];
		cubo[L][6] = cubo[D][8];
		cubo[D][8] = cubo[R][2];
		cubo[R][2] = temp;

		temp = cubo[U][7];
		cubo[U][7] = cubo[L][3];
		cubo[L][3] = cubo[D][7];
		cubo[D][7] = cubo[R][5];
		cubo[R][5] = temp;

		temp = cubo[U][8];
		cubo[U][8] = cubo[L][0];
		cubo[L][0] = cubo[D][6];
		cubo[D][6] = cubo[R][8];
		cubo[R][8] = temp;

		RotarCaraSentidoAntiHorario(B);
		
		System.out.println(" Ba ");
		resultado.add("Ba");
	}
	
	//le decis un color y te dice q cara tiene ese color
	public int GetCara(char color){
		switch(color){
			case 'Y': return this.caraAmarrilla;
			case 'B': return this.caraAzul;
			case 'W': return this.caraBlanca;
			case 'R': return this.caraRoja;
			case 'V': return this.caraVerde;
			case 'N': return this.caraNaranja;
		}
		
		return -1;
	}
	
	// le decis el numero de cara y la gira horario
	public void GirarCaraHorario( int cara ){
		switch(cara){
			case 0: Fhorario();
					return;
			case 1:	Rhorario();
					return;
			case 2: Bhorario();
					return;
			case 3: Lhorario();
					return;
			case 4: Uhorario();
					return;
			case 5: Dhorario();
					return;
		}
	}
	
	// le decis el numero de la cara y la gira antihorario
	public void GirarCaraAntihorario( int cara){
		switch(cara){
		case 0: FantiHorario();
				return;
		case 1:	RantiHorario();
				return;
		case 2: BantiHorario();
				return;
		case 3: LantiHorario();
				return;
		case 4: UantiHorario();
				return;
		case 5: DantiHorario();
				return;
		}
	}
	
	public void GirarCara(int cara, boolean horario){
		if(horario == true){
			GirarCaraHorario(cara);
		}
		else{
			GirarCaraAntihorario(cara);
		}
	}
	
	
	
	public void HacerCruz(){
	int i;
	// int giros;
	int posActual[] = new int[2];
	int caraDeAlLado;
	int cantPiezasPuestas=0;
	int distancia;
	int caraDestino;
	int caraActualArista;
	//	if( ( posActual = HayBlancosEnD() )!= -1){
			//estaEnSuLugar( posActual, );
	//	}
	
		while(cantPiezasPuestas < 4){
			posActual = HayBlancosEnElMedio();
			if(  posActual[0] == -1){
				posActual = HayBlancosArriba();
			}
			if(  posActual[0] != -1){

				//gira la capa de abajo para q la blanca quede a un giro de estar bien puesta

				
				// determina donde esta la otra parte de la arista 
				if(posActual[0] == 4) //el blanco esta en la capa de arriba
				{
					switch(posActual[1]){
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
					caraDestino = GetCara( GetColor(1, caraActualArista)); //determina la cara donde tiene q ir
				}
				else{ // esta en la cara del medio el blanco
					if( posActual[0] == 5 ){
						caraActualArista = posActual[1] + 1;
						if(caraActualArista == 4)
							caraActualArista = 0;
						caraDestino = GetCara( GetColor(3, caraActualArista)); //determina la cara donde tiene q ir
					}
					else{
						caraActualArista= posActual[1] - 1;
						if(caraActualArista == -1)
							caraActualArista = 3;
						caraDestino = GetCara( GetColor(5, caraActualArista)); //determina la cara donde tiene q ir
					}		
				}
				
				distancia = caraDestino - caraActualArista;            
				
				// hace q 3 giros para un lado los cambie por 1 giro para el otro
				if(distancia == 3){
					distancia = -1;
				}
				if(distancia == -3){
					distancia = 1;
				}
					
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
				
				// Hace el giro para q quede bien puesta
				
				if(posActual[0] == 4){
					GirarCaraHorario(caraActualArista);
					GirarCaraHorario(caraActualArista);
				}
				else if( posActual[1] == 3){
					GirarCaraHorario(caraActualArista);
				}
				else{
					GirarCaraAntihorario(caraActualArista);
				}
				
				//rehace los giros de abajo (para el otro lado) para q quede todo bien
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
			else {
				
				
			}
			
		}

	}
	
	private int calcularCantidadDeD(int[] posActual) {
		int distancia;
		int caraDestino;
		int caraActualArista;
		
		
		// determina donde esta la otra parte de la arista 
		if(posActual[0] == 4) //el blanco esta en la capa de arriba
		{
			switch(posActual[1]){
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
			caraDestino = GetCara( GetColor(1, caraActualArista)); //determina la cara donde tiene q ir
		}
		else{ // esta en la cara del medio el blanco
			if( posActual[0] == 5 ){
				caraActualArista = posActual[1] + 1;
				if(caraActualArista == 4)
					caraActualArista = 0;
				caraDestino = GetCara( GetColor(3, caraActualArista)); //determina la cara donde tiene q ir
			}
			else{
				caraActualArista= posActual[1] - 1;
				if(caraActualArista == -1)
					caraActualArista = 3;
				caraDestino = GetCara( GetColor(5, caraActualArista)); //determina la cara donde tiene q ir
			}		
		}
		
		distancia = caraDestino - caraActualArista;            
		
		// hace q 3 giros para un lado los cambie por 1 giro para el otro
		if(distancia == 3){
			distancia = -1;
		}
		if(distancia == -3){
			distancia = 1;
		}
		
		return distancia;
	}

	public int HayBlancosEnD(){
		
		return -1;
	}
	
	public int[] HayBlancosEnElMedio(){
		int i;
		int posActual[] = new int[2];
		char ColorAbajo = GetColorEnD(1, 1); 
		for(i=0; i<4; i++){
			if( GetColor(1, 2,i) == ColorAbajo){				
				posActual[0] = i;
				posActual[1] = 5;
				return posActual;
			}
		}
		for(i=0; i<4; i++){
			if( GetColor(1, 0,i) == ColorAbajo){
				posActual[0] = i;
				posActual[1] = 3;
				return posActual;
			}
		}
		posActual[0] = -1;
		return posActual;
	}
	
	public int[] HayBlancosArriba(){
		int i;
		int posActual[] = new int[2];
		char ColorAbajo = GetColorEnD(1, 1); 
		
		for(i=1; i <= 7; i+=2){ //mira las posiciones 1 3 5 y 7 (las aristas)
		
			if( GetColor(i, 4) == ColorAbajo){				
				posActual[0] = 4;
				posActual[1] = i;
				return posActual;
			}
		}

		posActual[0] = -1;
		return posActual;
	}
	

}

