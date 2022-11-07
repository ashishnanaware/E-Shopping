package com.velocity.users;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Just {

	private String pname;
	private String quantity;

	public Just() {
		super();
	}

	public Just(String pname, String quantity) {
		super();
		this.pname = pname;
		this.quantity = quantity;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public static void main(String[] args) {

		LinkedHashMap<Integer, Just> map = new LinkedHashMap<Integer, Just>(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
			put(1, new Just("fan","10"));
			put(2, new Just("data cable","10"));
		}};
		Iterator<Entry<Integer, Just>> itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<Integer, Just> entry = (Map.Entry<Integer, Just>) itr
					.next();
			System.out.println(entry.getKey()+" "+entry.getValue().pname+"t\t\t"+entry.getValue().quantity);
		}
		
	}

}
