package ru.ikupdev.library.util;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.util.ClassTypeInformation;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.model.User;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 10.02.2022
 */
@Slf4j
public class OrderUtil {

    public static QPageRequest usersOrder(Pageable pageable) {
        ClassTypeInformation<User> typeInfo = ClassTypeInformation.from(User.class);
        Iterator<Sort.Order> iterator = pageable.getSort().iterator();
        List<Sort.Order> orders = new LinkedList<>();
        while (iterator.hasNext()) {
            orders.add(iterator.next());
        }
        if (orders.stream().anyMatch(order -> "firstName".equals(order.getProperty()))) {
            Sort.Order fistNameOrder = orders.stream().filter(order -> "firstName".equals(order.getProperty())).findFirst().get();
            orders.removeIf(order -> "firstName".equals(order.getProperty()));
            orders.add(new Sort.Order(fistNameOrder.getDirection(), "created"));
            orders.add(new Sort.Order(fistNameOrder.getDirection(), "firstName"));
        } else if (orders.stream().anyMatch(order -> "lastName".equals(order.getProperty()))) {
            Sort.Order fistNameOrder = orders.stream().filter(order -> "lastName".equals(order.getProperty())).findFirst().get();
            orders.removeIf(order -> "lastName".equals(order.getProperty()));
            orders.add(new Sort.Order(fistNameOrder.getDirection(), "created"));
            orders.add(new Sort.Order(fistNameOrder.getDirection(), "lastName"));
        } else if (orders.stream().anyMatch(order -> "status".equals(order.getProperty()))) {
            Sort.Order fistNameOrder = orders.stream().filter(order -> "status".equals(order.getProperty())).findFirst().get();
            orders.removeIf(order -> "status".equals(order.getProperty()));
            orders.add(new Sort.Order(fistNameOrder.getDirection(), "created"));
            orders.add(new Sort.Order(fistNameOrder.getDirection(), "status"));
        } else {
            orders.add(new Sort.Order(Sort.Direction.DESC, "created"));
        }
        List<OrderSpecifier> specifiers = prepareOrderSpecifiers(orders, typeInfo);
        return QPageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), specifiers.toArray(new OrderSpecifier[]{}));
    }

    public static QPageRequest bookshelfOrder(Pageable pageable) {
        ClassTypeInformation<User> typeInfo = ClassTypeInformation.from(User.class);
        Iterator<Sort.Order> iterator = pageable.getSort().iterator();
        List<Sort.Order> orders = new LinkedList<>();
        while (iterator.hasNext()) {
            orders.add(iterator.next());
        }
        if (orders.stream().anyMatch(order -> "bookshelfName".equals(order.getProperty()))) {
            Sort.Order fistNameOrder = orders.stream().filter(order -> "bookshelfName".equals(order.getProperty())).findFirst().get();
            orders.removeIf(order -> "bookshelfName".equals(order.getProperty()));
            orders.add(new Sort.Order(fistNameOrder.getDirection(), "created"));
            orders.add(new Sort.Order(fistNameOrder.getDirection(), "bookshelfName"));
        } else {
            orders.add(new Sort.Order(Sort.Direction.DESC, "created"));
        }
        List<OrderSpecifier> specifiers = prepareOrderSpecifiers(orders, typeInfo);
        return QPageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), specifiers.toArray(new OrderSpecifier[]{}));
    }

    public static QPageRequest booksOrder(Pageable pageable) {
        ClassTypeInformation<Book> typeInfo = ClassTypeInformation.from(Book.class);
        Iterator<Sort.Order> iterator = pageable.getSort().iterator();
        List<Sort.Order> orders = new LinkedList<>();
        while (iterator.hasNext()) {
            orders.add(iterator.next());
        }
        if (orders.stream().anyMatch(order -> "title".equals(order.getProperty()))) {
            Sort.Order subtitleOrder = orders.stream().filter(order -> "title".equals(order.getProperty())).findFirst().get();
            orders.removeIf(order -> "title".equals(order.getProperty()));
            orders.add(new Sort.Order(subtitleOrder.getDirection(), "created"));
            orders.add(new Sort.Order(subtitleOrder.getDirection(), "title"));
        } else if (orders.stream().anyMatch(order -> "subtitle".equals(order.getProperty()))) {
            Sort.Order subtitleOrder = orders.stream().filter(order -> "subtitle".equals(order.getProperty())).findFirst().get();
            orders.removeIf(order -> "subtitle".equals(order.getProperty()));
            orders.add(new Sort.Order(subtitleOrder.getDirection(), "created"));
            orders.add(new Sort.Order(subtitleOrder.getDirection(), "subtitle"));
        } else if (orders.stream().anyMatch(order -> "authors".equals(order.getProperty()))) {
            Sort.Order subtitleOrder = orders.stream().filter(order -> "authors".equals(order.getProperty())).findFirst().get();
            orders.removeIf(order -> "authors".equals(order.getProperty()));
            orders.add(new Sort.Order(subtitleOrder.getDirection(), "created"));
            orders.add(new Sort.Order(subtitleOrder.getDirection(), "authors"));
        } else if (orders.stream().anyMatch(order -> "language".equals(order.getProperty()))) {
            Sort.Order subtitleOrder = orders.stream().filter(order -> "authors".equals(order.getProperty())).findFirst().get();
            orders.removeIf(order -> "language".equals(order.getProperty()));
            orders.add(new Sort.Order(subtitleOrder.getDirection(), "created"));
            orders.add(new Sort.Order(subtitleOrder.getDirection(), "language"));
        } else {
            orders.add(new Sort.Order(Sort.Direction.DESC, "created"));
        }
        List<OrderSpecifier> specifiers = prepareOrderSpecifiers(orders, typeInfo);
        return QPageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), specifiers.toArray(new OrderSpecifier[]{}));
    }

    private static List<OrderSpecifier> prepareOrderSpecifiers(List<Sort.Order> orders, ClassTypeInformation typeInfo ) {
        List<OrderSpecifier> specifiers = new LinkedList<>();
        orders.forEach(order -> {
            String property = order.getProperty();
            Sort.Direction direction = order.getDirection();
            addOrderSpecifier(specifiers, property, direction, typeInfo);
        });
        return specifiers;
    }

    private static void addOrderSpecifier(List<OrderSpecifier> specifiers,
                                          String property,
                                          Sort.Direction direction,
                                          ClassTypeInformation<?> typeInfo) {
        try {
            specifiers.add(buildOrderSpecifier(property, direction, typeInfo));
        } catch (PropertyReferenceException e) {
            log.error(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static OrderSpecifier<?> buildOrderSpecifier(String property,
                                                         Sort.Direction direction,
                                                         ClassTypeInformation<?> typeInfo) {
        Order order = direction == null ? Order.ASC : Order.valueOf(direction.name());
        Expression<?> sortPropertyExpression = new PathBuilderFactory().create(typeInfo.getType());
        PropertyPath path = PropertyPath.from(property, typeInfo);
        sortPropertyExpression = Expressions.path(path.getType(), (Path<?>) sortPropertyExpression, path.toDotPath());
        return new OrderSpecifier(order, sortPropertyExpression);
    }

}
