package solver;

public class Cubo {

	public final int PIEZAS_POR_CARA = 9;
	public final int CANT_CARAS = 6;

	private char[][] cubo = new char[CANT_CARAS][PIEZAS_POR_CARA];

	public final int F = 1;
	public final int R = 2;
	public final int B = 3;
	public final int L = 4;
	public final int D = 5;
	public final int U = 0;

	public final int CARA_AMARILLA = D;
	public final int CARA_AZUL = R;
	public final int CARA_BLANCA = U;
	public final int CARA_ROJA = F;
	public final int CARA_VERDE = L;
	public final int CARA_NARANJA = B;

	public final char AMARILLO ='Y';
	public final char ROJO ='R';
	public final char VERDE ='G';
	public final char AZUL ='B';
	public final char NARANJA ='O';
	public final char BLANCO ='W';

	public final String U_HORARIO = "U";
	public final String U_ANTI_HORARIO = "Ua";
	public final String L_HORARIO = "L";
	public final String L_ANTI_HORARIO = "La";
	public final String R_HORARIO = "R";
	public final String R_ANTI_HORARIO = "Ra";
	public final String F_HORARIO = "F";
	public final String F_ANTI_HORARIO  = "Fa";
	public final String B_HORARIO = "B";
	public final String B_ANTI_HORARIO  = "Ba";
	public final String D_HORARIO = "D";
	public final String D_ANTI_HORARIO  = "Da";
	
	public void SetColor(char color, int pieza, int cara) {
		cubo[cara][pieza] = color;
	}

	public char GetColor(int pieza, int cara) {
		return cubo[cara][pieza];
	}

	public int GetCara(char color) {
		switch(color) 
		{
			case AMARILLO: return this.CARA_AMARILLA;
			case ROJO: return this.CARA_ROJA;
			case VERDE: return this.CARA_VERDE;
			case AZUL: return this.CARA_AZUL;
			case NARANJA: return this.CARA_NARANJA;
			case BLANCO: return this.CARA_AMARILLA;
		}
		return -1;
	}

	public void RotarCaraSentidoHorario(int c) {
		char temp = cubo[c][2];
		cubo[c][2]= cubo[c][0];
		cubo[c][0]= cubo[c][6];
		cubo[c][6]= cubo[c][8];
		cubo[c][8]= temp;

		temp = cubo[c][5];
		cubo[c][5] = cubo[c][1];
		cubo[c][1] = cubo[c][3];
		cubo[c][3] = cubo[c][7];
		cubo[c][7] = temp;
	}
	
	public void RotarCaraSentidoAntiHorario(int c)
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

	public void GirarHorario(int cara) {
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

	public void GirarAntiHorario(int cara) {
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
	
	public void Uhorario() {
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

	}

	public void DantiHorario()
	{
		char temp;
		for (int i = 6; i < 9; i++)
		{
			temp = cubo[F][i];
			cubo[F][i] = cubo[R][i];
			cubo[R][i] = cubo[B][i];
			cubo[B][i] = cubo[L][i];
			cubo[L][i] = temp;
		}

		RotarCaraSentidoAntiHorario(D); 

	}
	
	public void Dhorario()
	{
		char temp;
		for (int i = 6; i < 9; i++)
		{
			temp = cubo[F][i];
			cubo[F][i] = cubo[L][i];
			cubo[L][i] = cubo[B][i];
			cubo[B][i] = cubo[R][i];
			cubo[R][i] = temp;
		}
		RotarCaraSentidoHorario(D);

	}

	public void Rhorario()
	{
		char temp = cubo[F][2];
		cubo[F][2] = cubo[D][2];
		cubo[D][2] = cubo[B][6];
		cubo[B][6] = cubo[U][2];
		cubo[U][2] = temp;

		temp = cubo[F][5];
		cubo[F][5] = cubo[D][5];
		cubo[D][5] = cubo[B][3];
		cubo[B][3] = cubo[U][5];
		cubo[U][5] = temp;

		temp = cubo[F][8];
		cubo[F][8] = cubo[D][8];
		cubo[D][8] = cubo[B][0];
		cubo[B][0] = cubo[U][8];
		cubo[U][8] = temp;

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

	}

	public void RantiHorario()
	{
		char temp = cubo[F][2];
		cubo[F][2] = cubo[U][2];
		cubo[U][2] = cubo[B][6];
		cubo[B][6] = cubo[D][2];
		cubo[D][2] = temp;

		temp = cubo[F][5];
		cubo[F][5] = cubo[U][5];
		cubo[U][5] = cubo[B][3];
		cubo[B][3] = cubo[D][5];
		cubo[D][5] = temp;

		temp = cubo[F][8];
		cubo[F][8] = cubo[U][8];
		cubo[U][8] = cubo[B][0];
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

	}

	public void Lhorario()
	{
		char temp = cubo[F][0];
		cubo[F][0] = cubo[U][0];
		cubo[U][0] = cubo[B][8];
		cubo[B][8] = cubo[D][0];
		cubo[D][0] = temp;

		temp = cubo[F][3];
		cubo[F][3] = cubo[U][3];
		cubo[U][3] = cubo[B][5];
		cubo[B][5] = cubo[D][3];
		cubo[D][3] = temp;

		temp = cubo[F][6];
		cubo[F][6] = cubo[U][6];
		cubo[U][6] = cubo[B][2];
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

	}

	public void LantiHorario()
	{
		char temp = cubo[F][0];
		cubo[F][0] = cubo[D][0];
		cubo[D][0] = cubo[B][8];
		cubo[B][8] = cubo[U][0];
		cubo[U][0] = temp;

		temp = cubo[F][3];
		cubo[F][3] = cubo[D][3];
		cubo[D][3] = cubo[B][5];
		cubo[B][5] = cubo[U][3];
		cubo[U][3] = temp;

		temp = cubo[F][6];
		cubo[F][6] = cubo[D][6];
		cubo[D][6] = cubo[B][2];
		cubo[B][2] = cubo[U][6];
		cubo[U][6] = temp;

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

	}

	public void Fhorario()
	{
		char temp = cubo[U][6];
		cubo[U][6] = cubo[L][8];
		cubo[L][8] = cubo[D][2];
		cubo[D][2] = cubo[R][0];
		cubo[R][0] = temp;

		temp = cubo[U][7];
		cubo[U][7] = cubo[L][5];
		cubo[L][5] = cubo[D][1];
		cubo[D][1] = cubo[R][3];
		cubo[R][3] = temp;

		temp = cubo[U][8];
		cubo[U][8] = cubo[L][2];
		cubo[L][2] = cubo[D][0];
		cubo[D][0] = cubo[R][6];
		cubo[R][6] = temp;
		RotarCaraSentidoHorario(F);
	}

	public void FantiHorario()
	{
		char temp = cubo[U][6];
		cubo[U][6] = cubo[R][0];
		cubo[R][0] = cubo[D][2];
		cubo[D][2] = cubo[L][8];
		cubo[L][8] = temp;

		temp = cubo[U][7];
		cubo[U][1] = cubo[R][3];
		cubo[R][3] = cubo[D][1];
		cubo[D][1] = cubo[L][5];
		cubo[L][5] = temp;

		temp = cubo[U][8];
		cubo[U][8] = cubo[R][6];
		cubo[R][6] = cubo[D][0];
		cubo[D][0] = cubo[L][2];
		cubo[L][2] = temp;

		RotarCaraSentidoAntiHorario(F);
	}

	public void Bhorario()
	{
		char temp = cubo[U][2];
		cubo[U][2] = cubo[R][8];
		cubo[R][8] = cubo[D][6];
		cubo[D][6] = cubo[L][0];
		cubo[L][0] = temp;

		temp = cubo[U][1];
		cubo[U][1] = cubo[R][5];
		cubo[R][5] = cubo[D][7];
		cubo[D][7] = cubo[L][3];
		cubo[L][3] = temp;

		temp = cubo[U][0];
		cubo[U][0] = cubo[R][2];
		cubo[R][2] = cubo[D][8];
		cubo[D][8] = cubo[L][6];
		cubo[L][6] = temp;

		RotarCaraSentidoHorario(B);

	}
	
	public void BantiHorario()
	{
		char temp = cubo[U][0];
		cubo[U][0] = cubo[L][6];
		cubo[L][6] = cubo[D][8];
		cubo[D][8] = cubo[R][2];
		cubo[R][2] = temp;

		temp = cubo[U][1];
		cubo[U][1] = cubo[L][3];
		cubo[L][3] = cubo[D][7];
		cubo[D][7] = cubo[R][5];
		cubo[R][5] = temp;

		temp = cubo[U][2];
		cubo[U][2] = cubo[L][0];
		cubo[L][0] = cubo[D][6];
		cubo[D][6] = cubo[R][8];
		cubo[R][8] = temp;

		RotarCaraSentidoAntiHorario(B);

	}
}
