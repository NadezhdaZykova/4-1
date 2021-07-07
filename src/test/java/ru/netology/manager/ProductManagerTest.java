package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository productRepository = new ProductRepository();
    ProductManager productManager = new ProductManager(productRepository);

    Book book1 = new Book(0, "Идиот", 132, "Достоевский");
    Book book2 = new Book(1, "Сказки", 99, "Народные");
    Smartphone smartphone1 = new Smartphone(2, "Яблоко", 50000, "Эппл");
    Smartphone smartphone2 = new Smartphone(3, "Андройд", 7200, "Найк");


    @Test
    void searchBy() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(smartphone1);
        productManager.add(smartphone2);

        assertArrayEquals(new Product[]{book2, smartphone2},productManager.searbyBy("На"));
    }

    @Test
    void searchByOneTrue() {
        productManager.add(book1);

        assertArrayEquals(new Product[]{book1},productManager.searbyBy("До"));
    }

    @Test
    void searchByOneFalse() {
        productManager.add(book2);

        assertArrayEquals(new Product[]{},productManager.searbyBy("Со"));
    }

    @Test
    void searchByZero() {
        assertArrayEquals(new Product[]{},productManager.searbyBy("Но"));
    }
}