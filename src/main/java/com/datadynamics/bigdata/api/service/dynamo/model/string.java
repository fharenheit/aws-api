package com.datadynamics.bigdata.api.service.dynamo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class string {

    Long CapacityUnits;
    Long ReadCapacityUnits;
    Long WriteCapacityUnits;

}
