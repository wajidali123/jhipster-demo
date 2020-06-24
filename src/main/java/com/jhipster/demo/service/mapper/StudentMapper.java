package com.jhipster.demo.service.mapper;

import com.jhipster.demo.domain.*;
import com.jhipster.demo.service.dto.StudentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {



    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
