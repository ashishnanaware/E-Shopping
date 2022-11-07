package com.velocity.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.velocity.connection.DbConnection;
import com.velocity.login.Login;

public class Administrator {

	public static void login() {
		System.out.println(
				" ------------------------------------: ADMINISTRATOR LOGIN :-------------------------------------");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Administrator:");
		String uname = sc.next();
		System.out.println("Enter password");
		String pwd = sc.next();
		Connection con = DbConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from admin where uname=? ");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (uname.equals(rs.getString(2)) && pwd.equals(rs.getString(3))) {
					System.out.println("Please wait...");
					Thread.sleep(2000);
					menu();
				} else {
					System.out.println("Enter correct credentials");
					login();
				}
			}
		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}

	public static void checkQuantity() {

		Scanner sc = new Scanner(System.in);
		System.out.println(".------------------------------------------------------------------.");
		System.out.println("|                       : CHECK QUANTITY  :                        |");
		System.out.println("'------------------------------------------------------------------'");
		System.out.println("Enter product ID:  ");
		if (sc.hasNextInt()) {

			Connection con = DbConnection.getConnection();
			try {

				PreparedStatement pst = con.prepareStatement("select pro_name, quantity from product where pro_id=?");
				int id = sc.nextInt();
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				System.out.println("ID   Product Name         Quantity    ");
				while (rs.next()) {
					System.out.println(id + "     " + rs.getString(1) + "               " + rs.getString(2));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("'------------------------------------------------------------------'");
		}

		menu();
		sc.close();
	}

	public static void checkUserList() {

		Connection con = DbConnection.getConnection();
		try {
			System.out.println(".-------------------------------------------------------------------------------.");
			System.out.println("|                             : REGISTERED USER :                               |");
			System.out.println("'-------------------------------------------------------------------------------'");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from user");

			System.out.println("\t\t  Registered User");
			System.out.println("\t\t  --------------");
			while (rs.next()) {
				System.out.println("\t\t" + rs.getRow() + ". " + rs.getString(2));
			}
			System.out.println("'--------------------------------------------------------------------------------'");
			menu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void menu() {
		int option = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("********************************** ADMINISTRATOR **********************************\n");
		System.out.println(".----------------------.                         .----------------------.");
		System.out.println("|   1.  UserList       |                       |  2. Check Quantity    |");
		System.out.println(
				"'----------------------'                         '-----------------------'                           ");

		System.out.println(".-----------------------                        .-----------------------.");
		System.out.println("|  3. User history      |                       |  4.  Logout           |");
		System.out.println("'-----------------------                        '-----------------------'");
		System.out.println("                       (Press 1:Userlist, 2:Check Quantity, 3: User History, 4: logout)");
		System.out.println("***********************************************************************************");

		if (sc.hasNextInt()) {
			option = sc.nextInt();

			if (option == 1 || option == 2 || option == 3 || option == 4) {
				switch (option) {
				case 1:
					checkUserList();

					break;
				case 2:
					checkQuantity();

					break;
				case 3:
					showUserhistory();
					break;
				case 4:
					Login.showUser();
					break;

				default:
					System.out.println("Choose a correct option to proceed");
					menu();
					break;
				}
				
			} else {
				System.out.println("Choose a correct option to proceed");
				menu();
			}
		} else {
			System.out.println("Choose a correct option to proceed");
			menu();
		}

		sc.close();
	}

	public static void showUserhistory() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter User Name:");
		String name=null;
		try {
			name = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection con = DbConnection.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("select pname, price, qty from orders where uname=?");
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		menu();
	}

}
