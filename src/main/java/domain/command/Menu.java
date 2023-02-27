package domain.command;

import java.util.Arrays;

public enum Menu {

    LIST;

    public static Menu of (String menuName) {
        return Arrays.stream(values())
            .filter(menu -> menuName.equals(menu.name().toLowerCase()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 메뉴 명령어가 아닙니다."));
    }
}