package context;

import dao.Dao;

public interface Factory<T> {

    /**
     * Return instance by id, from DataBase for example.
     *
     * @param id instance id
     * @return instance
     */
    T getById(int id, Dao<T> dao);
}
