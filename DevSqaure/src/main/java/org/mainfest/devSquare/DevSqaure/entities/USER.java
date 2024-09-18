package org.mainfest.devSquare.DevSqaure.entities;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "user_collection")
public class USER {
    @Id
    private ObjectId id;

    private String userName;

    private String password;

    private String user_handle;

    private List<String> roles= new ArrayList<>();

    @DBRef
    private ArrayList<Querry> querries = new ArrayList<>();

    @Transient
    private int no_of_querries_asked;


}
