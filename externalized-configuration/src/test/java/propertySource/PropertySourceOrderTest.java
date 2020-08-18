package propertySource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(
//        properties = "user.id=9",
        locations = "classpath:META-INF/default.properties")
@SpringBootTest(
//        properties = "user.id=8",
        classes = PropertySourceOrderTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Configuration
@PropertySource(name = "test-property-source", value = "classpath:META-INF/test.properties")
public class PropertySourceOrderTest {

    @Value("${user.id}")
    private Long userId;

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    public void testEnvironment() {
        Assert.assertSame(context.getEnvironment(), environment);
    }

    @Test
    public void testUserId() {
        Assert.assertEquals(new Long(7), userId);

        /**
         * 1. @TestPropertySource.properties
         * 2. @SpringBootTest.properties
         * 3. @TestPropertySource.locations
         */
    }

    @Test
    public void testPropertySources() {
        environment.getPropertySources().forEach(System.out::println);
    }

}
