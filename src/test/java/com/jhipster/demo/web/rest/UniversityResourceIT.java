package com.jhipster.demo.web.rest;

import com.jhipster.demo.JhipsterDemoApp;
import com.jhipster.demo.domain.University;
import com.jhipster.demo.repository.UniversityRepository;
import com.jhipster.demo.service.UniversityService;
import com.jhipster.demo.service.dto.UniversityDTO;
import com.jhipster.demo.service.mapper.UniversityMapper;
import com.jhipster.demo.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jhipster.demo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UniversityResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
public class UniversityResourceIT {

    private static final String DEFAULT_UNI_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UNI_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNI_LOC = "AAAAAAAAAA";
    private static final String UPDATED_UNI_LOC = "BBBBBBBBBB";

    private static final String DEFAULT_UNI_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_UNI_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private UniversityMapper universityMapper;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restUniversityMockMvc;

    private University university;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UniversityResource universityResource = new UniversityResource(universityService);
        this.restUniversityMockMvc = MockMvcBuilders.standaloneSetup(universityResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static University createEntity(EntityManager em) {
        University university = new University()
            .uniName(DEFAULT_UNI_NAME)
            .uniLoc(DEFAULT_UNI_LOC)
            .uniContact(DEFAULT_UNI_CONTACT)
            .city(DEFAULT_CITY);
        return university;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static University createUpdatedEntity(EntityManager em) {
        University university = new University()
            .uniName(UPDATED_UNI_NAME)
            .uniLoc(UPDATED_UNI_LOC)
            .uniContact(UPDATED_UNI_CONTACT)
            .city(UPDATED_CITY);
        return university;
    }

    @BeforeEach
    public void initTest() {
        university = createEntity(em);
    }

    @Test
    @Transactional
    public void createUniversity() throws Exception {
        int databaseSizeBeforeCreate = universityRepository.findAll().size();

        // Create the University
        UniversityDTO universityDTO = universityMapper.toDto(university);
        restUniversityMockMvc.perform(post("/api/universities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(universityDTO)))
            .andExpect(status().isCreated());

        // Validate the University in the database
        List<University> universityList = universityRepository.findAll();
        assertThat(universityList).hasSize(databaseSizeBeforeCreate + 1);
        University testUniversity = universityList.get(universityList.size() - 1);
        assertThat(testUniversity.getUniName()).isEqualTo(DEFAULT_UNI_NAME);
        assertThat(testUniversity.getUniLoc()).isEqualTo(DEFAULT_UNI_LOC);
        assertThat(testUniversity.getUniContact()).isEqualTo(DEFAULT_UNI_CONTACT);
        assertThat(testUniversity.getCity()).isEqualTo(DEFAULT_CITY);
    }

    @Test
    @Transactional
    public void createUniversityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = universityRepository.findAll().size();

        // Create the University with an existing ID
        university.setId(1L);
        UniversityDTO universityDTO = universityMapper.toDto(university);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUniversityMockMvc.perform(post("/api/universities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(universityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the University in the database
        List<University> universityList = universityRepository.findAll();
        assertThat(universityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUniNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = universityRepository.findAll().size();
        // set the field null
        university.setUniName(null);

        // Create the University, which fails.
        UniversityDTO universityDTO = universityMapper.toDto(university);

        restUniversityMockMvc.perform(post("/api/universities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(universityDTO)))
            .andExpect(status().isBadRequest());

        List<University> universityList = universityRepository.findAll();
        assertThat(universityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = universityRepository.findAll().size();
        // set the field null
        university.setCity(null);

        // Create the University, which fails.
        UniversityDTO universityDTO = universityMapper.toDto(university);

        restUniversityMockMvc.perform(post("/api/universities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(universityDTO)))
            .andExpect(status().isBadRequest());

        List<University> universityList = universityRepository.findAll();
        assertThat(universityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUniversities() throws Exception {
        // Initialize the database
        universityRepository.saveAndFlush(university);

        // Get all the universityList
        restUniversityMockMvc.perform(get("/api/universities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(university.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniName").value(hasItem(DEFAULT_UNI_NAME)))
            .andExpect(jsonPath("$.[*].uniLoc").value(hasItem(DEFAULT_UNI_LOC)))
            .andExpect(jsonPath("$.[*].uniContact").value(hasItem(DEFAULT_UNI_CONTACT)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)));
    }
    
    @Test
    @Transactional
    public void getUniversity() throws Exception {
        // Initialize the database
        universityRepository.saveAndFlush(university);

        // Get the university
        restUniversityMockMvc.perform(get("/api/universities/{id}", university.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(university.getId().intValue()))
            .andExpect(jsonPath("$.uniName").value(DEFAULT_UNI_NAME))
            .andExpect(jsonPath("$.uniLoc").value(DEFAULT_UNI_LOC))
            .andExpect(jsonPath("$.uniContact").value(DEFAULT_UNI_CONTACT))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY));
    }

    @Test
    @Transactional
    public void getNonExistingUniversity() throws Exception {
        // Get the university
        restUniversityMockMvc.perform(get("/api/universities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUniversity() throws Exception {
        // Initialize the database
        universityRepository.saveAndFlush(university);

        int databaseSizeBeforeUpdate = universityRepository.findAll().size();

        // Update the university
        University updatedUniversity = universityRepository.findById(university.getId()).get();
        // Disconnect from session so that the updates on updatedUniversity are not directly saved in db
        em.detach(updatedUniversity);
        updatedUniversity
            .uniName(UPDATED_UNI_NAME)
            .uniLoc(UPDATED_UNI_LOC)
            .uniContact(UPDATED_UNI_CONTACT)
            .city(UPDATED_CITY);
        UniversityDTO universityDTO = universityMapper.toDto(updatedUniversity);

        restUniversityMockMvc.perform(put("/api/universities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(universityDTO)))
            .andExpect(status().isOk());

        // Validate the University in the database
        List<University> universityList = universityRepository.findAll();
        assertThat(universityList).hasSize(databaseSizeBeforeUpdate);
        University testUniversity = universityList.get(universityList.size() - 1);
        assertThat(testUniversity.getUniName()).isEqualTo(UPDATED_UNI_NAME);
        assertThat(testUniversity.getUniLoc()).isEqualTo(UPDATED_UNI_LOC);
        assertThat(testUniversity.getUniContact()).isEqualTo(UPDATED_UNI_CONTACT);
        assertThat(testUniversity.getCity()).isEqualTo(UPDATED_CITY);
    }

    @Test
    @Transactional
    public void updateNonExistingUniversity() throws Exception {
        int databaseSizeBeforeUpdate = universityRepository.findAll().size();

        // Create the University
        UniversityDTO universityDTO = universityMapper.toDto(university);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUniversityMockMvc.perform(put("/api/universities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(universityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the University in the database
        List<University> universityList = universityRepository.findAll();
        assertThat(universityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUniversity() throws Exception {
        // Initialize the database
        universityRepository.saveAndFlush(university);

        int databaseSizeBeforeDelete = universityRepository.findAll().size();

        // Delete the university
        restUniversityMockMvc.perform(delete("/api/universities/{id}", university.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<University> universityList = universityRepository.findAll();
        assertThat(universityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
