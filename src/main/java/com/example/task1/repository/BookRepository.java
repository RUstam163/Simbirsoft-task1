package com.example.task1.repository;

        import com.example.task1.model.Book;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query("delete from Book b where b.id = :id")
    void delete(@Param("id") Long id);

    @Query("select b from Book b where b.name = :name")
    Book findByName(@Param("name") String name);
}
