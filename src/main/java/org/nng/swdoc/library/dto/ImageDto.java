package org.nng.swdoc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {

    private Long id;

    private byte[] imageData;

    public ImageDto(byte[] bytes) {
        imageData = bytes;
    }
}
