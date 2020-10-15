package com.basusingh.scbot;

import java.io.*;
import java.util.Scanner;

import static java.lang.Runtime.*;

public class message {
	public static void main(String twinks[]) {
		try {
			System.out.print("\nHow Many Row U want to Create:=");
			Scanner sc = new Scanner(System.in);
			int row = sc.nextInt();
			System.out.print("\nHow Many Column U want to Create:=");
			int col = sc.nextInt();
			;

			int arr1[][] = new int[row][col];
			int arr2[][] = new int[row][col];
			int arr3[][] = new int[row][col];
			int S3;

			System.out.print("\nMatrix 1:=");
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.print("\n\tEnter [" + i + "] [" + j + "] :=");
					S3 = sc.nextInt();
					arr1[i][j] = S3;
				}
			}

			System.out.print("\nMatrix 2:=");
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.print("\n\tEnter [" + i + "] [" + j + "] :=");
					S3 = sc.nextInt();
					arr2[i][j] = S3;
				}
			}
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.print(" " + arr1[i][j]);
				}
				System.out.println(" ");
			}
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.print(" " + arr2[i][j]);
				}
				System.out.println(" ");
			}

			int ch;
			do {
				System.out.print("\n\n\t\t\t !!!!.....WELLCOME IN 2-D ARRAY .....!!!!");
				System.out.print("\n\t 1.ADDITION");
				System.out.print("\n\t 2.Substraction");
				System.out.print("\n\t 3.Multiplication");
				System.out.print("\n\t 4.Exit");
				System.out.print("\n\n\n\t Enter ur Choise:=");

				ch = sc.nextInt();

				switch (ch) {
				case 1:
					for (int i = 0; i < row; i++) {
						for (int j = 0; j < col; j++) {
							System.out.print(" " + (arr1[i][j] + arr2[i][j]));
						}
						System.out.println(" ");
					}

					break;
				case 2:
					for (int i = 0; i < row; i++) {
						for (int j = 0; j < col; j++) {
							System.out.print(" " + (arr1[i][j] - arr2[i][j]));
						}
						System.out.println(" ");
					}
					break;
				case 3:
					for (int i = 0; i < row; i++) {
						for (int j = 0; j < col; j++) {
							for (int k = 0; k < col; k++) {
								arr3[i][j] = arr3[i][j] + arr1[i][k] * arr2[k][j];
							}
						}
					}
					for (int i = 0; i < row; i++) {
						for (int j = 0; j < col; j++) {
							System.out.print(" " + arr3[i][j]);
						}
						System.out.println(" ");
					}

					break;

				case 4:
					sc.close();
					try {
						getRuntime().exit(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}

			} while (ch != 4);
		} finally {

		}
	}

}