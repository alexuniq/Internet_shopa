package application.bd;

import application.items.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class ProductListDatabase implements Database {

    private final List<Product> db = new ArrayList<>();
    private long id;

    @Override
    public long add(String name, String description, double price) {
        id++;
        db.add(new Product(id, name, description, price));
        return id;
    }

    @Override
    public boolean delete(long id) {
        db.removeIf(x -> x.getId() == id);
        return false;
    }

    @Override
    public void delete(Predicate<Product> predicate) {
        db.removeIf(predicate);
    }

    @Override
    public void clear() {
        db.clear();
    }

    @Override
    public Optional<Product> getById(long id) {
        return db.stream()
                .filter(t -> t.getId() == id)
                .findAny();
    }

    @Override
    public List<Product> filter(Predicate<Product> predicate) {
        return db.stream()
                .filter(predicate)
                .collect(toList());
    }

    @Override
    public List<Product> getList() {
        return db;
    }
}