package com.example.vidhyarthi.repository;

import com.example.vidhyarthi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from system_user_login s where s.USERNAME= :userName", nativeQuery = true)
    public User findByUserName(String userName);
          
}
