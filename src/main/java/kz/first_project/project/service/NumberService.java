package kz.first_project.project.service;

import kz.first_project.project.model.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NumberService {

    List<PhoneNumber> getAllPhones();
}
