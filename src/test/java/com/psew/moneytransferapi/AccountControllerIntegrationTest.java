package com.psew.moneytransferapi;

import com.psew.moneytransferapi.util.TestDataGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Biniam Asnake
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class AccountControllerIntegrationTest extends AbstractBaseTest {

    private static final String BASE_URL = "/api/account";

    @Test
    public void shouldGetAllAccountsAndReturnHttpOk() throws Exception {
        mvc.perform(
                get(BASE_URL + "/list")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(readExpectedResponse("account_getAllAccounts"), true));
    }

    @Test
    public void shouldCreateAccountAndReturnCreatedStatusAndMessage() throws Exception {

        mvc.perform(
                post(BASE_URL + "/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        // Here we are using the Account object defined in TestDataGenerator.java
                        .content(objectMapper.writeValueAsString(TestDataGenerator.createAccount()))
        )
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.message")
                        .value("Account with id 5 is created successfully."));
    }

    @Test
    public void shouldReturnAnErrorWhenCreatingAccountWithDuplicateData() throws Exception {

        String data = "{" +
                "  \"firstName\": \"Biniam\"," +
                "  \"lastName\": \"Asnake\"," +
                "  \"email\" : \"biniamasnake@gmail.com\"," +
                "  \"phoneNumber\": \"+251966272502\"," +
                "  \"pin\": \"1234\"," +
                "  \"balance\": 2000" +
                "}";

        mvc.perform(
                post(BASE_URL + "/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        // Here we are using the Account object defined in the above 'data' JSON
                        .content(data)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(readExpectedResponse("account_createAccount_error"), true));
    }

    @Test
    public void shouldUpdateAccountAndReturnHttpOkAndMessage() throws Exception {

        String data = "{" +
                "  \"firstName\": \"Jane Updated\"," +
                "  \"lastName\": \"Doe Updated\"," +
                "  \"email\" : \"janedoe_updated@example.com\"," +
                "  \"phoneNumber\": \"+4922222222\"," +
                "  \"pin\": \"2222\"" +
                "}";

        mvc.perform(
                put(BASE_URL + "/" + 4)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        // Here we are using the Account object defined in the above 'data' JSON
                        .content(data)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.message")
                        .value("Account with id 4 is updated successfully."));
    }

    @Test
    public void shouldDeleteAccountByIdAndReturnHttpOkAndMessage() throws Exception {

        mvc.perform(
                delete(BASE_URL + "/" + 3)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.message")
                        .value("Account with id 3 is deleted successfully."));
    }

    @Test
    public void shouldReturnErrorWhenDeletingAccountByIdWithWrongId() throws Exception {

        mvc.perform(
                delete(BASE_URL + "/" + 0)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(readExpectedResponse("account_deleteAccountById_error"), true));
    }
}
