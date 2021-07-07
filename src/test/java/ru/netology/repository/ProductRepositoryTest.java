package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book coreJava = new Book();

    Book book1 = new Book(0, "Идиот", 132, "Достоевский");
    Book book2 = new Book(1, "Сказки", 99, "Народные");
    Smartphone smartphone1 = new Smartphone(2, "Яблоко", 50000, "Эппл");
    Smartphone smartphone2 = new Smartphone(3, "Андройд", 7200, "Найк");

    @Test
    public void shouldDeleteItem() {
        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(smartphone2);

        repository.removeById(2);

        assertArrayEquals(new Product[] {book1,book2,smartphone2}, repository.findAll());
    }

    @Test
    public void negativeId() {
        repository.save(coreJava);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(-30);
        });
    }

    @Test
    public void outOfLength() {
        repository.save(coreJava);
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(10);
        });
    }

    @Test
    public void shouldSaveOneItem() {
        repository.save(coreJava);

        Product[] expected = new Product[]{coreJava};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

}