package ua.com.epam.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ua.com.epam.library.dao.BookDao;
import ua.com.epam.library.dao.CartDao;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.entity.Cart;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.CartService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CartServiceImplTest {

    @Mock
    private BookDao bookDao;
    @Mock
    private CartDao cartDao;
    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartServiceImpl(bookDao, cartDao);
    }

    @Test
    void testCreateCart_ShouldReturnTrue() {
        //setup test. mock
        Cart cart = getCart(1L, 2L, 1, 1, "First Book", "first.jpg,", "First Author name", "First Author last name");
        List<Cart> cartList = getCartList();
        Book testBook = new Book();
        testBook.setQuantity(5);
        when(cartDao.createCart(any(Cart.class))).thenReturn(true);
        when(cartDao.getAllBooksInCartByBookId(cart.getBookId())).thenReturn(cartList);
        when(bookDao.getBookById(cart.getBookId())).thenReturn(Optional.of(testBook));
//when - invoke test method
        boolean isCartCreated = cartService.createCart(cart.getBookId(), cart.getUserId());

        //then - assertions
        assertTrue(isCartCreated);
        verify(cartDao).createCart(any(Cart.class));
    }

    @Test
    void testGetAllBooksInCartByUserId_ShouldReturnListAllBooksInCartByUserId() {
        //given
        long testUserId = 1L;
        List<Cart> carts = getCartList();

        List<Cart> allCartsByUserId = carts.stream()
                .filter(cart -> cart.getUserId() == testUserId)
                .collect(Collectors.toList());

        given(cartDao.getAllBooksInCartByUserId(testUserId)).willReturn(allCartsByUserId);

        //when
        List<Cart> allBooksInCartByUserId = cartService.getAllBooksInCartByUserId(testUserId);

        //then
        assertThat(allBooksInCartByUserId).isNotNull();
        assertThat(allBooksInCartByUserId).isEqualTo(allCartsByUserId);

        verify(cartDao).getAllBooksInCartByUserId(testUserId);
    }

    @Test
    void testGetAllItemsOfBookInCartByBookId_ShouldReturnListAllItemsOfBooksInCartByBookId() {
        //given
        long testBookId = 2L;
        List<Cart> carts = getCartList();

        List<Cart> allCartsByBookId = carts.stream()
                .filter(cart -> cart.getBookId() == testBookId)
                .collect(Collectors.toList());

        given(cartDao.getAllBooksInCartByBookId(testBookId)).willReturn(allCartsByBookId);

        //when
        List<Cart> allBooksInCartByBookId = cartService.getAllItemsOfBookInCartByBookId(testBookId);

        //then
        assertThat(allBooksInCartByBookId).isNotNull();
        assertThat(allBooksInCartByBookId).isEqualTo(allCartsByBookId);

        verify(cartDao).getAllBooksInCartByBookId(testBookId);
    }

    @Test
    void testDelete_ShouldReturnTrue() {
        //given
        Cart testCart = getCartList().get(0);
        given(cartDao.getReceiptByUserIdAndByBookIdAndCartId(testCart.getUserId(), testCart.getBookId(), testCart.getId())).willReturn(Optional.of(new Cart()));
        given(cartDao.delete(testCart.getBookId(), testCart.getUserId(), testCart.getId())).willReturn(true);

        //when
        when(cartDao.getReceiptByUserIdAndByBookIdAndCartId(testCart.getUserId(), testCart.getBookId(), testCart.getId())).thenReturn(Optional.of(new Cart()));
        when(cartDao.delete(testCart.getBookId(), testCart.getUserId(), testCart.getId())).thenReturn(true);
        boolean cartIsDeleted = cartService.delete(testCart.getBookId(), testCart.getUserId(), testCart.getId());

        //then
        assertTrue(cartIsDeleted);
        verify(cartDao).delete(testCart.getBookId(), testCart.getUserId(), testCart.getId());
    }

    @Test
    void testDelete_ShouldReturnFalseIfReceiptAbsent() {
        //given
        Cart testCart = getCartList().get(0);
        given(cartDao.delete(testCart.getBookId(), testCart.getUserId(), testCart.getId())).willThrow(new ServiceException("Cart id: %d created by user with id %d with book id: %d does`t found".formatted(testCart.getId(), testCart.getUserId(), testCart.getBookId())));

        //when
        boolean response = cartService.delete(testCart.getBookId(), testCart.getUserId(), testCart.getId());
        //then
        assertFalse(response, "If not found should be false");
        then(cartDao).should(never()).delete(testCart.getBookId(), testCart.getUserId(), testCart.getId());
    }

    private List<Cart> getCartList() {
        return new ArrayList<>(Arrays.asList(
                getCart(1, 2, 1, 1, "first book", "first.jpg", "first author", "first author"),
                getCart(2, 2, 2, 1, "second book", "second.jpg", "second author", "second author"),
                getCart(3, 3, 1, 2, "third book", "third.jpg", "third author", " third author")
        ));
    }

    private Cart getCart(
            long bookId,
            long userId,
            int quantity,
            int total,
            String title,
            String photoName,
            String authorFirstName,
            String authorLastName
    ) {
        return new Cart(
                bookId,
                userId,
                quantity,
                total,
                title,
                photoName,
                authorFirstName,
                authorLastName
        );
    }
}