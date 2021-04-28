package com.plateer.lotteonDisplay.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItmByMaxPurPsbJsn {
    int maxPurQty;
}
