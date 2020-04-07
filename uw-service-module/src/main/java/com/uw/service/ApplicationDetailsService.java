package com.uw.service;

import com.uw.db.model.ApplicationDetails;
import com.uw.db.model.Document;
import com.uw.db.repo.ApplicationDetailsRepository;
import com.uw.model.ApplicationDetailsModel;
import com.uw.model.DocumentModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationDetailsService {
    @Autowired
    private ApplicationDetailsRepository repository;

    public List<ApplicationDetailsModel> getApplicationDetailsBySsnAnd(String ssn, LocalDate year){
        LocalDate startDate = LocalDate.of(year.getYear(), 1, 1);
        LocalDate endDate = LocalDate.of(year.getYear(), 12, 31);
        List<ApplicationDetails> list = repository.findAllByCustomerIdAndCreatedDateBetween(ssn, Date.valueOf(startDate), Date.valueOf(endDate));
        return getApplicationDetailsModel(list);
    }
    private List<ApplicationDetailsModel> getApplicationDetailsModel(List<ApplicationDetails> list) {
        if (list != null) {
            return list.stream().map(ApplicationDetailsService::mapToAppModel).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static ApplicationDetailsModel mapToAppModel(ApplicationDetails details) {
        ApplicationDetailsModel model = new ApplicationDetailsModel();
        BeanUtils.copyProperties(details, model);
        return model;
    }

}
