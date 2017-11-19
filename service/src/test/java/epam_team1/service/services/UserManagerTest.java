package epam_team1.service.services;

import epam_team1.service.dao.UserDao;
import epam_team1.service.dao.UserJdbcDaoImpl;
import epam_team1.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {

//    @InjectMocks
//    UserManagerImpl mockedObject;
//
//    UserManager mockedInterface = mock(UserManager.class);
//
    @Mock
    User mokedUser;

    @InjectMocks
    UserManagerImpl userManagerInjectMock;

    @Mock
    UserJdbcDaoImpl userDaoMock;

    @Test
    public void readById() throws Exception {
//         //вот так не работает
//        // вызываем метод у mock объекта
//         mockedObject.readById(35);
//        // проверяем что это метод был вызвал
//         verify(userManager).readById(35);

        // а так почему то работает
        // вызываем метод у mock объекта
        userManagerInjectMock.readById(35);
        // проверяем что этот метод был вызвал в dao классе
        verify(userDaoMock).read(35);

    }

    @Test
    public void readByLogin() throws Exception {
        // вызываем метод у mock объекта
        userManagerInjectMock.readByLogin("user");
        // проверяем что этот метод был вызвал в dao классе
        verify(userDaoMock).readByLogin("user");
    }

    @Test
    public void update() throws Exception {
        // вызываем метод у mock объекта
        userManagerInjectMock.update(mokedUser);
        // проверяем что этот метод был вызвал в dao классе
        verify(userDaoMock).update(mokedUser);
    }

    @Test
    public void create() throws Exception {
        // вызываем метод у mock объекта
        userManagerInjectMock.create(mokedUser);
        // проверяем что этот метод был вызвал в dao классе
        verify(userDaoMock).create(mokedUser);
    }

    @Test
    public void deleteById() throws Exception {
        // вызываем метод у mock объекта
        userManagerInjectMock.deleteById(25);
        // проверяем что этот метод был вызвал в dao классе
        verify(userDaoMock).delete(25);
    }

    @Test
    public void list() throws Exception {
        // вызываем метод у mock объекта
        userManagerInjectMock.list();
        // проверяем что этот метод был вызвал в dao классе
        verify(userDaoMock).list();
    }

    @Test
    public void getStudents() throws Exception {
        // вызываем метод у mock объекта
        userManagerInjectMock.getStudents();
        // проверяем что этот метод был вызвал в dao классе
        verify(userDaoMock).getStudents();
    }

    @Test
    public void getStudentsByCourseId() throws Exception {
        // вызываем метод у mock объекта
        userManagerInjectMock.getStudentsByCourseId(25,1000, 0);
        // проверяем что этот метод был вызвал в dao классе
        verify(userDaoMock).getStudentsByCourseId(25,1000, 0);

    }

}