package com.velocity.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.velocity.connection.DbConnection;
import com.velocity.login.Login;
import com.velocity.product.Order;
import com.velocity.product.Products;

public class User {
	public static int id;
	public static String uname;
	private String pwd;

	public User() {
		super();
	}

	public User(int id, String uname) {
		super();
		this.id = id;
		this.uname = uname;
	}

	public User(String uname, String pwd) {
		super();
		this.uname = uname;
		this.pwd = pwd;
	}

	

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		User.id = id;
	}

	public static String getUname() {
		return uname;
	}

	public static void setUname(String uname) {
		User.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public static void menu() {

		int option = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("******************************WELCOME USER **************************************\n");

		System.out.println("                     Available Products for Shopping\n                  ");
		System.out.println("================================================================================== ");
		System.out.println("    Product Name\t\tDescription\tPrice");
		System.out.println("----------------------------------------------------------------------------------");
		Products.showProducts();
		System.out.println("================================================================================== ");
		System.out.println("\n.------------------------------.         .------------------------------.");
		System.out.println("|       1. Shopping            |         |          2.  Logout           |");
		System.out.println("'------------------------------'         '------------------------------' ");
		System.out.println("                                             (Press 1:Shopping 2:logout)");
		System.out.println("***********************************************************************************");

		if (sc.hasNextInt()) {
			option = sc.nextInt();

			if (option == 1 || option == 2) {
				switch (option) {
				case 1:
					Order.shopping();

					break;
				case 2:
					System.out.println("Thank you...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Login.showUser();

					break;

				default:
					System.err.println("Choose a correct option to proceed");
					menu();
					break;
				}
				sc.close();
			} else {
				System.err.println("Choose a correct option to proceed");
				menu();
			}
		} else {
			System.err.println("Choose a correct option to proceed");
			menu();
		}

	}

	public static void createUser() {
		System.out.println(
				" ------------------------------------: USER REGISTRATION :-------------------------------------");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Username:");
		String name = sc.next();
		System.out.println("Enter password:");
		String pwd = sc.next();
		User user = new User(name, pwd);

		try {
			Connection con = DbConnection.getConnection();
			String sql = "insert into user(uname, pwd) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUname());
			ps.setString(2, user.getPwd());
			ps.executeUpdate();
			System.out.println("Account created sucessfully");
			Thread.sleep(2000);
			System.out.print("Please wait");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.print(".");
		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			login();
			sc.close();
		}
	}

	public static void login() {
		System.out.println(" ------------------------------------: USER LOGIN :-------------------------------------");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter user name:");
		String uname = sc.next();
		System.out.println("Enter password");
		String pwd = sc.next();
		Connection con = DbConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user where uname=? ");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				@SuppressWarnings("unused")
				User user = new User(rs.getInt(1), rs.getString(2));
				if (uname.equals(rs.getString(2)) && pwd.equals(rs.getString(3))) {

					System.out.println("login sucessfully...");
					menu();
				} else {
					System.out.println("Enter correct credentials");
					login();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}
}
