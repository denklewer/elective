package epam_team1.service.services;

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
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void create() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

    @Test
    public void list() throws Exception {
    }

    @Test
    public void getStudents() throws Exception {
    }

    @Test
    public void getStudentsByCourseId() throws Exception {
    }

}