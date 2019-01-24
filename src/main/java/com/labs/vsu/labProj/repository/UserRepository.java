package com.labs.vsu.labProj.repository;

import com.labs.vsu.labProj.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.PATCH;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUserLastName(@Param("user_last_name") String userLastName);
    List<User> findByUserFirstName(@Param("user_first_name") String userFirstName);
    User findByUserId(@Param("user_id") long userId);
    User findByUserEmail(@Param("user_email") String userEmail);

    @Transactional
    void deleteByUserEmail(@Param("user_email") String userEmail);

    @Override
    List<User> findAll();
}
