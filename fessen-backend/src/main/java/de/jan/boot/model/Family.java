package de.jan.boot.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Family of members
 *
 */
@Entity
@Table(name="FAMILY")
public class Family
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="NAME")
	private String name;

	@Column(name="CREATION_TIME")
	private Date creationTime;	
	
	//Modification time
	@Column(name="MOD_TIME")
	private Date modTime;

	//Modification user
	@Column(name="MOD_USER")
	private String modUser;
	
    @OneToMany(mappedBy="family", orphanRemoval = true)
    private List<Member> members = new ArrayList<>(); 
    
    public void addMember(Member member) {
    	members.add(member);
    	member.setFamily(this);
    }
    
    public void removeMember(Member member) {
    	members.remove(member);
    	member.setFamily(null);
    }
    
    @OneToMany(mappedBy="family", orphanRemoval = true)
    private List<Meal> meals = new ArrayList<>(); 
    
    public void addMeal(Meal meal) {
    	meals.add(meal);
    	meal.setFamily(this);
    }
    
    public void removeMeal(Meal meal) {
    	meals.remove(meal);
    	meal.setFamily(null);
    }
    
	public Family() {
		//empty for hibernate
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModTime() {
		return modTime;
	}

	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (meals == null) {
			if (other.meals != null)
				return false;
		} else if (!meals.equals(other.meals))
			return false;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (modTime == null) {
			if (other.modTime != null)
				return false;
		} else if (!modTime.equals(other.modTime))
			return false;
		if (modUser == null) {
			if (other.modUser != null)
				return false;
		} else if (!modUser.equals(other.modUser))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Family [id=" + id + ", code=" + code + ", password=" + password + ", name=" + name + ", creationTime="
				+ creationTime + ", modTime=" + modTime + ", modUser=" + modUser + ", members=" + members + ", meals="
				+ meals + "]";
	}
}
