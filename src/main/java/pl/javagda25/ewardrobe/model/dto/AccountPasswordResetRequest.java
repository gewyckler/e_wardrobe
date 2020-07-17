package pl.javagda25.ewardrobe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountPasswordResetRequest {
    private Long accountId;

    @NotEmpty
    @Size(min = 5)
    private String resetPassword;
}
