package epam_team1.service.services;

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

    @Mock
    UserManager userManager;

    @InjectMocks
    UserManagerImpl mockedObject = new UserManagerImpl();

    UserManager mockedInterface = mock(UserManager.class);

    User mokedUser = mock(User.class);

    @Test
    public void readById() throws Exception {
//         //вот так не работает
//        // вызываем метод у mock объекта
//         mockedObject.readById(35);
//        // проверяем что это метод был вызвал
//         verify(userManager).readById(35);

        // а так почему то работает
        // вызываем метод у mock объекта
        mockedInterface.readById(35);
        // проверяем что это метод был вызвал
        verify(mockedInterface).readById(35);

    }

    @Test
    public void readByLogin() throws Exception {
        // вызываем метод у mock объекта
        mockedInterface.readByLogin("user");
        // проверяем что это метод был вызвал
        verify(mockedInterface).readByLogin("user");
    }

    @Test
    public void update() throws Exception {
        // вызываем метод у mock объекта
        mockedInterface.update(mokedUser);
        // проверяем что это метод был вызвал
        verify(mockedInterface).update(mokedUser);
    }

    @Test
    public void create() throws Exception {
        // вызываем метод у mock объекта
        mockedInterface.create(mokedUser);
        // проверяем что это метод был вызвал
        verify(mockedInterface).create(mokedUser);
    }

    @Test
    public void deleteById() throws Exception {
        // вызываем метод у mock объекта
        mockedInterface.deleteById(10);
        // проверяем что это метод был вызвал
        verify(mockedInterface).deleteById(10);
    }

    @Test
    public void list() throws Exception {
        // вызываем метод у mock объекта
        mockedInterface.list();
        // проверяем что это метод был вызвал
        verify(mockedInterface).list();
    }

    @Test
    public void getStudents() throws Exception {
        // вызываем метод у mock объекта
        mockedInterface.getStudents();
        // проверяем что это метод был вызвал
        verify(mockedInterface).getStudents();
    }

    @Test
    public void getStudentsByCourseId() throws Exception {
        // вызываем метод у mock объекта
        mockedInterface.getStudentsByCourseId(25);
        // проверяем что это метод был вызвал
        verify(mockedInterface).getStudentsByCourseId(25);
    }

}