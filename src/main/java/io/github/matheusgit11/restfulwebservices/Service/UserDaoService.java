package io.github.matheusgit11.restfulwebservices.Service;

import io.github.matheusgit11.restfulwebservices.Entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = '0';

    static{
        users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"Lara", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount,"John", LocalDate.now().minusYears(23)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
