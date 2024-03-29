package com.kiosk.dto;

import com.kiosk.model.type.RoomBookingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomBookingDTO extends BaseDTO {

    @NotNull(message = "type should be present", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private RoomBookingType type;

    @NotNull(message = "room id should be present", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private Integer roomId;

    @NotNull(message = "user id should be present", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private Integer userId;

    @NotNull(message = "startDate should be present", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private Timestamp startDate;

    @NotNull(message = "endDate should be present", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private Timestamp endDate;
}
