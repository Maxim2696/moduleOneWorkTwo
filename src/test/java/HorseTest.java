
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    Horse horse;
    @BeforeEach
    void init() {

    }

    @Test
    void whenHorseNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> horse = new Horse(null, 0, 0));
    }

    @Test
    void whenHorseNameIsNullCheckMessage() {
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> horse = new Horse(null, 0, 0));
        assertEquals("Name cannot be null.", throwable.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\f", "\r"}) //"0x0B"
    void whenHorseNameInvalid(String name) {
        assertThrows(IllegalArgumentException.class, () -> horse = new Horse(name, 0, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\f", "\r"}) //"0x0B"
    void whenHorseNameInvalidCheckMessage(String name) {
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> horse = new Horse(name, 0, 0));
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @Test
    void whenHorseSpeedNegative() {

    }
}
