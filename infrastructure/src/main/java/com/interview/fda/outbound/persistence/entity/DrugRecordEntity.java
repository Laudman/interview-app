package com.interview.fda.outbound.persistence.entity;

import com.interview.fda.outbound.persistence.converter.StringListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "drug_record")
@NoArgsConstructor
@AllArgsConstructor
public class DrugRecordEntity {

    @Id
    @NotBlank
    private String applicationNumber;
    private String manufacturerName;
    private String substanceName;
    @Convert(converter = StringListConverter.class)
    private List<String> productNumbers;
}
