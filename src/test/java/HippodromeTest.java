import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    Hippodrome hippodrome;

    @Test
    void whenHippodromeParamNull() {
        assertThrows(IllegalArgumentException.class, () -> hippodrome = new Hippodrome(null));
    }

    @Test
    void whenHippodromeParamNullCheckedMessage() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> hippodrome = new Hippodrome(null));
        assertEquals("Horses cannot be null.", throwable.getMessage());
    }

    @Test
    void whenHippodromeParamEmpty() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> hippodrome = new Hippodrome(horses));
    }

    @Test
    void whenHippodromeParamEmptyCheckedMessage() {
        List<Horse> horses = new ArrayList<>();
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> hippodrome = new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", throwable.getMessage());
    }

    @Test
    void whenGetHorsesList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i));
        }
        hippodrome = new Hippodrome(horses);
        assertIterableEquals(horses, hippodrome.getHorses());
    }

    @Test
    void whenGetWinnerHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i));
        }

        hippodrome = new Hippodrome(horses);
        Horse maxDistance = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();

        assertSame(maxDistance, hippodrome.getWinner());
    }

    @Test
    void whenHorseOnHippodromeMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : hippodrome.getHorses()) {
            Mockito.verify(horse).move();
        }
    }
}
