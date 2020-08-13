package samworks.UsersService.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AccountDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int accountNumber;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity xyz;
	
	private String accountType;
	private long accountBalance;
	
	public AccountDetails() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public UserEntity getXyz() {
		return xyz;
	}
	public void setXyz(UserEntity xyz) {
		this.xyz = xyz;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}
	public AccountDetails(int id, int accountNumber, UserEntity xyz, String accountType, long accountBalance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.xyz = xyz;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	
	
	
	
}
