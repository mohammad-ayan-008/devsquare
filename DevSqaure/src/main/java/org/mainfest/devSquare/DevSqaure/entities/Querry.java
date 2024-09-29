package org.mainfest.devSquare.DevSqaure.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "querry_collection")
@ToString
public class Querry {

    @Id
    private ObjectId _id;

    private String posted_by;

    private  String Querry;

    private  List<String> helpFull = new ArrayList();

    private List<CommentDTO> replies = new ArrayList<>();


}
