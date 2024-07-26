package com.example.APISkeleton.mappers.plantMapper;
import com.example.APISkeleton.persistance.entities.Device;
import com.example.APISkeleton.persistance.entities.Plant;
import com.example.APISkeleton.persistance.entities.PlantRecord;
import com.example.APISkeleton.persistance.entities.pivots.Category;
import com.example.APISkeleton.persistance.entities.pivots.Family;
import com.example.APISkeleton.persistance.entities.pivots.TypePlant;
import com.example.APISkeleton.persistance.repositories.pivotsRepositories.ICategoryRepository;
import com.example.APISkeleton.persistance.repositories.pivotsRepositories.IFamilyRepository;
import com.example.APISkeleton.persistance.repositories.pivotsRepositories.ITypeRepository;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRecordRequest;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRequest;
import com.example.APISkeleton.web.dtos.plants.response.PlantResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@Component
public class PlantMapper {

    private final ICategoryRepository categoryRepository;
    private final IFamilyRepository familyRepository;
    private final ITypeRepository typePlantRepository;

    @Autowired
    public PlantMapper(ICategoryRepository categoryRepository, IFamilyRepository familyRepository, ITypeRepository typePlantRepository) {
        this.categoryRepository = categoryRepository;
        this.familyRepository = familyRepository;
        this.typePlantRepository = typePlantRepository;
    }

    public Plant convertUpdatePlantRequestToPlant(CreatePlantRequest request,
                                                  List<String> listCategories,
                                                  List<String> listTypes,
                                                  List<String> listFamilies,
                                                  Plant plant) {


        Set<Category> categories = new HashSet<>();
        for (String categoryName : listCategories) {
            Optional<Category> category = categoryRepository.findByName(categoryName.trim());
            category.ifPresent(categories::add);
        }

        // Obtener los tipos existentes
        Set<TypePlant> types = new HashSet<>();
        for (String typeName : listTypes) {
            Optional<TypePlant> type = typePlantRepository.findByName(typeName.trim());
            type.ifPresent(types::add);
        }

        // Obtener las familias existentes
        Set<Family> families = new HashSet<>();
        for (String familyName : listFamilies) {
            Optional<Family> family = familyRepository.findByName(familyName.trim());
            family.ifPresent(families::add);
        }



        // Actualizar los campos del objeto Plant con los nuevos valores de la solicitud
        plant.setName(request.getName());
        plant.setNameScientific(request.getNameScientific());
        plant.setHumidityEarth(request.getHumidityEarth().orElse(plant.getHumidityEarth()));
        plant.setHumidityEnvironment(request.getHumidityEnvironment().orElse(plant.getHumidityEnvironment()));
        plant.setBrightness(request.getBrightness().orElse(plant.getBrightness()));
        plant.setAmbientTemperature(request.getAmbientTemperature().orElse(plant.getAmbientTemperature()));
        plant.setMq135(request.getMq135().orElse(plant.getMq135()));
        plant.setUrlImagePlant(request.getUrlImagePlant());
        plant.setCategories(categories);
        plant.setTypes(types);
        plant.setFamilies(families);

        // Guardar y devolver el objeto Plant actualizado
        return plant;
    }



    public Plant convertCreatePlantRequestToPlant(CreatePlantRequest request,
                                                  List<String> listCategories,
                                                  List<String> listTypes,
                                                  List<String> listFamilies) {


        Set<Category> categories = new HashSet<>();
        for (String categoryName : listCategories) {
            Optional<Category> category = categoryRepository.findByName(categoryName.trim());
            category.ifPresent(categories::add);
        }

        Set<TypePlant> types = new HashSet<>();
        for (String typeName : listTypes) {
            Optional<TypePlant> type = typePlantRepository.findByName(typeName.trim());
            type.ifPresent(types::add);
        }

        Set<Family> families = new HashSet<>();
        for (String familyName : listFamilies) {
            Optional<Family> family = familyRepository.findByName(familyName.trim());
            family.ifPresent(families::add);
        }

        System.out.println(request.getMq135().get());

        Plant plant = Plant.builder()
                .name(request.getName())
                .nameScientific(request.getNameScientific())
                .humidityEarth(request.getHumidityEarth().get())
                .humidityEnvironment(request.getHumidityEnvironment().get())
                .brightness(request.getBrightness().get())
                .ambientTemperature(request.getAmbientTemperature().get())
                .mq135(request.getMq135().get())
                .urlImagePlant(request.getUrlImagePlant())
                .categories(categories)
                .types(types)
                .families(families)
                .build();

        return plant;
    }

    public PlantResponse PlantToPlantResponse(Plant plant) {
        return PlantResponse.builder()
                .id(plant.getId())
                .name(plant.getName())
                .nameScientific(plant.getNameScientific())
                .humidityEarth(plant.getHumidityEarth())
                .humidityEnvironment(plant.getHumidityEnvironment())
                .brightness(plant.getBrightness())
                .ambientTemperature(plant.getAmbientTemperature())
                .mq135(plant.getMq135())
                .urlImagePlant(plant.getUrlImagePlant())
                .categories(plant.getCategories().stream().map(Category::getName).collect(Collectors.toList()))
                .types(plant.getTypes().stream().map(TypePlant::getName).collect(Collectors.toList()))
                .families(plant.getFamilies().stream().map(Family::getName).collect(Collectors.toList()))
                .build();
    }

    public List<PlantResponse> toPlantResponseDTOs(List<Plant> plants) {
        return plants.stream()
                .map(this::PlantToPlantResponse)
                .collect(Collectors.toList());
    }

    public PlantResponse OptionalPlantToPlant(Plant plant) {
        return PlantToPlantResponse(plant);
    }

    public Set<Category> getCategoriesFromNames(List<String> categoryNames) {
        Set<Category> categories = new HashSet<>();
        for (String categoryName : categoryNames) {
            Optional<Category> category = categoryRepository.findByName(categoryName.trim());
            category.ifPresent(categories::add);
        }
        return categories;
    }

    public Set<TypePlant> getTypesFromNames(List<String> typeNames) {
        Set<TypePlant> types = new HashSet<>();
        for (String typeName : typeNames) {
            Optional<TypePlant> type = typePlantRepository.findByName(typeName.trim());
            type.ifPresent(types::add);
        }
        return types;
    }

    public Set<Family> getFamiliesFromNames(List<String> familyNames) {
        Set<Family> families = new HashSet<>();
        for (String familyName : familyNames) {
            Optional<Family> family = familyRepository.findByName(familyName.trim());
            family.ifPresent(families::add);
        }
        return families;
    }

    public PlantRecord mapToPlantRecord(CreatePlantRecordRequest request, Device device) {
        PlantRecord plantRecord = new PlantRecord();
        plantRecord.setHumidityEarth(request.getHumidityEarth());
        plantRecord.setHumidityEnvironment(request.getHumidityEnvironment());
        plantRecord.setBrightness(request.getBrightness());
        plantRecord.setAmbientTemperature(request.getAmbientTemperature());
        plantRecord.setMq135(request.getMq135());
        plantRecord.setEstado(request.getEstado());
        plantRecord.setDevice(device);
        return plantRecord;
    }


}
