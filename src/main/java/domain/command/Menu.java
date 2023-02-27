package domain.command;

import java.util.Arrays;

public enum Menu {

    LIST,
    HASH,
    ZLIB,
    QUIT;

    public boolean isNotQuit() {
        return this != QUIT;
    }
    public static boolean isQuit(String menuName) {
        return menuName.equalsIgnoreCase(QUIT.name());
    }

    public static Menu of (String menuName) {
        return Arrays.stream(values())
            .filter(menu -> menuName.equalsIgnoreCase(menu.name()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 메뉴 명령어가 아닙니다."));
    }
}