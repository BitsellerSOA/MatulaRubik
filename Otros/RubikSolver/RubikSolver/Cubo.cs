using System;
using System.Collections.Generic;
using System.Text;

namespace RubikSolver
{
    class Cubo
    {
        /// <summary>
        /// Valor que representa al color amarillo
        /// </summary>
        public const char AMARILLO = 'Y';
        /// <summary>
        /// Valor que representa al color azul
        /// </summary>
        public const char AZUL = 'B';
        /// <summary>
        /// Valor que representa al color blanco
        /// </summary>
        public const char BLANCO = 'W';
        /// <summary>
        /// Valor que representa al color rojo
        /// </summary>
        public const char ROJO = 'R';
        /// <summary>
        /// Valor que representa al color verde
        /// </summary>
        public const char VERDE = 'V';
        /// <summary>
        /// Valor que representa al color negro
        /// </summary>
        public const char NARANJA = 'N';
        
        /// <summary>
        /// Valor que representa el indice en la matriz para la cara Frontal. Ej. Rojo
        /// </summary>
        public const int F = 0;
        /// <summary>
        /// Valor que representa el indice en la matriz para la cara lateral derecha. Ej. Azul
        /// </summary>
        public const int R = 1;
        /// <summary>
        /// Valor que representa el indice en la matriz para la cara trasera. Ej. Naranja
        /// </summary>
        public const int B = 2;
        /// <summary>
        /// Valor que representa el indice en la matriz para la cara lateral Izquierda. Ej. Verde
        /// </summary>
        public const int L = 3;
        /// <summary>
        /// Valor que representa el indice en la matriz para la cara superior. Ej. Blanco
        /// </summary>
        public const int U = 4;
        /// <summary>
        /// Valor que representa el indice en la matriz para la cara de abajo. Ej. Amarillo
        /// </summary>
        public const int D = 5;

        /// <summary>
        /// Este constructor es solo para pruebas. Aca se une lo de opencv
        /// </summary>
        public Cubo()
        {
            for (int i = 0; i < 9; i++)
            {
                cubo[F, i] = ROJO;
                cubo[R, i] = AZUL;
                cubo[B, i] = NARANJA;
                cubo[L, i] = VERDE;
                cubo[D, i] = AMARILLO;
                cubo[U, i] = BLANCO;
            }
        }

        /// <summary>
        /// Representamos al cubo como un arreglo bidemencional, Cara-Posisiones
        /// </summary>
        private char[,] cubo = new char[6, 9];

        /// <summary>
        /// Metodo interno, para encapsular el acceso a las caras. Se considera una vista de frente de la cara
        /// </summary>
        /// <param name="x">fila 0..2 de izq a derecha</param>
        /// <param name="y">columna 0..2 de abajo a arriba</param>
        /// <param name="cara"></param>
        /// <returns></returns>
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

        /// <summary>
        /// Metodo interno, para indicar el color de una arista del cubo. Se considera una vista de frente de la cara.
        /// </summary>
        /// <param name="color">Usar las constantes de la clase</param>
        /// <param name="x">fila 0..2 de izq a derecha</param>
        /// <param name="y">columna 0..2 de abajo a arriba</param>
        /// <param name="cara">Usar las constantes de la clase</param>
        private void SetColor(char color, int x, int y, int cara)
        {
            switch (cara)
            {
                case F:
                case D:
                case R:
                case L:
                    cubo[cara, this.getIndex(x, y, cara)] = color;
                    break;

                case B:
                    cubo[B, this.getIndex(x, y, cara)] = color;
                    break;

                case U:
                    cubo[cara, this.getIndex(x, y, cara)] = color;
                    break;
            }
        }

        /// <summary>
        /// Permite Indicar el color de una arista de la cara superior. Se considera una vista de frente de la cara.
        /// </summary>
        /// <param name="color">Usar las constantes de la clase</param>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        public void SetColorEnU(char color, int fil, int col)
        {
            SetColor(color, fil, col, U);
        }
        /// <summary>
        /// Permite Indicar el color de una arista de la cara inferior. Se considera una vista de frente de la cara.
        /// </summary>
        /// <param name="color">Usar las constantes de la clase</param>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        public void SetColorEnD(char color, int fil, int col)
        {
            SetColor(color, fil, col, D);
        }
        /// <summary>
        /// Permite Indicar el color de una arista de la cara trasera. Se considera una vista de frente de la cara.
        /// </summary>
        /// <param name="color">Usar las constantes de la clase</param>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        public void SetColorEnB(char color, int fil, int col)
        {
            SetColor(color, fil, col, B);
        }
        /// <summary>
        /// Permite Indicar el color de una arista de la cara lateral izq. Se considera una vista de frente de la cara.
        /// </summary>
        /// <param name="color">Usar las constantes de la clase</param>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        public void SetColorEnL(char color, int fil, int col)
        {
            SetColor(color, fil, col, L);
        }
        /// <summary>
        /// Permite Indicar el color de una arista de la cara frontal. Se considera una vista de frente de la cara.
        /// </summary>
        /// <param name="color">Usar las constantes de la clase</param>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        public void SetColorEnF(char color, int fil, int col)
        {
            SetColor(color, fil, col, F);
        }
        /// <summary>
        /// Permite Indicar el color de una arista de la cara lateral derecha. Se considera una vista de frente de la cara.
        /// </summary>
        /// <param name="color">Usar las constantes de la clase</param>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        public void SetColorEnR(char color, int fil, int col)
        {
            SetColor(color, fil, col, R);
        }

        /// <summary>
        /// Metodo iterno que permite obtener el color de una arista del cubo. Se considera una vista de frente de la cara.
        /// </summary>
        /// <param name="x">fila 0..2 de izq a derecha</param>
        /// <param name="y">columna 0..2 de abajo a arriba</param>
        /// <param name="cara">Usar constantes clase</param>
        /// <returns>El color representados con las constantes de la clase</returns>
        private char GetColor(int x, int y, int cara)
        {
            switch (cara)
            {
                case F:
                case D:
                case R:
                case L:
                    return cubo[cara, getIndex(x, y, cara)];
                case B:
                    return cubo[B, getIndex(x, y, cara)];
                case U:
                    return cubo[cara, getIndex(x, y, cara)];
            }
            return 'E';
        }
        /// <summary>
        /// Devuelve el color de una arista de la cara superior del cubo. Se considera una vista de frente de la cara. Ej. 1,1 => W -> Blanco
        /// </summary>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        /// <returns>El color representados con las constantes de la clase</returns>
        public char GetColorEnU(int fil, int col)
        {
            return this.GetColor(fil, col, Cubo.U);
        }
        /// <summary>
        /// Devuelve el color de una arista de la cara trasera del cubo. Se considera una vista de frente de la cara. Ej. 1,1 => N -> Naranja 
        /// </summary>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        /// <returns>El color representados con las constantes de la clase</returns>
        public char GetColorEnB(int fil, int col)
        {
            return this.GetColor(fil, col, Cubo.B);
        }
        /// <summary>
        /// Devuelve el color de una arista de la cara inferior del cubo. Se considera una vista de frente de la cara. Ej. 1,1 => Y -> Amarillo
        /// </summary>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        /// <returns>El color representados con las constantes de la clase</returns>
        public char GetColorEnD(int fil, int col)
        {
            return this.GetColor(fil, col, Cubo.D);
        }
        /// <summary>
        /// Devuelve el color de una arista de la cara frontal del cubo. Se considera una vista de frente de la cara. Ej. 1,1 => R -> Rojo
        /// </summary>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        /// <returns>El color representados con las constantes de la clase</returns>
        public char GetColorEnF(int fil, int col)
        {
            return this.GetColor(fil, col, Cubo.F);
        }
        /// <summary>
        /// Devuelve el color de una arista de la cara lateral izq del cubo. Se considera una vista de frente de la cara. Ej. 1,1 => V -> Verde
        /// </summary>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        /// <returns>El color representados con las constantes de la clase</returns>
        public char GetColorEnL(int fil, int col)
        {
            return this.GetColor(fil, col, Cubo.L);
        }
        /// <summary>
        /// Devuelve el color de una arista de la cara lateral derecha del cubo. Se considera una vista de frente de la cara. Ej. 1,1 => B -> Azul
        /// </summary>
        /// <param name="fil">fila 0..2 de izq a derecha</param>
        /// <param name="col">columna 0..2 de abajo a arriba</param>
        /// <returns>El color representados con las constantes de la clase</returns>
        public char GetColorEnR(int fil, int col)
        {
            return this.GetColor(fil, col, Cubo.R);
        }

        #region Movimientos
        private void RotarCaraSentidoHorario(int c)
        {
            char temp = cubo[c, 2];
            cubo[c, 2] = cubo[c, 0];
            cubo[c, 0] = cubo[c, 6];
            cubo[c, 6] = cubo[c, 8];
            cubo[c, 8] = temp;

            temp = cubo[c, 5];
            cubo[c, 5] = cubo[c, 1];
            cubo[c, 1] = cubo[c, 3];
            cubo[c, 3] = cubo[c, 7];
            cubo[c, 7] = temp;
        }
        private void RotarCaraSentidoAntiHorario(int c)
        {
            char temp = cubo[c, 2];
            cubo[c, 2] = cubo[c, 8];
            cubo[c, 8] = cubo[c, 6];
            cubo[c, 6] = cubo[c, 0];
            cubo[c, 0] = temp;

            temp = cubo[c, 1];
            cubo[c, 1] = cubo[c, 5];
            cubo[c, 5] = cubo[c, 7];
            cubo[c, 7] = cubo[c, 3];
            cubo[c, 3] = temp;
        }

        /// <summary>
        /// Rotar cara superior (Ej. Blanco) sentido horario
        /// </summary>
        public void Uhorario()
        {
            char temp;
            for (int i = 0; i < 3; i++)
            {
                temp = cubo[F, i];
                cubo[F, i] = cubo[R, i];
                cubo[R, i] = cubo[B, i];
                cubo[B, i] = cubo[L, i];
                cubo[L, i] = temp;
            }
            /*
            temp = cubo[U, 0];
            cubo[U, 0] = cubo[U, 2];
            cubo[U, 2] = cubo[U, 8];
            cubo[U, 8] = cubo[U, 6];
            cubo[U, 6] = temp;

            temp = cubo[U, 1];
            cubo[U, 1] = cubo[U, 5];
            cubo[U, 5] = cubo[U, 7];
            cubo[U, 7] = cubo[U, 3];
            cubo[U, 3] = temp;*/

            RotarCaraSentidoHorario(U);
        }
        /// <summary>
        /// Rotar cara superior (Ej. Blanco) sentido antihorario
        /// </summary>
        public void UantiHorario()
        {
            char temp;
            for (int i = 0; i < 3; i++)
            {
                temp = cubo[F, i];
                cubo[F, i] = cubo[L, i];
                cubo[L, i] = cubo[B, i];
                cubo[B, i] = cubo[R, i];
                cubo[R, i] = temp;
            }
            /* temp = cubo[U, 2];
             cubo[U, 2] = cubo[U, 0];
             cubo[U, 0] = cubo[F, 6];
             cubo[U, 6] = cubo[F, 8];
             cubo[U, 8] = temp;


             temp = cubo[U, 5];
             cubo[U, 5] = cubo[U, 1];
             cubo[U, 1] = cubo[U, 3];
             cubo[U, 3] = cubo[U, 7];
             cubo[U, 7] = temp;
             */
            RotarCaraSentidoAntiHorario(U);
        }

        /// <summary>
        /// Rotar cara inferior (Ej. Amarillo) sentido horario
        /// </summary>
        public void DantiHorario()
        {
            char temp;
            for (int i = 6; i < 8; i++)
            {
                temp = cubo[F, i];
                cubo[F, i] = cubo[R, i];
                cubo[R, i] = cubo[B, i];
                cubo[B, i] = cubo[L, i];
                cubo[L, i] = temp;
            }
            /*temp = cubo[D, 0];
            cubo[D, 0] = cubo[D, 2];
            cubo[D, 2] = cubo[D, 8];
            cubo[D, 8] = cubo[D, 6];
            cubo[D, 6] = temp;


            temp = cubo[D, 1];
            cubo[D, 1] = cubo[D, 5];
            cubo[D, 5] = cubo[D, 7];
            cubo[D, 7] = cubo[D, 3];
            cubo[D, 3] = temp;*/

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
                temp = cubo[F, i];
                cubo[F, i] = cubo[L, i];
                cubo[L, i] = cubo[B, i];
                cubo[B, i] = cubo[R, i];
                cubo[R, i] = temp;
            }
            /*  temp = cubo[D, 2];
              cubo[D, 2] = cubo[D, 0];
              cubo[D, 0] = cubo[D, 6];
              cubo[D, 6] = cubo[D, 8];
              cubo[D, 8] = temp;


              temp = cubo[D, 5];
              cubo[D, 5] = cubo[D, 1];
              cubo[D, 1] = cubo[D, 3];
              cubo[D, 3] = cubo[D, 7];
              cubo[D, 7] = temp;
              */

            RotarCaraSentidoHorario(D);
        }

        /// <summary>
        /// Rotar cara lateral derecha (Ej. Azul) sentido horario
        /// </summary>
        public void Rhorario()
        {
            char temp = cubo[F, 2];
            cubo[F, 2] = cubo[D, 2];
            cubo[D, 2] = cubo[B, 6];
            cubo[B, 6] = cubo[U, 8];
            cubo[U, 8] = temp;

            temp = cubo[F, 5];
            cubo[F, 5] = cubo[D, 5];
            cubo[D, 5] = cubo[B, 3];
            cubo[B, 3] = cubo[U, 5];
            cubo[U, 5] = temp;

            temp = cubo[F, 8];
            cubo[F, 8] = cubo[D, 8];
            cubo[D, 8] = cubo[B, 0];
            cubo[B, 0] = cubo[U, 2];
            cubo[U, 2] = temp;

            temp = cubo[R, 2];
            cubo[R, 2] = cubo[R, 0];
            cubo[R, 0] = cubo[R, 6];
            cubo[R, 6] = cubo[R, 8];
            cubo[R, 8] = temp;

            temp = cubo[R, 5];
            cubo[R, 5] = cubo[R, 1];
            cubo[R, 1] = cubo[R, 3];
            cubo[R, 3] = cubo[R, 7];
            cubo[R, 7] = temp;
        }
        /// <summary>
        /// Rotar cara lateral derecha (Ej. Azul) sentido antihorario
        /// </summary>
        public void RantiHorario()
        {
            char temp = cubo[F, 2];
            cubo[F, 2] = cubo[U, 8];
            cubo[U, 8] = cubo[B, 6];
            cubo[B, 6] = cubo[D, 2];
            cubo[D, 2] = temp;

            temp = cubo[F, 5];
            cubo[F, 5] = cubo[U, 5];
            cubo[U, 5] = cubo[B, 3];
            cubo[B, 3] = cubo[D, 5];
            cubo[D, 5] = temp;

            temp = cubo[F, 8];
            cubo[F, 8] = cubo[U, 2];
            cubo[U, 2] = cubo[B, 0];
            cubo[B, 0] = cubo[D, 8];
            cubo[D, 8] = temp;

            temp = cubo[R, 2];
            cubo[R, 2] = cubo[R, 8];
            cubo[R, 8] = cubo[R, 6];
            cubo[R, 6] = cubo[R, 0];
            cubo[R, 0] = temp;

            temp = cubo[R, 1];
            cubo[R, 1] = cubo[R, 5];
            cubo[R, 5] = cubo[R, 7];
            cubo[R, 7] = cubo[R, 3];
            cubo[R, 3] = temp;
        }

        /// <summary>
        /// Rotar cara lateral izq (Ej. Verde) sentido horario
        /// </summary>
        public void Lhorario()
        {
            char temp = cubo[F, 0];
            cubo[F, 0] = cubo[U, 6];
            cubo[U, 6] = cubo[B, 8];
            cubo[B, 8] = cubo[D, 0];
            cubo[D, 0] = temp;

            temp = cubo[F, 3];
            cubo[F, 3] = cubo[U, 3];
            cubo[U, 3] = cubo[B, 5];
            cubo[B, 5] = cubo[D, 3];
            cubo[D, 3] = temp;

            temp = cubo[F, 6];
            cubo[F, 6] = cubo[U, 0];
            cubo[U, 0] = cubo[B, 2];
            cubo[B, 2] = cubo[D, 6];
            cubo[D, 6] = temp;

            temp = cubo[L, 2];
            cubo[L, 2] = cubo[L, 0];
            cubo[L, 0] = cubo[L, 6];
            cubo[L, 6] = cubo[L, 8];
            cubo[L, 8] = temp;

            temp = cubo[L, 5];
            cubo[L, 5] = cubo[L, 1];
            cubo[L, 1] = cubo[L, 3];
            cubo[L, 3] = cubo[L, 7];
            cubo[L, 7] = temp;
        }
        /// <summary>
        /// Rotar cara lateral izq (Ej. Verde) sentido antihorario
        /// </summary>
        public void LantiHorario()
        {
            char temp = cubo[F, 0];
            cubo[F, 0] = cubo[D, 0];
            cubo[D, 0] = cubo[B, 8];
            cubo[B, 8] = cubo[U, 6];
            cubo[U, 6] = temp;

            temp = cubo[F, 3];
            cubo[F, 3] = cubo[D, 3];
            cubo[D, 3] = cubo[B, 5];
            cubo[B, 5] = cubo[U, 3];
            cubo[U, 3] = temp;

            temp = cubo[F, 6];
            cubo[F, 6] = cubo[D, 6];
            cubo[D, 6] = cubo[B, 2];
            cubo[B, 2] = cubo[U, 0];
            cubo[U, 0] = temp;

            temp = cubo[L, 2];
            cubo[L, 2] = cubo[L, 8];
            cubo[L, 8] = cubo[L, 6];
            cubo[L, 6] = cubo[L, 0];
            cubo[L, 0] = temp;

            temp = cubo[L, 1];
            cubo[L, 1] = cubo[L, 5];
            cubo[L, 5] = cubo[L, 7];
            cubo[L, 7] = cubo[L, 3];
            cubo[L, 3] = temp;
        }

        /// <summary>
        /// Rotar cara frontal (Ej. Roja) sentido horario
        /// </summary>
        public void Fhorario()
        {
            char temp = cubo[U, 0];
            cubo[U, 0] = cubo[L, 8];
            cubo[L, 8] = cubo[D, 2];
            cubo[D, 2] = cubo[R, 0];
            cubo[R, 0] = temp;

            temp = cubo[U, 1];
            cubo[U, 1] = cubo[L, 5];
            cubo[L, 5] = cubo[D, 1];
            cubo[D, 1] = cubo[R, 3];
            cubo[R, 3] = temp;

            temp = cubo[U, 2];
            cubo[U, 2] = cubo[L, 2];
            cubo[L, 2] = cubo[D, 0];
            cubo[D, 0] = cubo[R, 6];
            cubo[R, 6] = temp;
            /*
            temp = cubo[F, 2];
            cubo[F, 2] = cubo[F, 0];
            cubo[F, 0] = cubo[F, 6];
            cubo[F, 6] = cubo[F, 8];
            cubo[F, 8] = temp;

            temp = cubo[F, 5];
            cubo[F, 5] = cubo[F, 1];
            cubo[F, 1] = cubo[F, 3];
            cubo[F, 3] = cubo[F, 7];
            cubo[F, 7] = temp;*/

            RotarCaraSentidoAntiHorario(F);
        }
        /// <summary>
        /// Rotar cara frontal (Ej. Roja) sentido antihorario
        /// </summary>
        public void FantiHorario()
        {
            char temp = cubo[U, 0];
            cubo[U, 0] = cubo[R, 0];
            cubo[R, 0] = cubo[D, 2];
            cubo[D, 2] = cubo[L, 8];
            cubo[L, 8] = temp;

            temp = cubo[U, 1];
            cubo[U, 1] = cubo[R, 3];
            cubo[R, 3] = cubo[D, 1];
            cubo[D, 1] = cubo[L, 5];
            cubo[L, 5] = temp;

            temp = cubo[U, 2];
            cubo[U, 2] = cubo[R, 6];
            cubo[R, 6] = cubo[D, 0];
            cubo[D, 0] = cubo[L, 2];
            cubo[L, 2] = temp;
            /*
            temp = cubo[F, 2];
            cubo[F, 2] = cubo[F, 8];
            cubo[F, 8] = cubo[F, 6];
            cubo[F, 6] = cubo[F, 0];
            cubo[F, 0] = temp;

            temp = cubo[F, 1];
            cubo[F, 1] = cubo[F, 5];
            cubo[F, 5] = cubo[F, 7];
            cubo[F, 7] = cubo[F, 3];
            cubo[F, 3] = temp;*/

            RotarCaraSentidoAntiHorario(F);
        }

        /// <summary>
        /// Rotar cara trasera (Ej. Naranja) sentido horario
        /// </summary>
        public void Bhorario()
        {
            char temp = cubo[U, 8];
            cubo[U, 8] = cubo[R, 8];
            cubo[R, 8] = cubo[D, 6];
            cubo[D, 6] = cubo[L, 0];
            cubo[L, 0] = temp;

            temp = cubo[U, 7];
            cubo[U, 7] = cubo[R, 5];
            cubo[R, 5] = cubo[D, 7];
            cubo[D, 7] = cubo[L, 3];
            cubo[L, 3] = temp;

            temp = cubo[U, 6];
            cubo[U, 6] = cubo[R, 2];
            cubo[R, 2] = cubo[D, 8];
            cubo[D, 8] = cubo[L, 6];
            cubo[L, 6] = temp;

            RotarCaraSentidoHorario(B);
        }
        /// <summary>
        /// Rotar cara trasera (Ej. Naranja) sentido antihorario
        /// </summary>
        public void BantiHorario()
        {
            char temp = cubo[U, 6];
            cubo[U, 6] = cubo[L, 6];
            cubo[L, 6] = cubo[D, 8];
            cubo[D, 8] = cubo[R, 2];
            cubo[R, 2] = temp;

            temp = cubo[U, 7];
            cubo[U, 7] = cubo[L, 3];
            cubo[L, 3] = cubo[D, 7];
            cubo[D, 7] = cubo[R, 5];
            cubo[R, 5] = temp;

            temp = cubo[U, 8];
            cubo[U, 8] = cubo[L, 0];
            cubo[L, 0] = cubo[D, 6];
            cubo[D, 6] = cubo[R, 8];
            cubo[R, 8] = temp;

            RotarCaraSentidoAntiHorario(B);
        }
        #endregion Movimientos

        #region Basura
        public List<string> GetFrontal()
        {
            List<string> s = new List<string>();

            s.Add(string.Concat(cubo[F, 0].ToString(), " ", cubo[F, 1].ToString(), " ", cubo[F, 2].ToString()));
            s.Add(string.Concat(cubo[F, 3].ToString(), " ", cubo[F, 4].ToString(), " ", cubo[F, 5].ToString()));
            s.Add(string.Concat(cubo[F, 6].ToString(), " ", cubo[F, 7].ToString(), " ", cubo[F, 8].ToString()));

            return s;

        }
        public List<string> GetBack()
        {
            List<string> s = new List<string>();
            s.Add(string.Concat(cubo[B, 2].ToString(), " ", cubo[B, 1].ToString(), " ", cubo[B, 0].ToString()));
            s.Add(string.Concat(cubo[B, 5].ToString(), " ", cubo[B, 4].ToString(), " ", cubo[B, 3].ToString()));
            s.Add(string.Concat(cubo[B, 8].ToString(), " ", cubo[B, 7].ToString(), " ", cubo[B, 6].ToString()));
            return s;
        }
        public List<string> GetUp()
        {
            List<string> s = new List<string>();
            s.Add(string.Concat(cubo[U, 6].ToString(), " ", cubo[U, 7].ToString(), " ", cubo[U, 8].ToString()));
            s.Add(string.Concat(cubo[U, 3].ToString(), " ", cubo[U, 4].ToString(), " ", cubo[U, 5].ToString()));
            s.Add(string.Concat(cubo[U, 0].ToString(), " ", cubo[U, 1].ToString(), " ", cubo[U, 2].ToString()));
            return s;
        }
        public List<string> GetDow()
        {
            List<string> s = new List<string>();
            s.Add(string.Concat(cubo[D, 0].ToString(), " ", cubo[D, 1].ToString(), " ", cubo[D, 2].ToString()));
            s.Add(string.Concat(cubo[D, 3].ToString(), " ", cubo[D, 4].ToString(), " ", cubo[D, 5].ToString()));
            s.Add(string.Concat(cubo[D, 6].ToString(), " ", cubo[D, 7].ToString(), " ", cubo[D, 8].ToString()));
            return s;
        }
        public List<string> GetLeft()
        {
            List<string> s = new List<string>();
            s.Add(string.Concat(cubo[L, 0].ToString(), " ", cubo[L, 1].ToString(), " ", cubo[L, 2].ToString()));
            s.Add(string.Concat(cubo[L, 3].ToString(), " ", cubo[L, 4].ToString(), " ", cubo[L, 5].ToString()));
            s.Add(string.Concat(cubo[L, 6].ToString(), " ", cubo[L, 7].ToString(), " ", cubo[L, 8].ToString()));
            return s;
        }
        public List<string> GetRight()
        {
            List<string> s = new List<string>();
            s.Add(string.Concat(cubo[R, 0].ToString(), " ", cubo[R, 1].ToString(), " ", cubo[R, 2].ToString()));
            s.Add(string.Concat(cubo[R, 3].ToString(), " ", cubo[R, 4].ToString(), " ", cubo[R, 5].ToString()));
            s.Add(string.Concat(cubo[R, 6].ToString(), " ", cubo[R, 7].ToString(), " ", cubo[R, 8].ToString()));
            return s;
        }
        #endregion Basura
    }
}

