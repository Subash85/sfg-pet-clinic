package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    //Automatically @Autowired because its a constructor
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {


       int count = petTypeService.findAll().size();

       if(count == 0) {
           loadData();
       }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality saveRadiology = specialityService.save(radiology);

        Speciality surgeon = new Speciality();
        surgeon.setDescription("Surgeon");
        Speciality saveSurgeon = specialityService.save(surgeon);

        Speciality dietitian = new Speciality();
        dietitian.setDescription("dietitian");
        Speciality saveDietitian = specialityService.save(dietitian);


        Owner owner1 = new Owner();
        owner1.setFirstName("Subash");
        owner1.setLastName("Janakiraman");
        owner1.setAddress("2074 W Fairwoo");
        owner1.setCity("Taylorsvile");
        owner1.setTelephone("123456-12");

        Pet mikePet = new Pet();
        mikePet.setPetType(saveDogType);
        mikePet.setOwner(owner1);
        mikePet.setBirthday(LocalDate.now());
        mikePet.setName("Rudy");
        owner1.getPets().add(mikePet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Owner2");
        owner2.setLastName("owner2 lastname");
        owner2.setAddress("20789 Fairwoo");
        owner2.setCity("Salt Lake City");
        owner2.setTelephone("2343456-12");

        Pet owner2Pet = new Pet();
        owner2Pet.setPetType(saveCatType);
        owner2Pet.setOwner(owner2);
        owner2Pet.setBirthday(LocalDate.now());
        owner2Pet.setName("Rossy");
        owner2.getPets().add(owner2Pet)
;
        ownerService.save(owner2);

        System.out.println("Loading Owners ....");


        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Alex");
        vet1.getSpecialities().add(saveRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Max");
        vet2.setLastName("Alex");
        vet2.getSpecialities().add(saveSurgeon);

        vetService.save(vet2);

        System.out.println("Loading wets ....");
    }
}
