package guru.springframework.petclinic.services.springdatajpa;

import guru.springframework.petclinic.model.Speciality;
import guru.springframework.petclinic.repositories.SpecialtyRepository;
import guru.springframework.petclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtyService implements SpecialityService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Speciality findById(Long id) {
        Optional<Speciality> optionalSpeciality = specialtyRepository.findById(id);
        if (optionalSpeciality.isPresent()) return optionalSpeciality.get();
        else return null;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialtyRepository.findAll().forEach(specialities::add);
        return  specialities;
    }

    @Override
    public Speciality save(Speciality object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
