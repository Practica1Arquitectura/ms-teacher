package arquitectura.software.msteacher.api;

import arquitectura.software.msteacher.entity.Teacher;
import arquitectura.software.msteacher.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/api/teacher")
public class TeacherController {

    private static Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(path = "/test",method = RequestMethod.GET)
    public String testTeacher(){
        return "Ms-Teacher";
    }

    @RequestMapping(path = "/save",method = RequestMethod.POST)
    public Teacher saveTeacher(@RequestBody Teacher teacher){
        LOGGER.info("Registrando un profesor: con los siguientes datos, {} ",teacher);
        return teacherRepository.save(teacher);
    }

    @RequestMapping(path = "/all",method = RequestMethod.GET)
    public List<Teacher> getAllStudent(){
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers;
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public Teacher updateTeacher(@RequestParam Integer teacherId, @RequestBody Teacher teacher){
        Teacher teacherSearch = teacherRepository.findById(teacherId).get();
        teacherSearch.setName(teacher.getName());
        teacherSearch.setSubject(teacher.getSubject());
        return teacherRepository.save(teacherSearch);
    }


    @RequestMapping(method = RequestMethod.GET)
    public Teacher getStudent(@RequestParam Integer teacherId) throws Exception{
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isPresent()){
            Teacher teacher = teacherOptional.get();
            return teacher;
        }else {
            throw new Exception("No se encuentra el usuario");
        }
    }

}
