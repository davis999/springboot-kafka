import io.davis.receiver.Receiver
import io.davis.sender.Sender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.util.concurrent.TimeUnit

/**
 * Created by Davis on 17/2/13.
 */
@ContextConfiguration
@SpringBootTest
class ApplicationTest extends Specification {
    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    def "testReceiver"() {
        when:
        sender.sendMessage("helloworld.t", "Hello Spring Kafka!");

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        println(receiver.getLatch().getCount())

        then:
        true
    }
}
