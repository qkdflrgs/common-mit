package domain.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    void createSuccess() {
        // given
        String input1 = "mit list /Desktop/masters/cs/cs16";
        String input2 = "quit";

        // when

        // then
        assertDoesNotThrow(() -> new Command(input1));
        assertDoesNotThrow(() -> new Command(input2));
    }

    @Test
    @DisplayName("명령어 형식의 길이가 3개가 아닐경우")
    void createFail1() {
        // given
        String input = "mit list";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new Command(input));
    }

    @Test
    @DisplayName("첫번째 명령어가 mit가 아닌경우")
    void createFail2() {
        // given
        String input = "git list /Desktop/masters/cs/cs16";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new Command(input));
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 명령어인 경우")
    void createFail3() {
        // given
        String input = "mit empty /Desktop/masters/cs/cs16";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new Command(input));
    }

    @Test
    @DisplayName("존재하지 않는 디렉토리일 경우")
    void createFail4() {
        // given
        String input = "mit list /Desktop1/masters/cs/cs16";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new Command(input));
    }
}