package me.moosecanswim.studentdb.Repository;

import me.moosecanswim.studentdb.Model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
