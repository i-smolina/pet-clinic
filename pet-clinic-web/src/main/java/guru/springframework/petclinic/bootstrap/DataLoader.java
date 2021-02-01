package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.model.Pet;
import guru.springframework.petclinic.model.PetType;
import guru.springframework.petclinic.model.Vet;
import guru.springframework.petclinic.services.OwnerService;
import guru.springframework.petclinic.services.PetTypeService;
import guru.springframework.petclinic.services.VetService;
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

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Собака");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Кошка");
        PetType savedCatPetType = petTypeService.save(cat);

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

        System.out.println("Loaded owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Агния");
        vet1.setLastName("Барто");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Анастасия");
        vet2.setLastName("Орлова");
        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }
}
