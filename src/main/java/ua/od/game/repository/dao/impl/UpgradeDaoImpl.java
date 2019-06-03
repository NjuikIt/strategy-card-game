package ua.od.game.repository.dao.impl;

import ua.od.game.model.BuildingSetEntity;
import ua.od.game.model.ResourceSetEntity;
import ua.od.game.model.UpgradeEntity;
import ua.od.game.repository.dao.UpgradeDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UpgradeDaoImpl implements UpgradeDao {
    private static final String GET_UPGRADE_LIST_QUERY =
                    "select\n" +
                    "    u.id u_id,\n" +
                    "    u.name,\n" +
                    "    u.description,\n" +
                    "    u.default_number,\n" +
                    "    rs.id r_id,\n" +
                    "    rs.set_id resource_set_id,\n" +
                    "    rs.amount resource_amount,\n" +
                    "    rs.resource_id,\n" +
                    "    bs.id b_id,\n" +
                    "    bs.set_id building_set_id,\n" +
                    "    bs.amount building_amount,\n" +
                    "    bs.building_id\n" +
                    "from Upgrade u\n" +
                    "join Resource_Set rs on u.resource_set_id = rs.set_id\n" +
                    "join Building_Set bs on u.building_set_id = bs.set_id";
    @Override
    public List<UpgradeEntity> getAllUpgradeList() {
        return SqlHelper.prepareStatement(GET_UPGRADE_LIST_QUERY, statement -> {
            ResultSet resultSet = statement.executeQuery();
            List<UpgradeEntity> upgrades = new LinkedList<>();
            while (resultSet.next()) {
                UpgradeEntity ue = new UpgradeEntity() {{
                    setId(resultSet.getInt("id"));
                    setName(resultSet.getString("name"));
                    setDescription(resultSet.getString("description"));
                    setDefaultNumber(resultSet.getInt("default_number"));
                }};
                upgrades.add(ue);
                fetchSetLists(resultSet, ue);
            }
            return upgrades;
        });
    }

    public List<BuildingSetEntity> fetchSetLists(ResultSet resultSet, UpgradeEntity upgradeEntity) throws SQLException {
        if(upgradeEntity.getBuildingSetList() == null) {
            upgradeEntity.setBuildingSetList(new LinkedList<>());
        }

        if(upgradeEntity.getResourceSetList() == null) {
            upgradeEntity.setResourceSetList(new LinkedList<>());
        }

        int setId = resultSet.getInt("building_set_id");
        BuildingSetEntity bse;
        List<BuildingSetEntity> buildingSetEntity = new LinkedList();

        do {
            bse = new BuildingSetEntity();
            bse.setId(resultSet.getInt("b_id"));
            bse.setAmount(resultSet.getFloat("building_amount"));
            bse.setSetId(resultSet.getInt("building_set_id"));
            bse.setBuildingId(resultSet.getInt("building_id"));
            buildingSetEntity.add(bse);
        } while (resultSet.next() && (setId == bse.getSetId()));
        //code list insertion
        return buildingSetEntity;
    }

    /*public List<ResourceSetEntity> fetchResourceSetList(ResultSet resultSet) throws  SQLException{
        int setId = resultSet.getInt("building_set_id");
        ResourceSetEntity rse;
        List<ResourceSetEntity> resourceSetEntity = new LinkedList();
        do {
            rse = new ResourceSetEntity();
            rse.setId(resultSet.getInt("b_id"));
            rse.setAmount(resultSet.getFloat("building_amount"));
            rse.setSetId(resultSet.getInt("building_set_id"));
            rse.setResourceId(resultSet.getInt("building_id"));
            resourceSetEntity.add(rse);
        } while (resultSet.next() && (setId == rse.getSetId()));
        return resourceSetEntity;
    }*/
    /*setBuildingSetList(fetchBuildingSetList(resultSet));
                    setResourceSetList(fetchResourceSetList(resultSet));*/
}
