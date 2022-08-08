package com.dimuthu.bankAccount.repositories;

import com.dimuthu.bankAccount.entitities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
