import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@Disabled("Checked main method what it is less 22 second")
class MainTest {

    @Test
    void whenMainLess22Sec() {
        Assertions.assertTimeout(Duration.ofSeconds(22), () -> Main.main(new String[]{}));
    }
}
