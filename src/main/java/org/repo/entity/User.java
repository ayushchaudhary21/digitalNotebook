package org.repo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


     @Column(nullable=false,unique = true)
    private String userName;

    @Column(nullable=false)
     private String password;
    @Column(nullable=false,unique=true)
    private String userEmail;

    @OneToMany( mappedBy = "userId", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
      List<Notes> notesList=new ArrayList<>();

}
