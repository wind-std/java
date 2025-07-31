package com.example.familyaccounting.service.impl;

import com.example.familyaccounting.dto.FamilyDTO;
import com.example.familyaccounting.entity.Family;
import com.example.familyaccounting.mapper.FamilyMapper;
import com.example.familyaccounting.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyMapper familyMapper;

    @Override
    public Integer createFamily(FamilyDTO familyDTO) {
        Family family = new Family();
        family.setName(familyDTO.getName());
        family.setDescription(familyDTO.getDescription());

        if (familyMapper.insert(family) > 0) {
            return family.getId();
        }
        return null;
    }

    @Override
    public void updateFamilyCreator(Integer familyId, Integer creatorId) {
        familyMapper.updateCreator(familyId, creatorId);
    }

    @Override
    public boolean existsById(Integer familyId) {
        return familyMapper.selectById(familyId) != null;
    }

    @Override
    public List<Family> getAllFamilies() {
        return familyMapper.selectAllFamilies();
    }

    @Override
    public Family getDefaultFamily() {
        // 实现获取默认家庭的逻辑
        Family family = familyMapper.findByName("默认家庭");
        if (family == null) {
            family = new Family();
            family.setName("默认家庭");
            familyMapper.insert(family);
        }
        return family;
    }
}
