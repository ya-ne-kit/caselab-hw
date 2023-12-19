package org.example;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class User {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private Integer grade;
    @NonNull
    private LocalDate dateOfBirth;
    @NonNull
    private Boolean isAlive;
}
