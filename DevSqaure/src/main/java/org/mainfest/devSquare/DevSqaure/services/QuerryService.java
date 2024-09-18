package org.mainfest.devSquare.DevSqaure.services;

import org.bson.types.ObjectId;
import org.mainfest.devSquare.DevSqaure.entities.Querry;
import org.mainfest.devSquare.DevSqaure.entities.USER;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface QuerryService {

    Querry save(Querry querry,String name);
    Querry delete(ObjectId id,String name);
    Querry update(Querry querry,String name);
    List<Querry> fetchAll(String name);
    Querry fetchByID(ObjectId id);

}
