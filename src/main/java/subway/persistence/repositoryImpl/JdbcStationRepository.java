package subway.persistence.repositoryImpl;

import org.springframework.stereotype.Repository;
import subway.application.repository.StationRepository;
import subway.entity.LineEntity;
import subway.entity.SectionEntity;
import subway.entity.StationEntity;
import subway.persistence.dao.LineDao;
import subway.persistence.dao.SectionDao;
import subway.persistence.dao.StationDao;

import java.util.List;

@Repository
public class JdbcStationRepository implements StationRepository {

    private final LineDao lineDao;
    private final SectionDao sectionDao;
    private final StationDao stationDao;

    public JdbcStationRepository(final LineDao lineDao, final SectionDao sectionDao, final StationDao stationDao) {
        this.lineDao = lineDao;
        this.sectionDao = sectionDao;
        this.stationDao = stationDao;
    }

    @Override
    public LineEntity findLineById(final Long id) {
        return lineDao.findById(id);
    }

    @Override
    public void deleteAllSectionByLineId(Long lineId) {
        sectionDao.deleteAllByLineId(lineId);
    }

    @Override
    public void saveAllSection(final List<SectionEntity> sectionEntities) {
        sectionDao.insertAll(sectionEntities);
    }

    @Override
    public StationEntity saveStation(final StationEntity station) {
        return stationDao.insert(station);
    }

    @Override
    public List<StationEntity> findAllStation() {
        return stationDao.findAll();
    }

    @Override
    public StationEntity findStationByName(final String stationName) {
        return stationDao.findByName(stationName);
    }

    @Override
    public List<SectionEntity> findAllSectionByLineId(final Long lineId) {
        return sectionDao.findAllByLineId(lineId);
    }

    @Override
    public StationEntity findStationById(final Long id) {
        return stationDao.findById(id);
    }

    @Override
    public void deleteAllStation() {
        stationDao.deleteAll();
    }

    @Override
    public void deleteStationById(final Long id) {
        stationDao.deleteById(id);
    }

    @Override
    public void updateStation(final StationEntity stationEntity) {
        stationDao.update(stationEntity);
    }
}
