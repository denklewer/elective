package epam_team1.web.controllers;


import epam_team1.service.appconfig.AppConfig;
import epam_team1.service.model.User;
import epam_team1.service.services.CourseManager;
import epam_team1.service.services.StudentScoreManager;
import epam_team1.service.services.UserManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"elective\\service\\src\\main\\java\\epam_team1\\service\\appconfig\\AppConfig.java," +
 //       "elective\\web\\src\\main\\java\\epam_team1\\web\\WebApplication.java"})
//@ContextConfiguration({"elective\\web\\src\\main\\java\\epam_team1\\web\\controllers\\ElectiveController.java"});
@ContextConfiguration(locations = {
        "classpath*:spring/applicationContext.xml",
        "classpath*:spring/applicationContext-jpa.xml",
        "classpath*:spring/applicationContext-security.xml" })
//@ContextConfiguration({"classpath:test-context.xml", "classpath:mockTestBeans.xml"})
public class ElectiveControllerTest {

    @InjectMocks
    private ElectiveController electiveController;

    @Mock
    UserManager userManagerMock;

    @Mock
    CourseManager courseManagerMock;

    @Mock
    StudentScoreManager studentScoreManagerMock;

    private MockMvc mockMvc;


    @Before
    public void setup() {

        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(electiveController)
                .build();
    }

    @Test
    public void getCurrent() throws Exception {
    }

    @Test
    public void getUsers() throws Exception {
    }

    @Test
    public void getUser() throws Exception {
        User user = User.newBuilder()
                .setId(1)
                .setFirstName("Elena")
                .setLastName("Ivanova")
                .setLogin("e_ivanova")
                .setEmail("lena_ivanova95@gmail.com")
                .setPassword("firstTestController")
                .build();

        when(userManagerMock.readById(1)).thenReturn(user);
        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void createUser() throws Exception {
    }

    @Test
    public void deleteUser() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void getCourses() throws Exception {
    }

    @Test
    public void getCourses1() throws Exception {
    }

    @Test
    public void getCourse() throws Exception {
    }

    @Test
    public void getStudentsByCourseId() throws Exception {
    }

    @Test
    public void getCoursesExceptMine() throws Exception {
    }

    @Test
    public void getCoursesWhichITeach() throws Exception {
    }

    @Test
    public void createCourse() throws Exception {
    }

    @Test
    public void deleteCourse() throws Exception {
    }

    @Test
    public void updateCourse() throws Exception {
    }

    @Test
    public void getScore() throws Exception {
    }

    @Test
    public void subscribe() throws Exception {
    }

    @Test
    public void setScore() throws Exception {
    }

    @Test
    public void unsubscribe() throws Exception {
    }

}