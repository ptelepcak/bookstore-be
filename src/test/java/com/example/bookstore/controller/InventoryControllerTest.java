package com.example.bookstore.controller;

import com.example.bookstore.controller.command.InventoryCommand;
import com.example.bookstore.model.enums.ActionType;
import com.example.bookstore.service.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.bookstore.mock.InventoryMock.mockInventoryCommand;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @MockBean
    private InventoryService inventoryService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostInventory() throws Exception {
        InventoryCommand command = mockInventoryCommand();

        mockMvc.perform(post("/inventory").content(toJson(command)).
                        contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isOk()
                );
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostInventoryIncompleteCommand() throws Exception {
        InventoryCommand command = new InventoryCommand(null, 100, ActionType.OUTGOING);

        mockMvc.perform(post("/inventory").content(toJson(command)).
                        contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Book ID required")
                );
    }

    private String toJson(Object data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }
}
