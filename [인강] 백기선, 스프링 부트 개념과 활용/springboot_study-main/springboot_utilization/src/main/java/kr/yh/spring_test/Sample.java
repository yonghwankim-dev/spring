package kr.yh.spring_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sample_id")
    private Long id;
    private String name;
    private int age;
}
