package com.note.Notes.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


     @Column(nullable=false,unique = true)
    String userName;

    @Column(nullable=false)
    String password;
    String userEmail;

    @OneToMany
   List<Notes> list=new ArrayList<>();

}
