package com.cg.busbooking.repository;

import com.cg.busbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
