package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_member")
public class Member {
    @Id
   // @SequenceGenerator(name = "member_sequence",initialValue = 1,allocationSize = 50,sequenceName = "member_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id",referencedColumnName = "id")
    private Team team;
}
