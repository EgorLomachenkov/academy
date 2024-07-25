package model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private  int id;
    private String name;
    private String email;
    private String password;
    private boolean isActive; // not active by default
    private Timestamp createdTs;
    private Timestamp updatedTs;

}
