package com.spsvn.api.domain.repository.jpa;

import com.spsvn.api.domain.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by npkhanh on 6/7/2018.
 */
public interface UserRepository extends CrudRepository<User, String> {
}
