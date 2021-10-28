package edu.miu.cs.cs544.exercise03_2.e;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Book {
    @Id @NonNull
    private String isbn;

    @NonNull
    private String title;

    @NonNull
    private String author;
}
