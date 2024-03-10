package ru.kmeansfan.printer.learn.repository;

import ru.kmeansfan.printer.learn.dto.User;

public interface BlockingRepository<T> {

    void save(User user);

    T findFirst();

    Iterable<T> findAll();

    T findById(String id);
}
