package ua.od.game.repository.dao.impl;

import org.junit.Test;
import ua.od.game.model.UpgradeEntity;
import ua.od.game.repository.dao.DbTest;
import ua.od.game.repository.dao.UpgradeDao;

import java.util.List;

import static javax.management.Query.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class UpgradeDaoImplTest extends DbTest {
    private UpgradeDao upgradeDao = new UpgradeDaoImpl();
    @Test
    public void testGetAllUpgrades(){
        List<UpgradeEntity> upgradeEntitiesList = upgradeDao.getAllUpgradeList();
        //upgradeEntitiesList.forEach(item -> System.out.println(item.toString()));
        assertThat(upgradeEntitiesList, is(notNullValue()));
        assertThat(upgradeEntitiesList.size(), is(3));
    }
}
