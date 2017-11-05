package dao;

import java.io.Serializable;

public interface Dao<T, PK extends Serializable> {
    /**
     * get object from source by id.
     *
     * @param id object id
     * @return object what was read
     */
    T read(int id);

    /**
     * Update object in source.
     *
     * @param t object for update
     */
    void update(T t);

    /**
     * Insert object in data source.
     * @param t object for insertion
     * @return data source object id
     */
    PK create(T t);

    /**
     * remove object from table by id.
     *
     * @param id object id
     */
    void delete(int id);
}
