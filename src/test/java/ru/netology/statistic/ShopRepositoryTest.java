package ru.netology.statistic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    Product item1 = new Product(0, "Tshirt", 200);
    Product item2 = new Product(1, "Book", 600);
    Product item3 = new Product(2, "Copybook", 5);
    Product item4 = new Product(3, "Tshirt2", 400);
    Product item5 = new Product(4, "Book2", 700);
    Product item6 = new Product(5, "Copybook2", 25);

    @Test
    public void successfulRemoveByFoundedId() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);
        repo.add(item6);

        Product[] expected = {item1, item2, item3, item4, item6};
        repo.removeById(4);
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void removeByNotFoundIdWithException() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);
        repo.add(item6);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(6);
        });
    }

    @Test
    public void successfulAddProduct() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);
        repo.add(item6);
        Product item7 = new Product(6, "Copybook2", 25);

        Product[] expected = {item1, item2, item3, item4, item5, item6, item7};
        repo.add(item7);
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void addWithException() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);
        repo.add(item6);
        Product item7 = new Product(2, "Copybook2", 25);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item7);
        });
    }
}
