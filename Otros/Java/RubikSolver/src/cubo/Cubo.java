package cubo;

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
	private char GetColor(int x, int y, int cara)
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
		for (int i = 6; i < 8; i++)
		{
			temp = cubo[F][i];
			cubo[F][i] = cubo[R][i];
			cubo[R][i] = cubo[B][i];
			cubo[B][i] = cubo[L][i];
			cubo[L][i] = temp;
		}
		RotarCaraSentidoHorario(D);
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
	}
}

