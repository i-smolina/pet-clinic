package guru.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

public interface Owner extends CrudRepository<Owner, Long> {
}
