
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.only;

class HorseTest {
    Horse horse;
    @BeforeEach
    void init() {
        horse = new Horse("flash", 10, 20);
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
    @ValueSource(strings = {"", " ", "\t", "\n", "\f", "\r", "\u000B"})
    void whenHorseNameInvalidCheckMessage(String name) {
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> horse = new Horse(name, 0, 0));
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @Test
    void whenHorseSpeedNegative() {
        assertThrows(IllegalArgumentException.class, () -> horse = new Horse("ksd", -1, 0));
    }

    @Test
    void whenHorseSpeedNegativeCheckMessage() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> horse = new Horse("ksd", -1, 0));
        assertEquals("Speed cannot be negative.", throwable.getMessage());
    }

    @Test
    void whenHorseDistanceNegative() {
        assertThrows(IllegalArgumentException.class, () -> horse = new Horse("ksd", 5, -2));
    }

    @Test
    void whenHorseDistanceNegativeCheckMessage() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> horse = new Horse("ksd", 6, -1));
        assertEquals("Distance cannot be negative.", throwable.getMessage());
    }

    @Test
    void whenGetName() {
        assertEquals("flash", horse.getName());
    }

    @Test
    void whenGetSpeed() {
        assertEquals(10, horse.getSpeed());
    }

    @Test
    void whenGetDistanceBuilderWithThreeParam() {
        assertEquals(20, horse.getDistance());
    }

    @Test
    void whenGetDistanceBuilderWithTwoParam() {
        horse = new Horse("flash", 10);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void whenCheckGetRandomDouble() {
        try (MockedStatic<Horse> horseStatic = Mockito.mockStatic(Horse.class)){
            horse = new Horse("flash", 10, 10);
            horse.move();
            horseStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), only());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {2.0, 5.0})
    void whenCheckMove(double randDouble) {
        try (MockedStatic<Horse> horseStatic = Mockito.mockStatic(Horse.class)){
            horseStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randDouble);
            horse = new Horse("flash", 10, 10);
            horse.move();
            assertEquals(horse.getDistance(), 10 + 10 * randDouble);
        }
    }

}
