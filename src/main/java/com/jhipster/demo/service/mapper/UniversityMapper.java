package com.jhipster.demo.service.mapper;

import com.jhipster.demo.domain.*;
import com.jhipster.demo.service.dto.UniversityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link University} and its DTO {@link UniversityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UniversityMapper extends EntityMapper<UniversityDTO, University> {



    default University fromId(Long id) {
        if (id == null) {
            return null;
        }
        University university = new University();
        university.setId(id);
        return university;
    }
}
