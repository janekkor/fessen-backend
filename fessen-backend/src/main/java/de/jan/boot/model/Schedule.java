package de.jan.boot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Member meal association class
 *
 */
@Entity
@Table(name="SCHEDULE")
public class Schedule
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@OneToOne
	@JoinColumn(name="MEAL_ID")
	private Meal meal;
	
	@Column(name="DAY")
	private Date day;
	
	@Column(name="CREATION_TIME")
	private Date creationTime;	
	
	//Modification time
	@Column(name="MOD_TIME")
	private Date modTime;

	//Modification user
	@Column(name="MOD_USER")
	private String modUser;

	public Schedule() {
		//empty for hibernate
	}

	public Schedule(Member member, Meal meal, Date day, Date creationTime, Date modTime, String modUser) {
		super();
		this.member = member;
		this.meal = meal;
		this.day = day;
		this.creationTime = creationTime;
		this.modTime = modTime;
		this.modUser = modUser;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
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
		Schedule other = (Schedule) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (meal == null) {
			if (other.meal != null)
				return false;
		} else if (!meal.equals(other.meal))
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
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
		return true;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", member=" + member + ", meal=" + meal + ", day=" + day + ", creationTime="
				+ creationTime + ", modTime=" + modTime + ", modUser=" + modUser + "]";
	}
}
