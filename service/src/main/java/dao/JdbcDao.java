package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;

public interface JdbcDao<T, PK extends Serializable> extends Dao<T,PK> {

    /**
     * Set JDBCTemplate for dataBase.
     *
     * @param jdbcTemplate JDBCTemplate for use
     */
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}
