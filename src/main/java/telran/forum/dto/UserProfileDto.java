package telran.forum.dto;

import lombok.*;
import telran.forum.entity.UserAccount;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserProfileDto {
	String id;
	String firstName;
	String lastName;

	public UserProfileDto(UserAccount user){
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}
}
