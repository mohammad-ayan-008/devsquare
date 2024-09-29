package org.mainfest.devSquare.DevSqaure.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
    String reply_by;
    String reply_to;
    String reply;
}