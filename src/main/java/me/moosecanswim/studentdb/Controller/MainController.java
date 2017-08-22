package me.moosecanswim.studentdb.Controller;

import me.moosecanswim.studentdb.Model.Student;
import me.moosecanswim.studentdb.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class MainController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(value="/", method=GET)
    public String inputStudent(Model toSend){

        toSend.addAttribute("aStudent", new Student());
        return "formStudent";
    }
    @RequestMapping(value="/", method=POST)
    public String acceptStudent(Model toSend,@Valid @ModelAttribute("aStudent") Student aStudent, BindingResult result){
        if(result.hasErrors()){
            return "formStudent";
        }
        studentRepository.save(aStudent);
        return "redirect:/";
    }
}
