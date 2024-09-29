package org.mainfest.devSquare.DevSqaure.servicesImpl;


import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.mainfest.devSquare.DevSqaure.entities.USER;
import org.mainfest.devSquare.DevSqaure.repositories.UserRepository;
import org.mainfest.devSquare.DevSqaure.services.BloomFilterService;
import org.mainfest.devSquare.DevSqaure.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userrepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BloomFilterService bloomFilterService;

    @Override
    public USER save(USER user) {
        if (bloomFilterService.ifSUserNameIsAvailable(user.getUser_handle())) {
            logger.info("UserName Already Exists");
            return null;
        }

        if (bloomFilterService.ifSUserHandleIsAvailable(user.getUser_handle())){
            logger.info("UserHandle Already Exists");
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        bloomFilterService.AddUserName(user.getUser_handle());
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
