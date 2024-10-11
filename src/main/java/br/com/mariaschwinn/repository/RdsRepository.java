package br.com.mariaschwinn.repository;

import br.com.mariaschwinn.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RdsRepository extends CrudRepository<Student, Integer> {

    List<Student> findByNameContainingIgnoreCase(String name);
}
