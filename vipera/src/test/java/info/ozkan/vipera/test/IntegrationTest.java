package info.ozkan.vipera.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Entegrasyon test sınıflarının olması gereken ortak özelliklerini barındırır
 * 
 * @author Ömer Özkan
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml",
        "classpath:WEB-INF/securityConfig.xml" })
@Transactional
public abstract class IntegrationTest {

}
