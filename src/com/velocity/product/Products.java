package com.velocity.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.Map;


import com.velocity.connection.DbConnection;

public class Products {
	
	public int pro_id;
	public String pro_desc;
	public int pro_price;
	public String pro_name;
	public int quantity;
	
	public Products() {
		super();
	}

	public Products(int pro_id, int pro_price, String pro_name, int quantity) {
		super();
		this.pro_id = pro_id;
		this.pro_price = pro_price;
		this.pro_name = pro_name;
		this.quantity = quantity;
	}

	public Products(int pro_id, String pro_desc, int pro_price, String pro_name, int quantity) {
		super();
		this.pro_id = pro_id;
		this.pro_desc = pro_desc;
		this.pro_price = pro_price;
		this.pro_name = pro_name;
		this.quantity = quantity;
	}

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public String getPro_desc() {
		return pro_desc;
	}

	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}

	public int getPro_price() {
		return pro_price;
	}

	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public static void showProducts() {
		
		LinkedHashMap<Integer, Products> map = new LinkedHashMap<Integer, Products>();
		
		Connection con = DbConnection.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("select * from product");
			ResultSet rs =pst.executeQuery();
			while(rs.next()) {
				
				map.put(rs.getInt(1), new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));
			}
			
			Iterator<Map.Entry<Integer, Products>> itr = map.entrySet().iterator();
		
			while (itr.hasNext()) {
				Map.Entry<Integer, Products> entry =  itr.next();

				System.out.println(""+entry.getKey()+". "+entry.getValue().pro_name+"\t\t"+entry.getValue().pro_desc+"\t"+entry.getValue().pro_price);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
