import io.davis.Application
import io.davis.producer.SpringBootKafkaProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by Davis on 17/2/13.
 */
@SpringBootTest
@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
class ApplicationTest extends Specification {

    @Autowired
    SpringBootKafkaProducer springBootKafkaProducer;

    def "test 1 : "() {
        when:
        springBootKafkaProducer.send("二师兄");

        then:
        true
    }
}
