package com.zoo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zoo.Animal;
import com.zoo.Zoo;
import com.zoo.app.AnimalOption;
import lombok.val;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class FileUtils {

    public static void saveToFile(Zoo zoo, String fileName) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            ow.writeValue(new java.io.File(fileName), zoo);
        } catch (Exception e) {
            UserInterface.printError("Failed to save your zoo: " + e.getMessage());
        }
    }

    public static void loadFromFile(Zoo zoo, String fileName) {

        try {
            JsonNode zooNode = readJsonFromFile(fileName);
            JsonNode cagesNode = zooNode.get("cages");
            loadCages (zoo.getCages(), cagesNode);
            zoo.setAnimalSequence(zooNode.get("animalSequence").asInt());
        } catch (Exception e) {
            UserInterface.printError("Failed to import your zoo: " + e.getMessage());
        }
    }

    private static JsonNode readJsonFromFile(String fileName) throws IOException {

        StringBuilder json = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();

        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8);
        while (scanner.hasNext()) {
            json.append(scanner.next());
        }
        return mapper.readTree(json.toString());
    }

    private static void loadCages(Map<Integer, Animal> cages, JsonNode cagesNode) {

        cages.clear();
        Iterator<Map.Entry<String, JsonNode>> fields = cagesNode.fields();
        fields.forEachRemaining(c -> {
            val animalNode = c.getValue();
            val animalOption = AnimalOption.findByLabel(animalNode.get("specie").asText());
            animalOption.ifPresent(option -> {
                val animmal = option.newInstance(
                        animalNode.get("name").asText(),
                        animalNode.get("age").asInt(),
                        animalNode.get("hungerStatus").asInt(),
                        animalNode.get("healthStatus").asInt());
                cages.put(Integer.parseInt(c.getKey()), animmal);
            });
        });
    }
}
