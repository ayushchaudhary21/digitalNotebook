package org.repo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.ErrorResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotesResponseDTO {
    private String title;
    private  String content;

}
