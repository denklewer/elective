package controllers;

import model.Course;
import org.springframework.web.bind.annotation.*;
import services.CourseManager;
import services.CourseManagerImpl;

import java.util.List;

@RestController
// мапим наш REST на /myservice
@RequestMapping(value = "/myservice")
public class electiveController {

    // этот метод будет принимать student_id методом GET и на его основе
    // отвечать выдавать список курсов на которые записан студент
    @RequestMapping(value= "/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public Course getListCourse() {
        return null;
    }

    // этот метод будет принимать student_id, course_id методом GET и на его основе
    // отвечать выдавать оценку студента
    @RequestMapping(value = "/{student_id}/{course_id}", method = RequestMethod.GET)
    @ResponseBody
    public int getStudentScore(@PathVariable long id) {
        return как это делать вообще???
    }

    // этот метод будет принимать Объект MyDataObject и отдавать его клиенту
    // обычно метод PUT используют для сохранения либо для обновления объекта
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public MyDataObject putMyData(@RequestBody MyDataObject md) {
        return md;
    }

    // этот метод будет методом POST отдавать объект MyDataObject
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public MyDataObject postMyData() {
        return new MyDataObject(Calendar.getInstance(), "это ответ метода POST!");
    }

    // этот метод будет принимать время методом DELETE
    // и на его основе можно удалит объект
    @RequestMapping(value= "/{time}", method = RequestMethod.DELETE)
    @ResponseBody
    public MyDataObject deleteMyData(@PathVariable long time) {
        return new MyDataObject(Calendar.getInstance(), "Это ответ метода DELETE!");
    }
}
