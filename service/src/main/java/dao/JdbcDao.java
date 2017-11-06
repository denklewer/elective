package dao;

import org.springframework.jdbc.core.JdbcTemplate;

public interface JdbcDao<T> extends Dao<T,Integer> {

    /**
     * Set JDBCTemplate for dataBase.
     *
     * @param jdbcTemplate JDBCTemplate for use
     */
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}
