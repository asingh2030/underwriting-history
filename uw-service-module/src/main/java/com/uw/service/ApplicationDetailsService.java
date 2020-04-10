package com.uw.service;

import com.uw.db.entities.ApplicationDetails;
import com.uw.db.entities.UnderwritingDetails;
import com.uw.db.repo.ApplicationDetailsRepository;
import com.uw.model.ApplicationDetailsModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationDetailsService {
    @Autowired
    private ApplicationDetailsRepository repository;
    @Autowired
    UnderwritingService uwService;

    public List<Integer> getAllYearsBySsn(String ssn){
        List<Integer> yearsList = repository.findAllByCustomerIdAndGroupByCreatedYear(ssn);
        if(yearsList != null){
            return yearsList;
        }
        return Collections.emptyList();
    }

    public List<ApplicationDetailsModel> getApplicationDetailsBySsnAndYear(String ssn, int year){
        Date startDate = Date.from(LocalDate.of(year, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(LocalDate.of(year, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ApplicationDetails> list = repository.findAllByCustomerIdAndCreatedDateBetween(ssn, startDate, endDate);
        return getApplicationDetailsModel(list);
    }


    private List<ApplicationDetailsModel> getApplicationDetailsModel(List<ApplicationDetails> list) {
        if (list != null) {
            return list.stream().map(this::mapToAppModel).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private ApplicationDetailsModel mapToAppModel(ApplicationDetails details) {
        ApplicationDetailsModel model = new ApplicationDetailsModel();
        BeanUtils.copyProperties(details, model);
        List<UnderwritingDetails> uwDetailsList =details.getUwList ();
        if(uwDetailsList != null){
            UnderwritingDetails underwritingDetails = uwDetailsList.stream ().findAny ().get ();
            model.setUwDetailsModel (uwService.mapToUwModel (underwritingDetails));
        }
        return model;
    }


    public ApplicationDetailsModel getAppDetails(Long appId) {
        Optional<ApplicationDetails> optional = repository.findById(appId);
        ApplicationDetailsModel applicationDetails = null;
        if(optional.isPresent()){
            applicationDetails = mapToAppModel(optional.get());
        }

        return applicationDetails;
    }
}
