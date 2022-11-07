package com.velocity.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.Map;
import java.util.Scanner;

import com.velocity.connection.DbConnection;
import com.velocity.login.Login;
import com.velocity.users.User;

public class Order extends User {

	public static Products choose(int no) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Product Qty: ");
		int quantity = 0;
		try {
			quantity = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = select(no);
		Products products = null;
		// sc.close();
		try {
			while (rs.next()) {

				products = new Products(rs.getInt(1), rs.getInt(3), rs.getString(4), quantity);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	public static ResultSet select(int no) {

		Connection con = DbConnection.getConnection();
		ResultSet rs = null;

		try {
			PreparedStatement pst = con.prepareStatement("select * from product where pro_id = ?");
			pst.setInt(1, no);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;

	}

	public static void confirmOrder(LinkedHashMap<Integer, Products> map) {
		
		Connection con = DbConnection.getConnection();
		PreparedStatement pst=null;
		try {
			pst = con.prepareStatement("insert into orders(uid, uname, pid, pname, price, qty) values(?,?,?,?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Map.Entry<Integer, Products>> itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<Integer, Products> entry = itr.next();

			try {
				pst.setInt(1, id);pst.setString(2, uname);pst.setInt(3, entry.getKey());pst.setString(4, entry.getValue().pro_name);
				pst.setInt(5, entry.getValue().pro_price);pst.setInt(6, entry.getValue().quantity);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println(uname+" your order is confirmed...");
		thanks();
	}
	public static void thanks() {
		System.out.println(".---------------------.                          .---------------------.");
		System.out.println("|    1. EXIT   |      |                          |    3. Logout        |");
		System.out.println("'---------------------'                          '---------------------'");
		System.out.println("                                                 (Press 1:EXIT  2:Logout)");
		System.out.println("************************************************************************");
		Scanner scc = new Scanner(System.in);
		int option;
		if (scc.hasNextInt()) {
			option = scc.nextInt();

			if (option == 1 || option == 2 || option == 3) {
				switch (option) {
				case 1:
					User.menu();
					break;
				case 2:
					Login.showUser();
					break;
				
				default:
					System.err.println("Choose a correct option to proceed");
					thanks();
					break;
				}

			} else {
				System.err.println("Choose a correct option to proceed");
				thanks();
			}
		} else {
			System.err.println("Choose a correct option to proceed");
			thanks();
		}
		scc.close();
	}
	
	public static void confirmation(LinkedHashMap<Integer, Products> map) {
		System.out.println(".---------------------. .----------------------. .---------------------.");
		System.out.println("|    1. Place Order   | |    2.Cancel Order    | |      3. EXIT        |");
		System.out.println("'---------------------' '----------------------' '---------------------'");
		System.out.println("                       (Press 1:Place Order  2:Cancle Order 3: EXIT)");
		System.out.println("************************************************************************");
		Scanner scc = new Scanner(System.in);
		int option;
		if (scc.hasNextInt()) {
			option = scc.nextInt();

			if (option == 1 || option == 2 || option == 3) {
				switch (option) {
				case 1:
					confirmOrder(map);
					break;
				case 2:
					shopping();
					break;
				case 3:
					User.menu();
					break;

				default:
					System.err.println("Choose a correct option to proceed");
					confirmation(map);
					break;
				}

			} else {
				System.err.println("Choose a correct option to proceed");
				confirmation(map);
			}
		} else {
			System.err.println("Choose a correct option to proceed");
			confirmation(map);
		}
		scc.close();
	}

	public static void shopping() {

		Products.showProducts();
		LinkedHashMap<Integer, Products> map = new LinkedHashMap<Integer, Products>();
		System.out.println(".-------------------------------------------------------------------------.");
		System.out.println("|               Enter Sr. No. for product selection:          ( EXIT:0)   |");
		System.out.println("'-------------------------------------------------------------------------'");

		int option;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Sr.No:");
			option = sc.nextInt();
			switch (option) {
			case 1:
				Products p = choose(option);
				map.put(p.getPro_id(), p);
				break;
			case 2:
				Products p1 = choose(option);
				map.put(p1.getPro_id(), p1);

				break;
			case 3:
				Products p3 = choose(option);
				map.put(p3.getPro_id(), p3);

				break;
			case 4:
				Products p4 = choose(option);
				map.put(p4.getPro_id(), p4);

				break;
			case 5:
				Products p5 = choose(option);
				map.put(p5.getPro_id(), p5);
				break;
			case 6:
				Products p6 = choose(option);
				map.put(p6.getPro_id(), p6);
				break;
			case 7:
				Products p7 = choose(option);
				map.put(p7.getPro_id(), p7);
				break;
			case 8:
				Products p8 = choose(option);
				map.put(p8.getPro_id(), p8);
				break;
			case 9:
				Products p9 = choose(option);
				map.put(p9.getPro_id(), p9);
				break;
			case 10:
				Products p10 = choose(option);
				map.put(p10.getPro_id(), p10);
				break;
			case 0:
				break;
			default:
				System.err.println("Choose a correct option to proceed");
				break;
			}

			System.out.println("Do you want to select more product select another Sr.No");
		} while (option != 0);

		System.out.println("************************************************************************");
		Iterator<Map.Entry<Integer, Products>> itr = map.entrySet().iterator();
		System.out.println("ID. Product Name\t\tPrice\tQuantity");
		int total=0;
		while (itr.hasNext()) {
			Map.Entry<Integer, Products> entry = itr.next();

			System.out.println("" + entry.getKey() + ". " + entry.getValue().pro_name + "\t\t"
					+ entry.getValue().pro_price + "\t" + entry.getValue().quantity);
			total=total+(entry.getValue().pro_price*entry.getValue().quantity);

		}
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("                             Grand Total :"+total);
		
		confirmation(map);
		sc.close();
	}

}
