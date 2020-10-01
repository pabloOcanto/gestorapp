import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ar.com.gestor.stats.controller.StatsControllerTest;
import ar.com.gestor.stats.service.StatsServiceTest;
import ar.com.gestor.stats.util.FilterUtilTest;
import ar.com.gestor.stats.util.LocationUtilTest;
import ar.com.gestor.stats.util.ParserUtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   StatsControllerTest.class,
   StatsServiceTest.class,
   FilterUtilTest.class,
   LocationUtilTest.class,
   ParserUtilTest.class
})

public class SuiteTest {

}
