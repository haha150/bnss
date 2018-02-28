package org.bnss.repository;

import java.util.List;

import org.bnss.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomRepository {
	List<User> getAllUsers();

}
