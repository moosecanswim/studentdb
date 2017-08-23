package me.moosecanswim.studentdb.Controller;

import me.moosecanswim.studentdb.Model.Student;
import me.moosecanswim.studentdb.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/showAll")
    public String showAll(Model toSend){
        toSend.addAttribute("studentList", studentRepository.findAll());
        return "showAll";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        studentRepository.delete(id);
        return "redirect:/showAll";
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model toSend){
        toSend.addAttribute("aStudent",studentRepository.findOne(id));

        return "formStudent";
    }
    @RequestMapping("/index")
    public String newPage(Model toSend){
        toSend.addAttribute("aStudent", new Student());
        return "index";
    }
}
