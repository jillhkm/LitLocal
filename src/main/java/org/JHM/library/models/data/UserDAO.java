package org.JHM.library.models.data;

import org.JHM.library.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//interacts with database
//Data access object
@Repository
@Transactional
public interface UserDAO extends CrudRepository<User, Integer> {


    ArrayList<User> getUserByEmail(String email);
    ArrayList<User> getAllByEmail (String email);
}
