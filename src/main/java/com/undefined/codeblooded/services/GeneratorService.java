package com.undefined.codeblooded.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.undefined.codeblooded.models.ApplicationUser;
import com.undefined.codeblooded.models.Project;
import com.undefined.codeblooded.repositories.GeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Service
public class GeneratorService {

    @Autowired
    private GeneratorRepository generatorRepository;

    private final String PATH = "C:\\Users\\Daksh\\IdeaProjects\\STG-Codeblooded-Undefined\\files\\";

    public void persistData(ApplicationUser user, String projectName, String mapping, JsonNode source, JsonNode target) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String sourceJson = objectMapper.writeValueAsString(source);
        String targetJson = objectMapper.writeValueAsString(target);

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = generateRandomString();

        String targetPath = generatedString+"-target-"+projectName+".json";
        String sourcePath = generatedString+"-source-"+projectName+".json";
        String mappingPath = generatedString+"-mapping-"+projectName+".csv";


        try {
            FileWriter file = new FileWriter(PATH+sourcePath);
            file.write(sourceJson);
            file.close();

            FileWriter targetFileWriter = new FileWriter(PATH+targetPath);
            targetFileWriter.write(targetJson);
            targetFileWriter.close();

            FileWriter mappingFileWriter= new FileWriter(PATH+mappingPath);
            mappingFileWriter.write(mapping);
            mappingFileWriter.close();

        }
        catch (IOException e){

        }

        Project project = Project.builder()
                .projectName(projectName)
                .user(user)
                .inputPath(PATH)
                .outputPath(PATH)
                .mappingPath(PATH)
                .build();
        generatorRepository.save(project);



    }

    private String generateRandomString(){
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public List<Map<String, Object>> getProjects(ApplicationUser user){

        List<Project> projects = user.getProjects();
        System.out.println(projects.size());
        List<Map<String, Object>> result = new ArrayList<>();
        for(Project p: projects){
            Map<String, Object> map = new HashMap<>();
           map.put("name", p.getProjectName());
           map.put("id", p.getProjectId());
           result.add(map);
        }
        return result;

    }

}
