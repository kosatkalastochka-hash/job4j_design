package ru.job4j.question;
import java.util.Iterator;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0, changed = 0, deleted = 0;
        Iterator<User> currentIterator = current.iterator();
        Iterator<User> previosIterator = previous.iterator();
        while (currentIterator.hasNext()) {
            User currentUser = currentIterator.next();
            if (!previous.contains(currentUser)) {
                added++;
            } else {
                for (User user : previous) {
                    if (currentUser.equals(user) && !currentUser.getName().equals(user.getName())) {
                        changed++;
                    }
                }
            }
            while (previosIterator.hasNext()) {
                User previousUser = previosIterator.next();
                if (!current.contains(previousUser)) {
                    deleted++;
                }
            }
        }
        return new Info(added, changed, deleted);
    }
}