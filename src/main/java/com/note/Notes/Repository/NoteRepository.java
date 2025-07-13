package com.note.Notes.Repository;

import com.note.Notes.Entity.Notes;
import com.note.Notes.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Notes,Long> {
    @Transactional
    @Modifying
      void  deleteByTitle(String title);
   Optional<Notes> findByTitle(String title);
}
