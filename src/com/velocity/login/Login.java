package com.velocity.login;

import java.util.Scanner;

import com.velocity.users.Administrator;
import com.velocity.users.User;

public class Login {

	public void welcome() {

	}

	public static void showUser() {
		int option = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("O------------------------------------------------------------------------------O\n");
				
		System.out.println("                1: Admin login                      2: User Login/Sig           \n");
				
		System.out.println("O------------------------------------------------------------------------------O");
		
		System.out
				.println("                             (Press: 1:For admin login | 2: For User login/SignUp)");
		if (sc.hasNextInt()) {
			option = sc.nextInt();
			if (option == 1 || option == 2) {
				if (option == 1) {
					Administrator.login();
				} else {
					userConfirm();
				}
			} else {
				System.err.println("Enter correct option");
				showUser();
			}
		} else {
			System.err.println("Enter correct option");
			showUser();
		}
		sc.close();

	}

	public static void userConfirm() {
		int option = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("                            USER LOGIN OR REGISTRATION                                ");
		System.out
				.println("O----------------------------------------------------------------------------------O");
		System.out.println("                            1: login                                                            ");
		System.out.println("                            2: Create User Account                                                  ");
		System.out
				.println("O----------------------------------------------------------------------------------O");
		System.out
				.println("                               (Press: 1:For admin login | 2: For User login/SignUp)");

		if (sc.hasNextInt()) {
			option = sc.nextInt();
			if (option == 1 || option == 2) {
				if (option == 1) {
					User.login();
				} else {
					User.createUser();
				}
			} else {
				System.err.println("Enter correct option");
				userConfirm();
			}
		} else {
			System.err.println("Enter correct option");
			userConfirm();
		}
		sc.close();

	}


}
