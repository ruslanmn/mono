package ru.kmeansfan.printer.learn.repository;

import ru.kmeansfan.printer.learn.dto.User;

import java.util.List;
import java.util.stream.Stream;

public class UserBlockingRepository implements BlockingRepository<User> {
    @Override
    public void save(User user) {

    }

    @Override
    public User findFirst() {
        return User.SKYLER;
    }

    @Override
    public Iterable<User> findAll() {
        return List.of(User.SKYLER, User.JESSE, User.WALTER, User.SAUL);
    }

    @Override
    public User findById(String id) {
        return Stream.of(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
            .filter(user -> user.getUsername().equals(id))
            .findFirst()
            .orElse(null);
    }
}
