package org.mainfest.devSquare.DevSqaure.servicesImpl;

import org.bson.types.ObjectId;
import org.mainfest.devSquare.DevSqaure.entities.Querry;
import org.mainfest.devSquare.DevSqaure.entities.USER;
import org.mainfest.devSquare.DevSqaure.repositories.QuerriesRepository;
import org.mainfest.devSquare.DevSqaure.repositories.UserRepository;
import org.mainfest.devSquare.DevSqaure.services.QuerryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuerryServiceImpl implements QuerryService {
    @Autowired
    private QuerriesRepository querriesRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Querry save(Querry querry,String name) {
        USER byUserName = userRepository.findByUserName(name);
        Querry save = querriesRepository.save(querry);
        if (byUserName != null){
            byUserName.getQuerries().add(save);
            userRepository.save(byUserName);
        }
        return  save;
    }

    @Override
    public Querry delete(ObjectId id,String name) {
        List<Querry> collect = userRepository.findByUserName(name).getQuerries().stream().filter(querry -> id == querry.getId()).collect(Collectors.toList());
        if(querriesRepository.findById(id).isPresent() && !collect.isEmpty()) {
            querriesRepository.deleteById(id);
            return collect.get(0);
        }
        return null;
    }

    @Override
    public Querry update(Querry querry,String name) {
         if (querriesRepository.findById(querry.getId()).isPresent()){
            querriesRepository.save(querry);
         }
         return new Querry();
    }

    @Override
    public List<Querry> fetchAll(String name) {
        return userRepository.findByUserName(name).getQuerries();
    }

    @Override
    public Querry fetchByID(ObjectId id) {
        return querriesRepository.findById(id).orElse(null);
    }
}
