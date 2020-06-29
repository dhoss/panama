package io.dja.panama.fixtures;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dja.panama.aggregator.pocket.ImmutablePocketRequest;
import io.dja.panama.aggregator.pocket.ImmutablePocketResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFixtures {
    
    private static Logger logger = LoggerFactory.getLogger(TestFixtures.class);
    private static final ObjectMapper mapper =
            new ObjectMapper()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .findAndRegisterModules();
    
    // TODO: make this generic somehow
    public static ImmutablePocketRequest buildPocketRequest(String json) throws IOException {
        return mapper.readValue(json, ImmutablePocketRequest.class);
    }
    
    public static ImmutablePocketResponse buildPocketResponse(String json) throws IOException {
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
