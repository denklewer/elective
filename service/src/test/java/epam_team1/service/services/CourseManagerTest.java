package epam_team1.service.services;

import epam_team1.service.dao.CourseDao;
import epam_team1.service.dao.CourseJdbcDaoImpl;
import epam_team1.service.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseManagerTest {

    @InjectMocks
    CourseManagerImpl courseManagerImplInjectMock;

    @Mock
    CourseJdbcDaoImpl courseJdbcDaoMock;

    @Mock
    Course courseMock;

    @Test
    public void readById() throws Exception {
        courseManagerImplInjectMock.readById(15);
        verify(courseJdbcDaoMock).read(15);
    }

    @Test
    public void update() throws Exception {
        courseManagerImplInjectMock.update(courseMock);
        verify(courseJdbcDaoMock).update(courseMock);
    }

    @Test
    public void create() throws Exception {
        courseManagerImplInjectMock.create(courseMock);
        verify(courseJdbcDaoMock).create(courseMock);
    }

    @Test
    public void deleteById() throws Exception {
        courseManagerImplInjectMock.deleteById(13);
        verify(courseJdbcDaoMock).delete(13);
    }

    @Test
    public void list() throws Exception {
        courseManagerImplInjectMock.list();
        verify(courseJdbcDaoMock).list();
    }

    @Test
    public void listByStudentId() throws Exception {
        courseManagerImplInjectMock.listByStudentId(2);
        verify(courseJdbcDaoMock).listByStudentId(2);
    }

    @Test
    public void listByStudentIdExceptMine() throws Exception {
        courseManagerImplInjectMock.listByStudentIdExceptMine(4);
        verify(courseJdbcDaoMock).listByStudentIdExceptMine(4);
    }

    @Test
    public void listByInstructorId() throws Exception {
        courseManagerImplInjectMock.listByInstructorId(6);
        verify(courseJdbcDaoMock).listByInstructorId(6);
    }

}