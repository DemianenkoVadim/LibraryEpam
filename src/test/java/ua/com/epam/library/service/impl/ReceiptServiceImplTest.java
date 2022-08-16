package ua.com.epam.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.powermock.core.classloader.annotations.PrepareForTest;
import ua.com.epam.library.dao.BookDao;
import ua.com.epam.library.dao.ReceiptDao;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.entity.Receipt;
import ua.com.epam.library.entity.Rent;
import ua.com.epam.library.entity.Stage;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.ReceiptService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static ua.com.epam.library.entity.Rent.READING_ROOM;
import static ua.com.epam.library.entity.Rent.SUBSCRIPTION;
import static ua.com.epam.library.entity.Stage.PENDING;
import static ua.com.epam.library.entity.Stage.READING;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReceiptServiceImplTest {

    @Mock
    private ReceiptDao receiptDao;
    @Mock
    private BookDao bookDao;

    ReceiptService receiptService;

    @BeforeEach
    void setUp() {
        receiptService = new ReceiptServiceImpl(receiptDao, bookDao);
    }

    @Test
    @PrepareForTest(ReceiptServiceImpl.class)
    void testAddReceipt_ShouldReturnTrue() throws Exception {
        Receipt receipt = getReceiptList().get(0);

        Book testBook = new Book();
        testBook.setQuantity(3);

        //when
        when(receiptDao.addReceipt(anyList())).thenReturn(true);
        when(bookDao.changeQuantityBooksInLibraryWhenBookIsGivenToUser(receipt.getBookId(), 3)).thenReturn(true);
        when(bookDao.getBookById(anyLong())).thenReturn(Optional.of(testBook));


        boolean receiptIsAdded = receiptService.addReceipt(getReceiptList());

        //then
        assertTrue(receiptIsAdded);
        verify(receiptDao, times(1)).addReceipt(anyList());
    }

    @Test
    void testProceedReceipt_ShouldReturnTrue() {
        //given
        Receipt receipts = getReceiptList().get(0);
        given(receiptDao.proceedReceipt(receipts.getBookId(), receipts.getUserId(), receipts.getId())).willReturn(true);

        //when
        when(receiptDao.proceedReceipt(receipts.getBookId(), receipts.getUserId(), receipts.getId())).thenReturn(true);
        boolean isReceiptProceed = receiptService.proceedReceipt(receipts.getBookId(), receipts.getUserId(), receipts.getId());

        //then
        assertTrue(isReceiptProceed);

        verify(receiptDao).proceedReceipt(receipts.getBookId(), receipts.getUserId(), receipts.getId());
    }

    @Test
    void testLendBookToUser_ShouldReturnTrue() {
        //given
        Receipt receipts = getReceiptList().get(0);

        //when
        when(receiptDao.lendBookToUser(anyLong(), anyLong(), anyLong(), any(), any())).thenReturn(true);

        boolean isBookLendToUser = receiptService.lendBookToUser(receipts.getBookId(), receipts.getUserId(), receipts.getId());

        //then
        assertTrue(isBookLendToUser);
        verify(receiptDao).lendBookToUser(anyLong(), anyLong(), anyLong(), any(), any());
    }

    @Test
    void returnBookFromUser_ShouldReturnTrue() {
        //given
        Receipt receipts = getReceiptList().get(0);
        receipts.setId(2);

        Book testBook = new Book();
        testBook.setQuantity(3);

        Receipt receipt = new Receipt();
        receipt.setPenalty(0.0);

        receipt.setRealReturnDate(LocalDateTime.now().plusMonths(1));
        receipt.setEstimateReturnDate(LocalDateTime.now());
        receipt.setReceivingDate(LocalDateTime.now());

        //when
        when(receiptDao.returnBookFromUser(anyLong(), anyLong(), anyLong(), any(), anyDouble())).thenReturn(true);
        when(receiptDao.getRealReturnedDateTime(receipts.getBookId(), receipts.getUserId(), receipts.getId())).thenReturn(Optional.of(receipt));
        when(receiptDao.getEstimateDateTime(receipts.getBookId(), receipts.getUserId(), receipts.getId())).thenReturn(Optional.of(receipt));
        when(bookDao.changeQuantityBooksInLibraryWhenBookIsReturnedByUser(receipts.getBookId(), 3)).thenReturn(true);
        when(bookDao.getBookById(receipts.getBookId())).thenReturn(Optional.of(testBook));

        boolean bookReturned = receiptService.returnBookFromUser(receipts.getBookId(), receipts.getUserId(), receipts.getId());

        //then
        assertTrue(bookReturned);
        verify(receiptDao).returnBookFromUser(anyLong(), anyLong(), anyLong(), any(), anyDouble());
    }

    @Test
    void testChangeQuantityBooksInLibraryWhenBooksAreOrdered_ShouldReturnTrue() {
        //given
        Receipt receipts = getReceiptList().get(0);
        Book testBook = new Book();
        testBook.setQuantity(3);
        //when
        when(bookDao.changeQuantityBooksInLibraryWhenBookIsGivenToUser(anyLong(), anyInt())).thenReturn(true);
        when(bookDao.getBookById(receipts.getBookId())).thenReturn(Optional.of(testBook));

        boolean bookOrdered = receiptService.changeQuantityBooksInLibraryWhenBooksAreOrdered(receipts.getBookId());

        //then
        assertTrue(bookOrdered);
        verify(bookDao).changeQuantityBooksInLibraryWhenBookIsGivenToUser(anyLong(), anyInt());
    }

    @Test
    void testChangeQuantityBooksInLibraryWhenBooksAreOrdered_BookIdIsNotFound_ShouldThrownException() {
        //give
        Receipt receipts = getReceiptList().get(0);
        given(bookDao.getBookById(receipts.getBookId())).willThrow(new ServiceException("Book with id: %s does`t found".formatted(receipts.getBookId())));

        //when
        //then
        assertThatThrownBy(() -> receiptService.changeQuantityBooksInLibraryWhenBooksAreOrdered(receipts.getBookId()))
                .isInstanceOf(ServiceException.class)
                .hasMessageContaining("Book with id: %s does`t found".formatted(receipts.getBookId()));
    }

    @Test
    void testChangeQuantityBooksInLibraryWhenBooksAreOrdered_ShouldThrownException() {
        //given
        int booksQuantityInLibrary = 0;
        Receipt receipts = getReceiptList().get(0);

        given(bookDao.getBookById(receipts.getBookId())).willReturn(Optional.of(new Book()));
        given(bookDao.changeQuantityBooksInLibraryWhenBookIsGivenToUser(receipts.getBookId(), booksQuantityInLibrary))
                .willThrow(new IllegalStateException("Can`t change total quantity books with id: %s in library, because there are no copies".formatted(receipts.getBookId())));

        //when
        assertThatThrownBy(() -> receiptService.changeQuantityBooksInLibraryWhenBooksAreOrdered(receipts.getBookId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Can`t change total quantity books with id: %s in library, because there are no copies".formatted(receipts.getBookId()));

        //then
        then(bookDao).should(never()).changeQuantityBooksInLibraryWhenBookIsGivenToUser(receipts.getBookId(), booksQuantityInLibrary);
    }

    @Test
    void testChangeQuantityBooksInLibraryWhenBooksIsReturnedByUser_ShouldReturnTrue() {
        //given
        int booksQuantityInLibrary = 1;
        Receipt receipts = getReceiptList().get(0);

        given(bookDao.getBookById(receipts.getBookId())).willReturn(Optional.of(new Book()));
        given(bookDao.changeQuantityBooksInLibraryWhenBookIsReturnedByUser(receipts.getBookId(), booksQuantityInLibrary)).willReturn(true);

        //when
        when(bookDao.getBookById(receipts.getBookId())).thenReturn(Optional.of(new Book()));
        when(bookDao.changeQuantityBooksInLibraryWhenBookIsReturnedByUser(receipts.getBookId(), booksQuantityInLibrary)).thenReturn(true);

        boolean totalQuantity = receiptService.changeQuantityBooksInLibraryWhenBooksIsReturnedByUser(receipts.getBookId());

        //then
        assertThat(totalQuantity).isTrue();

        verify(bookDao).getBookById(receipts.getBookId());
        verify(bookDao).changeQuantityBooksInLibraryWhenBookIsReturnedByUser(receipts.getBookId(), booksQuantityInLibrary);

    }

    @Test
    void testGetAllUsersDescriptionReceipts_ShouldReturnListAllUsersDescription() {
        //given
        List<Receipt> receipts = getReceiptList();

        List<Receipt> allSubscription = receipts.stream()
                .filter(subscription -> subscription.getRent().equals(SUBSCRIPTION))
                .collect(Collectors.toList());

        given(receiptDao.getAllUsersDescriptionReceipts()).willReturn(allSubscription);

        //when
        List<Receipt> allUserSubscription = receiptService.getAllUsersDescriptionReceipts();

        //then
        assertThat(allUserSubscription).isNotNull();
        assertThat(allUserSubscription).isEqualTo(allSubscription);

        verify(receiptDao).getAllUsersDescriptionReceipts();
    }

    @Test
    void testGetAllUsersDescriptionReceipts_ShouldReturnSizeFindsListAllUsersDescriptionReceipts() {
        //given
        List<Receipt> receipts = getReceiptList();
        long sizeListAllUsersDescriptionReceipts = receipts
                .stream()
                .filter(description -> description.getRent().equals(SUBSCRIPTION))
                .count();

        given(receiptDao.getAllUsersDescriptionReceipts()).willReturn(receipts);

        //when
        long listSize = receiptService
                .getAllUsersDescriptionReceipts()
                .stream()
                .filter(description -> description.getRent().equals(SUBSCRIPTION))
                .count();

        //then
        assertThat(listSize).isNotNull();
        assertThat(listSize).isEqualTo(sizeListAllUsersDescriptionReceipts);

        verify(receiptDao).getAllUsersDescriptionReceipts();
    }

    @Test
    void testGetAllUsersDescriptionReceipt_ShouldReturnEmptyList() {
        //given
        List<Receipt> receipts = new ArrayList<>();
        given(receiptDao.getAllUsersDescriptionReceipts()).willReturn(receipts);

        //when
        boolean isListAllUsersDescriptionReceiptEmpty = receiptService.getAllUsersDescriptionReceipts().isEmpty();

        //then
        assertThat(isListAllUsersDescriptionReceiptEmpty).isNotNull();
        assertThat(isListAllUsersDescriptionReceiptEmpty).isEqualTo(receipts.isEmpty());

        verify(receiptDao).getAllUsersDescriptionReceipts();
    }

    @Test
    void testGetAllUsersReceipts_ShouldReturnAllUsersReceiptsList() {
        //given
        List<Receipt> receipts = getReceiptList();
        given(receiptDao.getAllUsersReceipts()).willReturn(receipts);

        //when
        List<Receipt> allReceipts = receiptService.getAllUsersReceipts();

        //then
        assertThat(allReceipts).isNotNull();
        assertThat(allReceipts).isEqualTo(receipts);

        verify(receiptDao).getAllUsersReceipts();
    }

    @Test
    void testGetAllUsersReceipt_ShouldReturnSizeOfListAllUserReceipts() {
        //given
        List<Receipt> receipts = getReceiptList();
        given(receiptDao.getAllUsersReceipts()).willReturn(receipts);

        //when
        int listAllUsersReceipts = receiptService.getAllUsersReceipts().size();

        //then
        assertThat(listAllUsersReceipts).isNotNull();
        assertThat(listAllUsersReceipts).isEqualTo(receipts.size());

        verify(receiptDao).getAllUsersReceipts();
    }

    @Test
    void testGetAllUsersReceipt_ShouldReturnEmptyList() {
        //given
        List<Receipt> receipts = new ArrayList<>();
        given(receiptDao.getAllUsersReceipts()).willReturn(receipts);

        //when
        boolean isListAllUsersReceiptEmpty = receiptService.getAllUsersReceipts().isEmpty();

        //then
        assertThat(isListAllUsersReceiptEmpty).isNotNull();
        assertThat(isListAllUsersReceiptEmpty).isEqualTo(receipts.isEmpty());

        verify(receiptDao).getAllUsersReceipts();
    }

    private Receipt getReceipt(String receiptNumber,
                               Stage stage,
                               Rent rent,
                               LocalDateTime receivingDate,
                               LocalDateTime estimateReturnDate,
                               LocalDateTime realReturnDate,
                               double penalty,
                               long userId,
                               long bookId,
                               long userOrderId,
                               String title,
                               String email,
                               String photoName,
                               String authorFirstName,
                               String authorLastName,
                               Book book) {
        return new Receipt(
                receiptNumber,
                stage,
                rent,
                receivingDate,
                estimateReturnDate,
                realReturnDate,
                penalty,
                userId,
                bookId,
                userOrderId,
                title,
                email,
                photoName,
                authorFirstName,
                authorLastName,
                book);
    }

    private List<Receipt> getReceiptList() {
        return new ArrayList<>(Arrays.asList(
                getReceipt("1", READING, READING_ROOM,
                        LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 0, 2, 3, 1,
                        "First Book", "first@gmial.com", "first.jpg", "first author", "second author", new Book()),
                getReceipt("2", PENDING, SUBSCRIPTION,
                        LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 1, 3, 4, 2,
                        "Second Book", "second@gmail.com", "second.jpg", "second author", "second author", new Book()),
                getReceipt("3", PENDING, SUBSCRIPTION,
                        LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 0, 2, 3, 1,
                        "Third Book", "third@gmail.com", "third.jpg", "third author", "third author", new Book())
        ));
    }
}