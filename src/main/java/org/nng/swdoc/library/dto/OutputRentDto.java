package org.nng.swdoc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputRentDto {

    private Long id;

    private LocalDate issueDate;

    private LocalDate expiredDate;

    private Boolean isReturned;

    private Boolean isDamaged;

    private Long bookId;

    private Long userId;
}
