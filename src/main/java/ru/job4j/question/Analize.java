package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0, changed = 0, deleted = 0;
        Map<User, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user, user.getName());
        }
        for (User user : current) {
            String username = map.remove(user);
            if (username == null) {
                added++;
            } else if (!username.equals(user.getName())) {
                changed++;
            }
        }
        deleted = map.size();
        return new Info(added, changed, deleted);
    }
}
