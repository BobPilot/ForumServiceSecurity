package telran.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.forum.entity.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}
