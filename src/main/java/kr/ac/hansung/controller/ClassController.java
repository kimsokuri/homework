package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.model.Course;
import kr.ac.hansung.service.CourseService;

@Controller
public class ClassController {
	
	@Autowired
	private CourseService cs;
	
	@RequestMapping("/semester")
	public String showSemester(Model model) {
		List<Course> courses = cs.getSemesterCurrent();
		model.addAttribute("semeCourses", courses);
		
		return "semester";
	}
	
	@RequestMapping("/detailSemester")
	public String showDetailSemester(@RequestParam(value = "year") int year, @RequestParam(value = "semester") int semester , Model model) {
		List<Course> courses = cs.getDetailSemesterCurrent(year, semester);
		model.addAttribute("detailSemeCourses", courses);
		model.addAttribute("year", year);
		model.addAttribute("semester", semester);
		return "detailSemester";
	}
	
	@RequestMapping("/division")
	public String showDivision(Model model) {
		List<Course> courses = cs.getDivisionCurrent();
		model.addAttribute("diviCourses", courses);
		
		return "division";
	}
	
	@RequestMapping("/lookup")
	public String showRegister(Model model) {
		List<Course> courses = cs.getCurrent();
		model.addAttribute("courses", courses);
		return "lookup";
	}
	
	@RequestMapping("/dodelete")
	public String doDelete(Model model, @RequestParam(value = "code") String code) {
		cs.delete(code);
		
		List<Course> courses = cs.getCurrent();
		model.addAttribute("courses", courses);
		
		return "lookup";
	}

	
	@RequestMapping("/registerCourse")
	public String registerCourse(Model model) {
		model.addAttribute("course", new Course());
		return "registerCourse";
	}
	
	@RequestMapping("/doregister")
	public String doCreate(Model model, @Valid Course course, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("===Form data does not validated");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			
			return "registerCourse";
		}
		
		cs.insert(course);
		return "checkedCourse";
	}
}

