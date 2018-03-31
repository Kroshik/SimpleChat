package ru.ifmo.cse.phms.web.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ifmo.cse.phms.service.TherapyService;
import ru.ifmo.cse.phms.web.dto.PatientDto;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class PatientController {
    @NonNull
    TherapyService therapyService;

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public ModelAndView getPatientInfo(@PathVariable("id") Long id) {
        val mav = new ModelAndView("therapy/patient");
        val patientInfo = therapyService.getPatient(id);
        mav.addObject("patient", patientInfo);
        return mav;
    }

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public ModelAndView getPatients() {
        val mav = new ModelAndView("therapy/patients");
        val patients = therapyService.getPatients();
        mav.addObject("patients", patients);
        return mav;
    }

    @RequestMapping(value = "/patient/{id}/save", method = RequestMethod.POST)
    public ModelAndView savePatient(@PathVariable("id") Long id, @ModelAttribute("patient") @Valid PatientDto patientDto,
                                    BindingResult result) {
        val mav = new ModelAndView("redirect:");
        val patient = patientDto.toPatient();
        patient.setId(id);
        val savedPatient = therapyService.updatePatient(patient);
        mav.addObject("patient", result.hasErrors() ? patient : savedPatient);
        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), false, 10));
    }
}