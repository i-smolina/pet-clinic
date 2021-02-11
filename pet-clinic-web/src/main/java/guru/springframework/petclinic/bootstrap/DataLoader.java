package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.*;
import guru.springframework.petclinic.services.*;
import guru.springframework.petclinic.services.map.OwnerServiceMap;
import guru.springframework.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService  visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Собака");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Кошка");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Рентген");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Хирургия");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Стоматология");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Иван");
        owner1.setLastName("Иванов");
        owner1.setSity("Москва");
        owner1.setAddress("Полевая 105 к5 кв 93");
        owner1.setTelephone("84951234569");

        Pet ivanovPet = new Pet();
        ivanovPet.setName("Шарик");
        ivanovPet.setPetType(savedDogPetType);
        ivanovPet.setBirthDate(LocalDate.now());
        ivanovPet.setOwner(owner1);
        owner1.getPets().add(ivanovPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Александр");
        owner2.setLastName("Пушкин");
        owner2.setSity("Санкт-Петербург");
        owner2.setAddress("Парашутная д.100 кв 90");
        owner2.setTelephone("88121234578");

        Pet pushkinPet = new Pet();
        pushkinPet.setName("Мурка");
        pushkinPet.setPetType(savedCatPetType);
        pushkinPet.setBirthDate(LocalDate.now());
        pushkinPet.setOwner(owner2);
        owner2.getPets().add(pushkinPet);

        ownerService.save(owner2);

        Visit visitCat = new Visit();
        visitCat.setPet(pushkinPet);
        visitCat.setDate(LocalDate.now());
        visitCat.setDescription("Чихающая кошка");
        visitService.save(visitCat);

        System.out.println("Loaded owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Агния");
        vet1.setLastName("Барто");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Анастасия");
        vet2.setLastName("Орлова");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }
}
