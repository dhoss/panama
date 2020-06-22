package io.dja.panama.fixtures;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dja.panama.aggregator.ImmutablePocketRequest;
import io.dja.panama.aggregator.ImmutablePocketResponse;
import io.dja.panama.aggregator.PocketRequest;
import io.dja.panama.aggregator.PocketResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFixtures {
    
    public static PocketRequest POCKET_REQUEST =
            buildPocketRequest("pocket-request.json");
    public static PocketResponse POCKET_RESPONSE =
            buildPocketResponse("pocket-response.json");
    
    public static String RAW_RESPONSE_JSON = readFileToString("pocket-response.json");
    
    private static final ObjectMapper mapper =
            new ObjectMapper().findAndRegisterModules();
   
    
    // TODO: make this generic somehow
    private static PocketRequest buildPocketRequest(String fileName) {
        PocketRequest pocketRequest = ImmutablePocketRequest.builder().build();
        try {
            pocketRequest = mapper.readValue(readFileToString(fileName), PocketRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return pocketRequest;
    }
    
    private static PocketResponse buildPocketResponse(String fileName) {
        PocketResponse pocketResponse = ImmutablePocketResponse.builder().build();
        try {
            pocketResponse = mapper.readValue(readFileToString(fileName), PocketResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return pocketResponse;
    }
    
    private static String readFileToString(String fileName){
        String json = "";
        try {
            new String(
                    Files.readAllBytes(
                            Paths.get(
                                    TestFixtures.class
                                            .getClassLoader()
                                            .getResource(fileName)
                                            .getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return json;
    }
}
