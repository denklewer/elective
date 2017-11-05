package context;

import dao.Dao;
import dao.JdbcDao;

public class TeacherFactory implements Factory<Teacher> {


    @Override
    public Teacher getById(int id, Dao<Teacher, Integer> dao) {
        return dao.read(id);
    }
}
