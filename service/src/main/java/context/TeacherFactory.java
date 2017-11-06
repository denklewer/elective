package context;

import dao.Dao;
import dao.JdbcDao;

public class TeacherFactory implements Factory<Teacher> {

    @Override
    public Teacher getById(int id, Dao<Teacher, Integer> dao) {
        return dao.read(id);
    }

    /**
     * Get new Teacher instance.
     * @param id teacher id
     * @param firtsName first name
     * @param lastName last name
     * @return new instance of class Teacher
     */
    public Teacher newInstance(int id, String firtsName, String lastName) {
        return new Teacher(id, firtsName, lastName);
    }

}
