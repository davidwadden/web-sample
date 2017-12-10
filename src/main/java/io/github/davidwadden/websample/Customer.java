package io.github.davidwadden.websample;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Builder
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String firstName;
    String lastName;

}
