package hibernate.model;

import lombok.*;
import org.h2.command.dml.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    private long id;
    private String nickname;
    private String password;
    private int age;
    private Gender gender;
    private Set listOrder;

}
