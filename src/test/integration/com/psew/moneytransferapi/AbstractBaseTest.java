package com.psew.moneytransferapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;

/**
 * @author Biniam Asnake
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class AbstractBaseTest {

    private static final String MOCK_RESPONSES_BASE_PATH = "src/test/resources/json/";

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mvc;

    protected String readExpectedResponse(final String responseFileName) {
        return readJson(MOCK_RESPONSES_BASE_PATH + responseFileName + ".json");
    }

    private String readJson(final String fileName) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                    objectMapper.readValue(new File(fileName), Object.class)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
