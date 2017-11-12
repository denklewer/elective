package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T, PK extends Serializable> {
    /**
     * get object from source by id.
     *
     * @param id object id
     * @return object what was read
     */
    T read(PK id);

    /**
     * Update object in source.
     *
     * @param t object for update
     */
    void update(T t);// T

    /**
     * Insert object in data source.
     *
     * @param t object for insertion
     * @return data source object id
     */
    PK create(T t);// T

    /**
     * remove object from table by id.
     *
     * @param id object id
     */
    void delete(PK id);

    /**
     * Get list of available objects from dao.
     *
     * @return list of objects
     */
    List<T> list();
}
