package com.uw.service;

import com.uw.db.entities.ApplicationDetails;
import com.uw.db.entities.Underwriter;
import com.uw.db.repo.ApplicationDetailsRepository;
import com.uw.exception.ResourceNotFoundException;
import com.uw.model.*;
import com.uw.util.UWStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationDetailsService {
    @Autowired
    private ApplicationDetailsRepository repository;
    @Autowired
    UnderwritingService uwService;
    @Autowired
    CustomerService customerService;

    public List<Integer> getAllYearsBySsn(String ssn){
        List<Integer> yearsList = repository.findAllByCustomerIdAndGroupByCreatedYear(ssn);
        if(yearsList != null){
            return yearsList;
        }
        return Collections.emptyList();
    }

    public List<AppDetails> getAppDetailsBySsnAndYear(String ssn, int year) {
        Date startDate = Date.from(LocalDate.of(year, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(LocalDate.of(year, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ApplicationDetails> list = repository.findAllByCustomerIdAndCreatedDateBetween(ssn, startDate, endDate);

        if(list != null){
            return list.stream().map(this::mapToAppDetailsModel).collect(Collectors.toList());
        }else {
            return Collections.emptyList();
        }
    }

    public List<ApplicationDetails> saveApplications(List<ApplicationDetailsModel> list) {
        return list.parallelStream ().map (this::mapToAppDetails).collect (Collectors.toList ());
    }

    private AppDetails mapToAppDetailsModel(ApplicationDetails applicationDetails) {
        UwDetails uwDetails = uwService.getUwDetailsByAppId(applicationDetails.getId());

        if (uwDetails == null )
            throw new ResourceNotFoundException("Given application belongs underwriting details not found, application id = "+applicationDetails.getId());

        Underwriter underwriter = uwService.getUnderwriter(uwDetails.getBusinessName());
        AppDetails model = new AppDetails(applicationDetails.getId(),applicationDetails.getCreatedDate(),applicationDetails.getStatus(),
                uwDetails.getDescription(),underwriter.getId(),underwriter.getBusinessName(),uwDetails.getDate(), UWStatus.valueOf(uwDetails.getUnderwritingStatus()));
        return model;
    }

    public List<ApplicationDetailsModel> getAllAppBySsn(String ssn) {
        List<ApplicationDetails> list = repository.findAllByCustomerId(ssn);
        List<ApplicationDetailsModel> appList = list.stream().map(app -> {
            UnderwritingDetailsModel uw = uwService.getUwDetailsModelByAppId(app.getId());
            ApplicationDetailsModel model = new ApplicationDetailsModel();
            BeanUtils.copyProperties(app, model);
            model.setUwDetailsModel(uw);
            return model;
        }).collect(Collectors.toList());
        return appList;
    }

    private ApplicationDetails mapToAppDetails(ApplicationDetailsModel appModel) {
        String ssn = appModel.getCustomerId ();
        CustomerModel customer = customerService.getCustomerDetails(ssn);
        ApplicationDetails app = new ApplicationDetails ();
        BeanUtils.copyProperties (appModel,app);
        app.setStatus(appModel.getStatus());
        ApplicationDetails savedApp = repository.save(app);

        uwService.save(appModel.getUwDetailsModel(), customer.getSsn(), savedApp.getId());
        return app;
    }

}
