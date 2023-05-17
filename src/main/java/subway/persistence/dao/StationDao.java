package subway.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import subway.entity.StationEntity;

import java.util.List;

@Repository
public class StationDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertAction;

    private RowMapper<StationEntity> rowMapper = (rs, rowNum) ->
            new StationEntity(
                    rs.getLong("id"),
                    rs.getString("name")
            );

    public StationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertAction = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("station")
                .usingGeneratedKeyColumns("id");
    }

    public StationEntity insert(StationEntity station) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(station);
        Long id = insertAction.executeAndReturnKey(params).longValue();
        return new StationEntity(id, station.getName());
    }

    public List<StationEntity> findAll() {
        String sql = "select * from STATION";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public StationEntity findById(Long id) {
        String sql = "select * from STATION where id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public StationEntity findByName(String name) {
        String sql = "select * from STATION where name = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, name);
    }

    public void update(StationEntity newStation) {
        String sql = "update STATION set name = ? where id = ?";
        jdbcTemplate.update(sql, new Object[]{newStation.getName(), newStation.getId()});
    }

    public void deleteById(Long id) {
        String sql = "delete from STATION where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAll() {
        String sql = "delete from STATION";
        jdbcTemplate.update(sql);
    }
}
