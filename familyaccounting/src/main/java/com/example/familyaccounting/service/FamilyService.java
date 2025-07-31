package com.example.familyaccounting.service;

import com.example.familyaccounting.dto.FamilyDTO;
import com.example.familyaccounting.entity.Family;

import java.util.List;

public interface FamilyService {
    boolean existsById(Integer familyId);
    Family getDefaultFamily();
    List<Family> getAllFamilies();
    Integer createFamily(FamilyDTO familyDTO);
    void updateFamilyCreator(Integer familyId, Integer creatorId);


}
