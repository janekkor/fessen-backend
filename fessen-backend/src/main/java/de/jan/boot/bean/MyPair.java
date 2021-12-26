package de.jan.boot.bean;

public class MyPair {
	
	String value1;
	String value2;
	
	public MyPair() {
		//empty constructor
	}
	
	public MyPair(String value1, String value2) {
		super();
		this.value1 = value1;
		this.value2 = value2;
	}
	
	public String getValue1() {
		return value1;
	}
	
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	
	public String getValue2() {
		return value2;
	}
	
	public void setValue2(String value2) {
		this.value2 = value2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
		result = prime * result + ((value2 == null) ? 0 : value2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyPair other = (MyPair) obj;
		if (value1 == null) {
			if (other.value1 != null)
				return false;
		} else if (!value1.equals(other.value1))
			return false;
		if (value2 == null) {
			if (other.value2 != null)
				return false;
		} else if (!value2.equals(other.value2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MyPair [value1=" + value1 + ", value2=" + value2 + "]";
	}
	
	

}
