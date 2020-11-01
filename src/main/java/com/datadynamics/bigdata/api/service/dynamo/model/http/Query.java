package com.datadynamics.bigdata.api.service.dynamo.model.http;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Query {

    ConsumedCapacity consumedCapacity;

    Long Count;

    List<Object> Items;

    LastEvaluatedKey lastEvaluatedKey;

    Long ScannedCount;

}
