package dao;

public interface Dao<T> {
    /**
     * Load object from source by id.
     *
     * @param id object id
     * @return object what was read
     */
    T loadById(int id);

    /**
     * Update object in source.
     *
     * @param t object for update
     */
    void update(T t);

    /**
     * Put object on a table :).
     *
     * @param t object for put
     */
    void put(T t);

    /**
     * remove object from table by id.
     *
     * @param id object id
     */
    void removeById(int id);
}
