package org.JHM.library.models.data;

import org.JHM.library.models.objects.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

//interacts with database
//Data access object

@Transactional
public interface UserDAO extends CrudRepository<User, Integer> {


    User getFirstByEmail(String email);
    ArrayList<User> getAllByEmail (String email);
}
