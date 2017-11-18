package epam_team1.service.services;

import epam_team1.service.dao.StudentScoreJdbcDaoImpl;
import epam_team1.service.model.StudentScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StudentScoreManagerTest {

    @InjectMocks
    StudentScoreManagerImpl studentScoreManagerInjectMock;

    @Mock
    StudentScoreJdbcDaoImpl studentScoreJdbcDaoMock;

    @Mock
    StudentScore studentScoreMock;

    @Test
    public void read() throws Exception {
        studentScoreManagerInjectMock.read(1,1);
        verify(studentScoreJdbcDaoMock).read(1,1);
    }

    @Test
    public void update() throws Exception {
        studentScoreManagerInjectMock.update(studentScoreMock);
        verify(studentScoreJdbcDaoMock).update(studentScoreMock);
    }

    @Test
    public void create() throws Exception {
        studentScoreManagerInjectMock.create(studentScoreMock);
        verify(studentScoreJdbcDaoMock).create(studentScoreMock);
    }

    @Test
    public void delete() throws Exception {
        studentScoreManagerInjectMock.delete(1,1);
        verify(studentScoreJdbcDaoMock).delete(1,1);
    }

    @Test
    public void list() throws Exception {
        studentScoreManagerInjectMock.list(7);
        verify(studentScoreJdbcDaoMock).list(7);
    }


}