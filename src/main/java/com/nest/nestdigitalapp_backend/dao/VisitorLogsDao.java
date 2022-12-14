package com.nest.nestdigitalapp_backend.dao;

import com.nest.nestdigitalapp_backend.model.VisitorLogsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitorLogsDao extends CrudRepository<VisitorLogsModel,Integer> {

    @Query(value = "SELECT * FROM `visitorlogs` WHERE `date`= :date",nativeQuery = true)
    List<VisitorLogsModel> viewVisOnDate(String date);
}
