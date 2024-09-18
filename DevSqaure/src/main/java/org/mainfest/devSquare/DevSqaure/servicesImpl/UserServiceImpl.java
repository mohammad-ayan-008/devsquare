package org.mainfest.devSquare.DevSqaure.servicesImpl;


import org.bson.types.ObjectId;
import org.mainfest.devSquare.DevSqaure.entities.USER;
import org.mainfest.devSquare.DevSqaure.repositories.UserRepository;
import org.mainfest.devSquare.DevSqaure.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userrepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public USER save(USER user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userrepository.save(user);
    }

    @Override
    public boolean delete(ObjectId id) {
        if (userrepository.findById(id) == null)  return false;
        userrepository.deleteById(id);
        return true;
    }

    @Override
    public USER update(USER user) {
        return userrepository.save(user);
    }

    @Override
    public List<USER> fetchAll() {
        return userrepository.findAll();
    }

    @Override
    public USER fetchByID(ObjectId id) {
        return userrepository.findById(id).orElse(new USER());
    }
}
