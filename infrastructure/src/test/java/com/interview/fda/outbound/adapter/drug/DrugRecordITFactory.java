package com.interview.fda.outbound.adapter.drug;

import com.interview.fda.drugs.api.model.DrugRecordDTO;
import com.interview.fda.outbound.persistence.entity.DrugRecordEntity;
import com.interview.fda.outbound.persistence.repository.DrugRecordRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.interview.fda.BaseIT.getRandomString;

@Component
public class DrugRecordITFactory {

    @Autowired
    private DrugRecordRepository repository;

    public DrugRecordDTO newDrugRecordDTO() {
        return DrugRecordBuilder.builder().build().toDrugRecordDTO();
    }

    public DrugRecordDTO newDrugRecordDTO(DrugRecordBuilder builder) {
        return builder.toDrugRecordDTO();
    }

    public DrugRecordEntity newDrugRecordEntity() {
        return DrugRecordBuilder.builder().build().toDrugRecordEntity();
    }

    public DrugRecordEntity newDrugRecordEntity(DrugRecordBuilder builder) {
        return builder.toDrugRecordEntity();
    }

    public DrugRecordEntity saveDrugRecordEntity() {
        return repository.save(newDrugRecordEntity());
    }

    @Builder
    public static class DrugRecordBuilder {

        @Builder.Default
        private String applicationNumber = getRandomString();

        @Builder.Default
        private String manufacturerName = getRandomString();

        @Builder.Default
        private String substanceName = getRandomString();

        @Builder.Default
        private List<String> productNumbers = List.of(getRandomString(), getRandomString());

        public DrugRecordDTO toDrugRecordDTO() {
            return new DrugRecordDTO()
                    .applicationNumber(applicationNumber)
                    .manufacturerName(manufacturerName)
                    .substanceName(substanceName)
                    .productNumbers(productNumbers);
        }

        public DrugRecordEntity toDrugRecordEntity() {
            return DrugRecordEntity.builder()
                    .applicationNumber(applicationNumber)
                    .manufacturerName(manufacturerName)
                    .substanceName(substanceName)
                    .productNumbers(productNumbers)
                    .build();
        }

    }
}
