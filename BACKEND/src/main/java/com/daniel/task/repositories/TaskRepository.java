package com.daniel.task.repositories;

import com.daniel.task.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);

    @Modifying
    @Transactional
    void deleteById(Long id);

    @Query(value="SELECT * FROM task", nativeQuery = true)
    public List<Task> getAll();

    @Transactional
    @Modifying
    @Query(value="insert into task (description) values (:#{#description})", nativeQuery = true)
    public List<Task> saveTask(@Param("description") String description);


}
