package com.print.print.service;

import com.print.print.pojo.School;

import java.util.List;

public interface SchoolService {
    School selectSchoolByName(String schoolname);

    List<School> selectAllSchool();

    School selectSchoolById(Integer id);
}
