package gui.rysis.demospringboot.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnimePutRequestBody {
    private Long id;
    @NotEmpty(message = "Name anime cannot be empty")
    private String name;
}
