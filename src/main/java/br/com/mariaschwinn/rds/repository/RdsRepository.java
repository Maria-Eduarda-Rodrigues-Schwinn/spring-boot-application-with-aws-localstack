package br.com.mariaschwinn.rds.repository;

import br.com.mariaschwinn.rds.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RdsRepository extends CrudRepository<Student, Integer> {

    List<Student> findByNameContainingIgnoreCase(String name);
}
