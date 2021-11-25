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
        if(teachers.size()>0){
            LOGGER.info("Se muestra los datos de todos los profesores");
        }else {
            LOGGER.warn("No se encontro ningun dato de los profesores");
        }
        return teachers;
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public Teacher updateTeacher(@RequestParam Integer teacherId, @RequestBody Teacher teacher){
        Teacher teacherSearch = teacherRepository.findById(teacherId).get();
        teacherSearch.setName(teacher.getName());
        teacherSearch.setSubject(teacher.getSubject());
        LOGGER.info("Se actualiza los datos del profesor");
        return teacherRepository.save(teacherSearch);
    }


    @RequestMapping(method = RequestMethod.GET)
    public Teacher getStudent(@RequestParam Integer teacherId) throws Exception{
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isPresent()){
            LOGGER.info("Se encontro al profesor");
            Teacher teacher = teacherOptional.get();
            return teacher;
        }else {
            LOGGER.error("no se encontro al profesor");
            throw new Exception("No se encuentra el usuario");
        }
    }

}
