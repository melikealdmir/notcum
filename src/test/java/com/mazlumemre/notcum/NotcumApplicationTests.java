package com.mazlumemre.notcum;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class NotcumApplicationTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    private static final String URL = "/user/save";

    @Test
    public void testSaveUser_NullName() throws Exception {
        String userJson = """
        {
            "name": "",
            "surName": "Veli",
            "mail": "ali@posta.mu.edu.tr",
            "password": "Password1"
        }
        """;
        
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST1: Null name - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_EmptySurname() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "",
            "mail": "ali@posta.mu.edu.tr",
            "password": "Password1"
        }
        """;
        
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    
                    String response = result.getResponse().getContentAsString();
                    assertTrue(response.contains("Surname cannot be empty"));
                });

        System.out.println("TEST2: Empty surname - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_InvalidEmailFormat() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": "ali@@posta.mu.edu.tr",
            "password": "Password1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST3: Invalid email format - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_EmailMissingDomain() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": "ali@posta",
            "password": "Password1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST4: Email missing domain - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_EmailWithoutAtSymbol() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": "aliposta.mu.edu.tr",
            "password": "Password1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST5: Email without @ symbol - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_EmptyPassword() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": "ali@posta.mu.edu.tr",
            "password": ""
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST6: Empty password - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_PasswordTooShort() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": "ali@posta.mu.edu.tr",
            "password": "Pass1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST7: Password too short - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_PasswordNoUpperCase() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": "ali@posta.mu.edu.tr",
            "password": "password1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST8: Password without uppercase letter - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_PasswordNoDigit() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": "ali@posta.mu.edu.tr",
            "password": "Password"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST9: Password without digit - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_NameExceedsMaxLength() throws Exception {
        String userJson = """
        {
            "name": "A".repeat(41),
            "surName": "Veli",
            "mail": "ali@posta.mu.edu.tr",
            "password": "Password1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST10: Name exceeds max length - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_SurnameExceedsMaxLength() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "V".repeat(21),
            "mail": "ali@posta.mu.edu.tr",
            "password": "Password1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST11: Surname exceeds max length - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_EmptyName() throws Exception {
        String userJson = """
        {
            "name": "",
            "surName": "Veli",
            "mail": "ali@posta.mu.edu.tr",
            "password": "Password1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST12: Empty name - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_NullEmail() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": null,
            "password": "Password1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST13: Null email - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_InvalidJsonFormat() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": "ali@posta.mu.edu.tr",
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST14: Invalid JSON format - Test Passed, returned status: BadRequest");
    }

    @Test
    public void testSaveUser_EmailNotUnique() throws Exception {
        String userJson = """
        {
            "name": "Ali",
            "surName": "Veli",
            "mail": "existing@posta.mu.edu.tr",
            "password": "Password1"
        }
        """;

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());

        System.out.println("TEST15: Email already exists - Test Passed, returned status: BadRequest");
    }
}
