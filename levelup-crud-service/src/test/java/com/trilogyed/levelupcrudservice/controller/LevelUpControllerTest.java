package com.trilogyed.levelupcrudservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.levelupcrudservice.dao.LevelUpDao;
import com.trilogyed.levelupcrudservice.dto.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LevelUpController.class)
public class LevelUpControllerTest<LevelUpRepo> {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LevelUpDao levelUpRepo;
    private JacksonTester<LevelUp> levelUpJacksonTester;
    private JacksonTester<List<LevelUp>> levelUpListJacksonTester;
    private LevelUp levelUp;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
        levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(1);
        levelUp.setMemberDate(LocalDate.parse("2019-12-05"));

    }

    @Test
    public void shouldGetAllLevelUp() throws Exception {
        levelUp.setLevelUpId(1);
        given(levelUpRepo.findAll())
                .willReturn(new ArrayList<LevelUp>() {
                    {
                        add(levelUp);
                    }
                });

        MockHttpServletResponse response = mockMvc.perform(
                get("/levelUp")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        List<LevelUp> levelUps = new ArrayList<LevelUp>() {
            {
                add(levelUp);
            }
        };

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(levelUpListJacksonTester.write(levelUps).getJson());
    }

    @Test
    public void shouldGetLevelUpById() throws Exception {
        levelUp.setLevelUpId(1);
        Optional<LevelUp> optionalLevelUp = Optional.of(levelUp);
        given(levelUpRepo.findById(1))
                .willReturn(optionalLevelUp);

        MockHttpServletResponse response = mockMvc.perform(
                get("/levelUp/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                levelUpJacksonTester.write(levelUp).getJson());
    }

    @Test
    public void shouldCreateUpdateAndDeleteLevelUp() throws Exception {
        LevelUp levelUpAdded = levelUp;
        levelUpAdded.setLevelUpId(1);

        given(levelUpRepo.save(levelUp)).willReturn(levelUpAdded);
        Optional<LevelUp> optionalLevelUpAdded = Optional.of(levelUp);
        given(levelUpRepo.findById(1)).willReturn(optionalLevelUpAdded);
        MockHttpServletResponse createResponse = mockMvc.perform(
                post("/levelUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(levelUpJacksonTester
                                .write(levelUp)
                                .getJson()))
                .andReturn().getResponse();
        assertThat(createResponse.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(createResponse.getContentAsString()).isEqualTo(levelUpJacksonTester.write(levelUpAdded).getJson());

        LevelUp levelUp2 = levelUp;
        //update levelUp
        //levelUp2.setTitle("new title");
        MockHttpServletResponse updateResponse = mockMvc.perform(
                put("/levelUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(levelUpJacksonTester
                                .write(levelUp2)
                                .getJson()))
                .andReturn().getResponse();

        assertThat(updateResponse.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());

        MockHttpServletResponse deleteResponse = mockMvc.perform(
                delete("/levelUp/1"))
                .andReturn().getResponse();

        assertThat(deleteResponse.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void shouldReturn422WhenInvalidInput() throws Exception {

        MockHttpServletResponse addEmptyStringResponse = mockMvc.perform(
                post("/levelUp").contentType(MediaType.APPLICATION_JSON)
                        .content(levelUpJacksonTester.write(new LevelUp()).getJson())
        ).andReturn().getResponse();

        assertThat(addEmptyStringResponse.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());

        MockHttpServletResponse addNullResponse = mockMvc.perform(
                post("/levelUp").contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(addNullResponse.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    public void shouldReturn404WhenIdIsInvalid() throws Exception {

        MockHttpServletResponse deleteResponse = mockMvc.perform(
                delete("/levelUp/10"))
                .andReturn().getResponse();

        assertThat(deleteResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        MockHttpServletResponse response = mockMvc.perform(
                get("/levelUp/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
//
//    @Test
//    public void shouldGetAllLevelUpsByGroups() throws Exception {
//        levelUp.setLevelUpId(1);
//        List<LevelUp> levelUps=new ArrayList<LevelUp>();
//        levelUps.add(levelUp);
//        given(levelUpRepo.findAllsByRating("bad")).willReturn(levelUps);
//        given(levelUpRepo.findAllsByStudio("Studio 1")).willReturn(levelUps);
//        given(levelUpRepo.findAllsByTitle("levelUp")).willReturn(levelUps);
//
//        MockHttpServletResponse response = mockMvc.perform(
//                get("/levelUp/studio/Studio 1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(levelUpListJacksonTester.write(levelUps).getJson());
//
//        response = mockMvc.perform(
//                get("/levelUp/title/levelUp")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(levelUpListJacksonTester.write(levelUps).getJson());
//        response = mockMvc.perform(
//                get("/levelUp/rating/bad")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(levelUpListJacksonTester.write(levelUps).getJson());
//    }
}