package cz.muni.fi.pa165.vozovypark.DAOTests;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andrej Bauer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testConf/dao.xml", "classpath:/testConf/dataSource.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public abstract class AbstractDAOTest {
}
