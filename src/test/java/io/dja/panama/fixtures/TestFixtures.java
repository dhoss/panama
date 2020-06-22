package io.dja.panama.fixtures;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dja.panama.aggregator.ImmutablePocketRequest;
import io.dja.panama.aggregator.ImmutablePocketResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFixtures {
    
    private static Logger logger = LoggerFactory.getLogger(TestFixtures.class);
    private static final ObjectMapper mapper =
            new ObjectMapper();//.findAndRegisterModules();
    
    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).findAndRegisterModules();
    }
    
    // TODO: make this generic somehow
    public static ImmutablePocketRequest buildPocketRequest(String json) throws IOException {
        json = "{\n" +
                "  \"consumer_key\": \"abcdef\",\n" +
                "  \"access_token\": \"fdsa\",\n" +
                "  \"count\": \"14\",\n" +
                "  \"detailType\": \"complete\"\n" +
                "}";
        logger.info("INSIDE BUILDPOCKET REQUEST");
        System.out.println("INSIDE BUILDPOCKET");
        ImmutablePocketRequest immutablePocketRequest = mapper.readValue(json, ImmutablePocketRequest.class);
        
        return immutablePocketRequest;
    }
    
    public static ImmutablePocketResponse buildPocketResponse(String json) throws IOException {
        logger.info("INSIDE BUILDPOCKET RESPONSE");
        return mapper.readValue(json, ImmutablePocketResponse.class);
    }
    
    public static String readFileToString(String fileName) throws IOException {
        return new String(
                Files.readAllBytes(
                        Paths.get(
                                TestFixtures.class
                                        .getClassLoader()
                                        .getResource(fileName)
                                        .getPath())));
    }
}
