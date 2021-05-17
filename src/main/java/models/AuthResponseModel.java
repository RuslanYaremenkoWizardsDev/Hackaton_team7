package models;

import lombok.*;

@Builder
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponseModel {
    private String role;
    private String token;
}
