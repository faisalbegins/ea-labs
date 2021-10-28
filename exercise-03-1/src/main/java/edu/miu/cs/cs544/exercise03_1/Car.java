package edu.miu.cs.cs544.exercise03_1;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.PRIVATE)
	private long id;

	@NonNull private String brand;
	@NonNull private String year;
	@NonNull private double price;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "owner_id")
	private Owner owner;
}
