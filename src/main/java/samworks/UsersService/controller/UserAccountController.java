package samworks.UsersService.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import samworks.UsersService.entity.AccountDetails;
import samworks.UsersService.entity.UserEntity;
import samworks.UsersService.repository.AccountRepository;
import samworks.UsersService.repository.UsersRepository;

@RestController
@RequestMapping("/UserAcoounts")
public class UserAccountController {
	@Autowired
	private AccountRepository actRepo;
	@Autowired
	private UsersRepository userRepo;

	@GetMapping("/getAllUsers")
	public List<UserEntity> getAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/getUsersById/{id}")
	public Optional<UserEntity> getUserById(@PathVariable int id) {
		Optional<UserEntity> obj = userRepo.findById(id);
		return obj;
	}

	@GetMapping("/getUsersBalanceById/{id}")
	public ResponseEntity<?> getUserBalanceById(@PathVariable int id) {
		Optional<UserEntity> obj = userRepo.findById(id);
		if (obj.isPresent()) {
			UserEntity presentUser = obj.get();
			AccountDetails userAccount = presentUser.getUsrAccntId();

			return new ResponseEntity<Long>(userAccount.getAccountBalance(), HttpStatus.OK);
		}else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/addUser")
	public void addUser(@RequestBody UserEntity usr) {
		AccountDetails acctDetails = new AccountDetails();
		acctDetails.setAccountBalance(0);
		acctDetails.setAccountType("checkings");
		acctDetails.setAccountNumber(new Random().nextInt());
		acctDetails.setXyz(usr);
		usr.setUsrAccntId(acctDetails);
		userRepo.save(usr);
	}

	@DeleteMapping("/deleteUserById/{id}")
	public void deleteUserById(@PathVariable int id) {
		userRepo.deleteById(id);
	}

	@PutMapping("/deductBalance/{usrId}/{deductionAmount}")
	public ResponseEntity<?> deductBalanceByUserId(@PathVariable int usrId,
			@PathVariable(value = "deductionAmount") Long deductionAmount) {
		Optional<UserEntity> userEn = userRepo.findById(usrId);
		Optional<AccountDetails> obj3 = actRepo.findByXyz(userEn);

		if (obj3.isPresent()) {
			AccountDetails currentAccount = obj3.get();
			if (currentAccount.getAccountBalance() > deductionAmount) {
				long currentBalance = currentAccount.getAccountBalance();
				currentAccount.setAccountBalance(currentBalance - deductionAmount);
				actRepo.save(currentAccount);
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);

			} else {
				return new ResponseEntity<String>("Not Sufficient Balance", HttpStatus.BAD_REQUEST);
			}

		} else {
			return new ResponseEntity<String>("No Account Found", HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/addBalance/{usrId}/{creditAmount}")
	public ResponseEntity<?> creditBalance(@PathVariable int usrId,
			@PathVariable(value = "creditAmount") Long creditAmount) {
		Optional<UserEntity> userEntity = userRepo.findById(usrId);
		Optional<AccountDetails> optionalEntity = actRepo.findByXyz(userEntity);
		if (optionalEntity.isPresent()) {
			AccountDetails currentAccount = optionalEntity.get();
			long currentBalance = currentAccount.getAccountBalance();
			long updatedBalance = currentBalance + creditAmount;
			currentAccount.setAccountBalance(updatedBalance);
			actRepo.save(currentAccount);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid UserId", HttpStatus.NOT_FOUND);
		}

	}

}
