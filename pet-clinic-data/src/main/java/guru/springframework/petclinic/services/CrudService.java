package guru.springframework.petclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {
    T findById(Long ID);

    Set<T> findAll();

    T save(T oblect);

    void delete(T object);

    void deleteById(Long ID);
}
