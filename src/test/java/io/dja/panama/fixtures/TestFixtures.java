package io.dja.panama.fixtures;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dja.panama.aggregator.ImmutablePocketItem;
import io.dja.panama.aggregator.ImmutablePocketRequest;
import io.dja.panama.aggregator.ImmutablePocketResponse;
import io.dja.panama.aggregator.PocketItem;
import io.dja.panama.aggregator.PocketRequest;
import io.dja.panama.aggregator.PocketResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class TestFixtures {
    
    public static ImmutablePocketRequest POCKET_REQUEST =
            buildPocketRequest("pocket-request.json");
    public static ImmutablePocketResponse POCKET_RESPONSE =
            buildPocketResponse("pocket-response.json");
    
    public static String RAW_RESPONSE_JSON = readFileToString("pocket-response.json");
    
    private static final ObjectMapper mapper =
            new ObjectMapper().findAndRegisterModules();
   
    
    // TODO: make this generic somehow
    private static ImmutablePocketRequest buildPocketRequest(String fileName) {
        // not ideal
        ImmutablePocketRequest pocketRequest = ImmutablePocketRequest
                .builder()
                .accessToken("")
                .consumerKey("")
                .count(0)
                .detailType("")
                .build();
        try {
            pocketRequest = mapper.readValue(readFileToString(fileName), ImmutablePocketRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return pocketRequest;
    }
    
    private static ImmutablePocketResponse buildPocketResponse(String fileName) {
        // also not ideal
        ImmutablePocketResponse pocketResponse = ImmutablePocketResponse
                .builder()
                .complete(0)
                .list(new HashMap<String, PocketItem>(){
                    { put("dummy", ImmutablePocketItem.builder().build()); }
                })
                .status(0)
                .build();
        try {
            pocketResponse = mapper.readValue(readFileToString(fileName), ImmutablePocketResponse.class);
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
