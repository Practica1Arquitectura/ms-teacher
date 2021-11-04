package arquitectura.software.msteacher.repository;

import arquitectura.software.msteacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
