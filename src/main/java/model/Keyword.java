package model;

import java.util.ArrayList;

public class Keyword {
	private ArrayList<String> name;
	private int id;
	public Keyword(ArrayList<String> name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	public Keyword(ArrayList<String> name) {
		super();
		this.name = name;
	}
	public Keyword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<String> getName() {
		return name;
	}
	public void setName(ArrayList<String> name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
