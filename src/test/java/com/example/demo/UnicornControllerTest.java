package com.example.demo;
import com.example.demo.unicorn.IdentifiableMark;
import com.example.demo.unicorn.Unicorn;
import com.example.demo.unicorn.UnicornController;
import com.example.demo.unicorn.UnicornService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UnicornControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UnicornService unicornService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);


        MockitoAnnotations.initMocks(this);
        UnicornController unicornController = new UnicornController(unicornService);
        mockMvc = MockMvcBuilders.standaloneSetup(unicornController).build();
    }

    @Test
    void testCreateUnicorn() throws Exception {
        Unicorn unicorn = new Unicorn("Sparkle Princess", "White", 104, "Gold", "Sapphire", 94, "cm", 104, "kg", Arrays.asList(new IdentifiableMark("Left", "Hind Quarters", "Star", "Blue")));
        when(unicornService.createUnicorn(any(Unicorn.class))).thenReturn(new Unicorn(1L, "Sparkle Princess", "White", 104, "Gold", "Sapphire", 94, "cm", 104, "kg", Arrays.asList(new IdentifiableMark("Left", "Hind Quarters", "Star", "Blue"))));

        mockMvc.perform(MockMvcRequestBuilders.post("/unicorns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"Sparkle Princess\",\n" +
                                "  \"color\": \"White\",\n" +
                                "  \"height\": 104,\n" +
                                "  \"hornColor\": \"Gold\",\n" +
                                "  \"eyeColor\": \"Sapphire\",\n" +
                                "  \"weight\": 94,\n" +
                                "  \"heightUnit\": \"cm\",\n" +
                                "  \"weightUnit\": \"kg\",\n" +
                                "  \"identifiableMarks\": [\n" +
                                "    {\n" +
                                "      \"side\": \"Left\",\n" +
                                "      \"location\": \"Hind Quarters\",\n" +
                                "      \"symbol\": \"Star\",\n" +
                                "      \"color\": \"Blue\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
    }


    @Test
    void testGetAllUnicorns() throws Exception {
        List<Unicorn> unicorns = new ArrayList<>();
        when(unicornService.getAllUnicorns()).thenReturn(unicorns);

        mockMvc.perform(get("/unicorns"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();

        verify(unicornService, times(1)).getAllUnicorns();
    }

    @Test
    void testGetUnicornById() throws Exception {
        Long id = 1L;
        Unicorn unicorn = new Unicorn(1L, "Sparkle Princess", "White", 104, "Gold", "Sapphire", 94, "cm", 104, "kg", new ArrayList<>());
        when(unicornService.getUnicornById(id)).thenReturn(unicorn);

        mockMvc.perform(MockMvcRequestBuilders.get("/unicorns/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Sparkle Princess"))
                .andExpect(jsonPath("$.hairColor").value("White"))
                .andExpect(jsonPath("$.height").value(94))
                .andExpect(jsonPath("$.hornColor").value("Gold"))
                .andExpect(jsonPath("$.eyeColor").value("Sapphire"))
                .andExpect(jsonPath("$.weight").value(104))
                .andExpect(jsonPath("$.heightUnit").value("cm"))
                .andExpect(jsonPath("$.weightUnit").value("kg"))
                .andExpect(jsonPath("$.identifiableMarks", hasSize(0)));

        verify(unicornService, times(1)).getUnicornById(id);
    }

    @Test
    public void testUpdateUnicorn() throws Exception {
        Long id = 1L;
        Unicorn unicorn = new Unicorn("Sparkle Princess", "White", 104, "Gold", "Sapphire", 94, "cm", 104, "kg", Arrays.asList(new IdentifiableMark("Left", "Hind Quarters", "Star", "Blue")));
        when(unicornService.updateUnicorn(eq(id), any(Unicorn.class))).thenReturn(new Unicorn(id, "Sparkle Princess Updated", "White", 104, "Gold", "Sapphire", 94, "cm", 104, "kg", Arrays.asList(new IdentifiableMark("Left", "Hind Quarters", "Star", "Blue"))));

        mockMvc.perform(MockMvcRequestBuilders.put("/unicorns/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"Sparkle Princess Updated\",\n" +
                                "  \"color\": \"White\",\n" +
                                "  \"height\": 104,\n" +
                                "  \"hornColor\": \"Gold\",\n" +
                                "  \"eyeColor\": \"Sapphire\",\n" +
                                "  \"weight\": 94,\n" +
                                "  \"heightUnit\": \"cm\",\n" +
                                "  \"weightUnit\": \"kg\",\n" +
                                "  \"identifiableMarks\": [\n" +
                                "    {\n" +
                                "      \"side\": \"Left\",\n" +
                                "      \"location\": \"Hind Quarters\",\n" +
                                "      \"symbol\": \"Star\",\n" +
                                "      \"color\": \"Blue\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sparkle Princess Updated"));

        verify(unicornService, times(1)).updateUnicorn(eq(id), any(Unicorn.class));
    }




}
