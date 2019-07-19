package com.example.demo.dao;


import com.example.demo.domain.OrganizationDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationDao {
    public List<OrganizationDo> listOrganization();
}
