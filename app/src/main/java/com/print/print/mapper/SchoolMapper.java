package com.print.print.mapper;

import com.print.print.pojo.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SchoolMapper {
    School selectSchoolByName(String schoolname);

    School selectSchoolById(Integer id);

    List<School> selectAllSchool();
}
