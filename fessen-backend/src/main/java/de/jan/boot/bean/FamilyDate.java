package de.jan.boot.bean;

import java.util.Date;

public class FamilyDate {

	String familyCode;
	Date day;
	
	public FamilyDate() {
		//empty constructor
	}
	
	public FamilyDate(String familyCode, Date day) {
		this.familyCode = familyCode;
		this.day = day;
	}

	public String getFamilyCode() {
		return familyCode;
	}

	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((familyCode == null) ? 0 : familyCode.hashCode());
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
		FamilyDate other = (FamilyDate) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (familyCode == null) {
			if (other.familyCode != null)
				return false;
		} else if (!familyCode.equals(other.familyCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FamilyDate [familyCode=" + familyCode + ", day=" + day + "]";
	}
	
}
