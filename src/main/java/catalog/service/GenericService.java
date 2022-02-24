package catalog.service;

public interface GenericService<T> {
    T save(T t);

    T getById(Long id);

    void update(Long id, T t);

    void deleteById(Long id);
}
