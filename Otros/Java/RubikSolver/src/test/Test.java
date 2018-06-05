package test;

import cubo.Cubo;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cubo c = new Cubo();
		System.out.println("Cara Frontal");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(c.GetColorEnF(i, j));
			System.out.println();
		}
		System.out.println("Cara Trasera");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(c.GetColorEnB(i, j));
			System.out.println();
		}
		System.out.println("Cara Inferior");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(c.GetColorEnD(i, j));
			System.out.println();
		}
		System.out.println("Cara Superior");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(c.GetColorEnU(i, j));
			System.out.println();
		}
		System.out.println("Cara Lateral Derecha");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(c.GetColorEnR(i, j));
			System.out.println();
		}
		System.out.println("Cara Lateral Izq");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(c.GetColorEnL(i, j));
			System.out.println();
		}

	}

}
