package edu.miu.cs.cs544.exercise03_2.b;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "book_publisher")
    private Publisher publisher;
}
