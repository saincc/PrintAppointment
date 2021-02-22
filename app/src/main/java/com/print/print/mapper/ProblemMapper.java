package com.print.print.mapper;

import com.print.print.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProblemMapper {

    Integer addProblem(Problem problem);
}
