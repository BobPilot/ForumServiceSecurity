package telran.forum.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import telran.forum.configuration.AccountConfiguration;
import telran.forum.dao.UserAccountRepository;
import telran.forum.entity.UserAccount;
import telran.forum.dto.UserProfileDto;
import telran.forum.dto.UserRegisterDto;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	UserAccountRepository userRepository;
	
	@Autowired
	AccountConfiguration accountConfiguration;


	@Override
	public UserProfileDto addUser(UserRegisterDto userRegDto) {
		if (userRepository.existsById(userRegDto.getId())) {
			throw new UserExistException();
		}
		String hashPassword = accountConfiguration
				.getEncodePassword(userRegDto.getPassword());
		UserAccount userAccount = UserAccount.builder()
				.id(userRegDto.getId())
				.password(hashPassword)
				.firstName(userRegDto.getFirstName())
				.lastName(userRegDto.getLastName())
				.role("User")
				.expDate(LocalDateTime.now().plusDays(accountConfiguration.getExpPeriod()))
				.build();
		userRepository.save(userAccount);
		return new UserProfileDto(userAccount);
	}

	@Override
	public UserProfileDto editUser(UserRegisterDto userRegDto) {
		UserAccount user = findUser(userRegDto.getId());

		user.setFirstName(checkData(user.getFirstName(), userRegDto.getFirstName()));
		user.setLastName(checkData(user.getLastName(), userRegDto.getLastName()));

		userRepository.save(user);

		return new UserProfileDto(user);
	}

	@Override
	public UserProfileDto removeUser(String id) {
		UserAccount user = findUser(id);
		userRepository.deleteById(id);
		return new UserProfileDto(user);
	}

	private UserAccount findUser(String id) {
		return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}

	private String checkData(String userData, String updData) {
		return updData == null ? userData : updData;
	}

	@Override
	public Set<String> addRole(String id, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> removeRole(String id, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String password) {
		// TODO Auto-generated method stub

	}

}
