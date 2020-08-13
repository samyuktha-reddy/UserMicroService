package samworks.UsersService.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	private String address;
	private String country;
	private char gender;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "xyz", cascade = CascadeType.ALL)
	private AccountDetails usrAccntId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public AccountDetails getUsrAccntId() {
		return usrAccntId;
	}

	public void setUsrAccntId(AccountDetails usrAccntId) {
		this.usrAccntId = usrAccntId;
	}

	public UserEntity(int id, String name, int age, String address, String country, char gender,
			AccountDetails usrAccntId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.country = country;
		this.gender = gender;
		this.usrAccntId = usrAccntId;
	}

	public UserEntity() {
		
	}
}
