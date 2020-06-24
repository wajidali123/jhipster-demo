package com.jhipster.demo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class UniversityMapperTest {

    private UniversityMapper universityMapper;

    @BeforeEach
    public void setUp() {
        universityMapper = new UniversityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(universityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(universityMapper.fromId(null)).isNull();
    }
}
