package context;

import dao.Dao;

public class StudentFactory implements Factory<Student> {

    @Override
    public Student getById(int id, Dao<Student, Integer> dao) {
        return dao.read(id);
    }

    /**
     * Get new Student instance.
     * @param id student's id
     * @param firstName student firstName
     * @param lastName student lastName
     * @return new instance of class Student
     */
    public Student newInstance(int id, String firstName, String lastName) {
        return new Student(id, firstName, lastName);
    }
}
