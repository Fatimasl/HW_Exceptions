package ru.netology.statistic;

public class ShopRepository {
    private Product[] products = new Product[0];

    public ShopRepository() {
    }

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        Product resultOfCheckProduct = findById(product.id);
        if (resultOfCheckProduct != null) {
            throw new AlreadyExistsException("В репозитории уже есть продукт с таким ID: " + product.id);
        }
        ;

        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    // Этот способ мы рассматривали в теории в теме про композицию
    public void removeById(int id) {
        Product resultOfCheckProduct = findById(id);
        if (resultOfCheckProduct == null) {
            throw new NotFoundException("Не найден продукт с таким ID: " + id);
        }

        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].id == id) {
                return products[i];
            }
        }
        return null;
    }
}
