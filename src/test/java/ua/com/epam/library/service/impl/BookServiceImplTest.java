package ua.com.epam.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ua.com.epam.library.dao.BookDao;
import ua.com.epam.library.entity.Author;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.entity.Genre;
import ua.com.epam.library.entity.PublishingHouse;
import ua.com.epam.library.exception.myexception.BookNotFoundException;
import ua.com.epam.library.service.AuthorService;
import ua.com.epam.library.service.BookService;
import ua.com.epam.library.service.GenreService;
import ua.com.epam.library.service.PublishingHouseService;
import ua.com.epam.library.servlet.admin.request.AddBookRequest;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookServiceImplTest {

    private BookService bookService;
    @Mock
    private BookDao bookDao;
    @Mock
    private GenreService genreService;
    @Mock
    private AuthorService authorService;
    @Mock
    private PublishingHouseService publishingHouseService;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookDao, genreService, authorService, publishingHouseService);
    }

    @Test
    void testAddBook_ShouldAddBook() throws SQLException {
        AddBookRequest addBookRequest = getBookRequest();
        Genre genre = new Genre();
        Author author = new Author();
        PublishingHouse publishingHouse = new PublishingHouse();

        //when
        when(bookDao.addBook(any(Book.class))).thenReturn(true);
        when(genreService.findByName(anyString())).thenReturn(Optional.of(genre));
        when(authorService.findAuthorByFirstNameAndLastName(anyString(), anyString())).thenReturn(Optional.of(author));
        when(publishingHouseService.findByName(anyString())).thenReturn(Optional.of(publishingHouse));

        boolean bookCreated = bookService.addBook(addBookRequest);

        //then
        assertTrue(bookCreated);
        verify(bookDao).addBook(any(Book.class));
    }

//    @Test
//    void updateBook_ShouldUpdateBook() {
//        // given
//        Book book = getBook("Books", "10.10.2010",
//                2, "description",
//                "photo.jpg", 2, "romance",
//                3, "Jack", "London",
//                1, "Black Swan",
//                new ArrayList<>());
//
//        // when
////        bookService.updateBook(1, "Books", "10.10.2010",
////                2, "description",
////                2, "romance",
////                3, "Jack", "London",
////                1, "Black Swan",
////                "photo.jpg");
//
//        // then
//        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
//        verify(bookDao).updateBook(bookArgumentCaptor.capture());
//
//        Book capturedBook = bookArgumentCaptor.getValue();
//        assertThat(capturedBook).isEqualTo(book);
//        verify(bookDao, times(1)).updateBook(book);
//    }

    @Test
    void testDelete_ShouldReturnTrue() {
        //given
        long id = 19;
        String title = "SeventhBook";
        String published = "22.12.2012";
        int quantity = 1;
        String description = "seventhDescription";
        String photoName = "seventh.jpg";
        long genreId = 1;
        String genreName = "classic";
        long authorId = 3;
        String authorFirstName = "thirdFirstName";
        String authorLastName = "thirdLastName";
        long publishingHouseId = 4;
        String publishingHouseName = "FourthHouse";

        Book testBook = createTestBook(id, title, published, quantity, description, photoName,
                genreId, genreName, authorId, authorFirstName, authorLastName,
                publishingHouseId, publishingHouseName);

        given(bookDao.getBookById(id)).willReturn(Optional.of(testBook));
        given(bookDao.delete(id)).willReturn(true);

        //when
        when(bookDao.getBookById(id)).thenReturn(Optional.of(testBook));
        when(bookDao.delete(id)).thenReturn(true);

        boolean bookIsDeleted = bookService.delete(id);

        //then
        assertTrue(bookIsDeleted);

        verify(bookDao, times(1)).getBookById(id);
        verify(bookDao, times(1)).delete(id);
    }

    @Test
    void testDelete_WrongBookId_ShouldThrownException() {
        //given
        given(bookDao.delete(anyLong())).willThrow(new BookNotFoundException("Book with id is not found"));
        //when
        //then
        assertThatThrownBy(() -> bookService.delete(2))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Book with id: %d does not found".formatted(2));

        then(bookDao).should(never()).delete(anyLong());
    }


    @Test
    void testGetAll_ShouldReturnListOfAllBooks() {
        //given
        List<Book> books = getBookList();

        given(bookDao.getAll()).willReturn(books);

        //when
        List<Book> allBooks = bookService.getAll();

        //then
        assertThat(allBooks).isNotNull();
        assertThat(allBooks).isEqualTo(books);

        verify(bookDao).getAll();
    }

    @Test
    void testGetAll_ShouldReturnSizeOfListAllBooks() {
        //given
        List<Book> books = getBookList();

        given(bookDao.getAll()).willReturn(books);

        //when
        int listBooksSize = bookService.getAll().size();

        //then
        assertThat(listBooksSize).isNotNull();
        assertThat(listBooksSize).isEqualTo(books.size());

        verify(bookDao).getAll();
    }

    @Test
    void testGetAll_EmptyListBook_ShouldReturnEmptyList() {
        // given
        List<Book> books = new ArrayList<>();
        given(bookDao.getAll()).willReturn(books);

        // when
        boolean isListBooksEmpty = bookService.getAll().isEmpty();

        // then
        assertThat(isListBooksEmpty).isNotNull();
        assertThat(isListBooksEmpty).isEqualTo(books.isEmpty());

        verify(bookDao).getAll();
    }

    @Test
    void testGetAllBooksByGenre_ShouldReturnListBooksByGenre() {
        // given
        List<Book> books = getBookList();

        List<Book> allClassic = books.stream()
                .filter(classic -> classic.getGenreName().equals("classic"))
                .collect(Collectors.toList());

        given(bookDao.getAllBooksByGenre("classic")).willReturn(allClassic);

        // when
        List<Book> allClassicBooks = bookService.getAllBooksByGenre("classic");

        //then
        assertThat(allClassicBooks).isNotNull();
        assertThat(allClassicBooks).isEqualTo(allClassic);

        verify(bookDao).getAllBooksByGenre("classic");
    }

    @Test
    void testGetAllBookByGenre_ShouldReturnSizeFindsListOfFoundBooksByGenre() {
        // given
        List<Book> books = getBookList();
        long sizeListOfAllClassic = books
                .stream()
                .filter(classic -> classic.getGenreName().equals("classic"))
                .count();

        given(bookDao.getAllBooksByGenre("classic")).willReturn(books);

        //when
        long listSize = bookService
                .getAllBooksByGenre("classic")
                .stream()
                .filter(classic -> classic.getGenreName().equals("classic"))
                .count();

        //then
        assertThat(listSize).isNotNull();
        assertThat(listSize).isEqualTo(sizeListOfAllClassic);

        verify(bookDao).getAllBooksByGenre("classic");
    }

    @Test
    void testGetAllBookByGenre_EmptyListBooks_ReturnEmptyList() {
        //given
        List<Book> books = new ArrayList<>();
        given(bookDao.getAllBooksByGenre("classic")).willReturn(books);

        //when
        boolean isListBooksEmpty = bookService.getAllBooksByGenre("classic").isEmpty();

        //then
        assertThat(isListBooksEmpty).isNotNull();
        assertThat(isListBooksEmpty).isEqualTo(books.isEmpty());

        verify(bookDao).getAllBooksByGenre("classic");
    }

    @Test
    void testGetBookById_ShouldReturnBook() {
        //given
        long id = 19;
        String title = "SeventhBook";
        String published = "22.12.2012";
        int quantity = 1;
        String description = "seventhDescription";
        String photoName = "seventh.jpg";
        long genreId = 1;
        String genreName = "classic";
        long authorId = 3;
        String authorFirstName = "thirdFirstName";
        String authorLastName = "thirdLastName";
        long publishingHouseId = 4;
        String publishingHouseName = "FourthHouse";

        Book testBook = createTestBook(id, title, published, quantity, description, photoName,
                genreId, genreName, authorId, authorFirstName, authorLastName,
                publishingHouseId, publishingHouseName);

        given(bookDao.getBookById(id)).willReturn(Optional.of(testBook));

        //when
        Optional<Book> optionalBook = bookService.getBookById(id);

        //then
        assertThat(optionalBook.isPresent()).isTrue();
        Book book = optionalBook.get();

        assertBookFields(book);

        verify(bookDao, times(1)).getBookById(id);
    }

    @Test
    void testGetById_ShouldThrownException() {
        //given
        long id = -1;
        given(bookDao.getBookById(id)).willThrow(new BookNotFoundException("Book with id %d is not found".formatted(id)));

        //when
        //then
        assertThrows(BookNotFoundException.class, () -> Optional
                .ofNullable(bookService.getBookById(id))
                .orElseThrow(() -> new BookNotFoundException("Book with id %d is not found".formatted(id))));

        verify(bookDao).getBookById(id);
    }

    @Test
    void testSort_ShouldReturnSortedListByTitleAsc() {
        //given
        int page = 1;
        String sortCriteria = "titleAsc";
        List<Book> books = getBookList();
        List<Book> sortedByTitleAsc = books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
        System.out.println("sortedByTitleAsc = " + sortedByTitleAsc);
        given(bookDao.sort(sortCriteria, page)).willReturn(sortedByTitleAsc);

        //when
        List<Book> byTitleAsc = bookService.sort(sortCriteria, page);
        System.out.println("byTitleAsc = " + byTitleAsc);

        //then
        assertThat(byTitleAsc).isEqualTo(sortedByTitleAsc);
        verify(bookDao).sort(sortCriteria, page);
    }

    @Test
    void testSort_ShouldReturnSortedListByTitleDesc() {
        //given
        int page = 1;
        String sortCriteria = "titleDesc";
        List<Book> books = getBookList();
        List<Book> sortedByTitleDesc = books.stream()
                .sorted(Comparator.comparing(Book::getTitle).reversed())
                .collect(Collectors.toList());

        given(bookDao.sort(sortCriteria, page)).willReturn(sortedByTitleDesc);

        //when
        List<Book> byTitleDesc = bookService.sort(sortCriteria, page);

        //then
        assertThat(byTitleDesc).isEqualTo(sortedByTitleDesc);
        verify(bookDao).sort(sortCriteria, page);
    }

    @Test
    void testSort_WhenSortCriteriaEmpty_ShouldReturnList() {
        //given
        int page = 1;
        String sortCriteria = "";
        List<Book> books = getBookList();

        given(bookDao.sort(sortCriteria, page)).willReturn(books);

        //when
        List<Book> byTitleDesc = bookService.sort(sortCriteria, page);

        //then
        assertThat(byTitleDesc).isEqualTo(books);
        verify(bookDao).sort(sortCriteria, page);
    }

    @Test
    void testGetBookByTitleAndAuthorSearch_SearchBySpecifiedParameter_ShouldReturnListOfFindingBooks() {
        //given
        String searchParameter = "fir";
        List<Book> books = getBookList();
        List<Book> booksBySearchParameter = books.stream()
                .filter(book -> book.getTitle().contains(searchParameter) ||
                        book.getAuthorFirstName().contains(searchParameter) ||
                        book.getAuthorLastName().contains(searchParameter))
                .collect(Collectors.toList());
        given(bookDao.getBookByTitleAndAuthorSearch(searchParameter)).willReturn(booksBySearchParameter);

        //when
        List<Book> bySearchParameter = bookService.getBookByTitleAndAuthorSearch(searchParameter);

        //then
        assertThat(bySearchParameter).isEqualTo(booksBySearchParameter);
        verify(bookDao).getBookByTitleAndAuthorSearch(searchParameter);
    }

    @Test
    void testGetBookByTitleAndAuthorSearch_SearchBySpecifiedParameter_ShouldReturnEmptyList() {
        //given
        String searchParameter = "Ten";
        List<Book> books = getBookList();
        List<Book> booksBySearchParameter = books.stream()
                .filter(book -> book.getTitle().contains(searchParameter) ||
                        book.getAuthorFirstName().contains(searchParameter) ||
                        book.getAuthorLastName().contains(searchParameter))
                .collect(Collectors.toList());
        given(bookDao.getBookByTitleAndAuthorSearch(searchParameter)).willReturn(booksBySearchParameter);

        //when
        boolean emptyList = bookService.getBookByTitleAndAuthorSearch(searchParameter).isEmpty();

        //then
        assertTrue(emptyList);

        verify(bookDao).getBookByTitleAndAuthorSearch(searchParameter);
    }

    @Test
    void testGetSize_ShouldReturnSizeList() {
        //given
        List<Book> books = getBookList();
        given(bookDao.getSize()).willReturn((double) books.size());

        //when
        when(bookDao.getSize()).thenReturn((double) books.size());
        double size = bookService.getSize();

        //then
        assertThat(size).isEqualTo(books.size());
        verify(bookDao).getSize();
    }

    @Test
    void testGetSize_ListIsEmpty_ShouldReturnZero() {
        //given
        given(bookDao.getSize()).willReturn(0.0);

        //when
        when(bookDao.getSize()).thenReturn(0.0);
        double size = bookService.getSize();

        //then
        assertThat(size).isEqualTo(0.0);
        verify(bookDao).getSize();
    }

    @Test
    void testFindBookByTitle_ShouldReturnBook() {
        //when
        when(bookDao.findBookByTitle(anyString())).thenReturn(Optional.of(new Book()));

        Optional<Book> optionalBook = bookService.findBookByTitle(anyString());

        //then
        assertThat(optionalBook.isPresent()).isTrue();

        verify(bookDao).findBookByTitle(anyString());
    }

    private AddBookRequest getBookRequest() {
        AddBookRequest bookRequest = new AddBookRequest();
        bookRequest.setTitle("Title");
        bookRequest.setQuantity(3);
        bookRequest.setPublishingHouse("Black Swan");
        bookRequest.setPublished("10.10.2021");
        bookRequest.setDescription("description");
        bookRequest.setGenre("Classic");
        bookRequest.setAuthorFirstName("First Name");
        bookRequest.setAuthorLastName("Last Name");
        bookRequest.setBookImage("image");
        return bookRequest;
    }

    private Book getBook(String title, String published, int quantity,
                         String description, String photoName,
                         long genreId, String genreName,
                         long authorId, String authorFirstName, String authorLastName,
                         long publishingHouseId, String publishingHouseName, List<Author> authors) {
        return new Book(title, published, quantity,
                description, photoName,
                genreId, genreName,
                authorId, authorFirstName, authorLastName,
                publishingHouseId, publishingHouseName, authors
        );
    }

    private Book createTestBook(long id, String title, String published, int quantity,
                                String description, String photoName,
                                long genreId, String genreName,
                                long authorId, String authorFirstName, String authorLastName,
                                long publishingHouseId, String publishingHouseName
    ) {
        Book testBook = new Book();
        testBook.setId(id);
        testBook.setTitle(title);
        testBook.setPublished(published);
        testBook.setQuantity(quantity);
        testBook.setDescription(description);
        testBook.setPhotoName(photoName);
        testBook.setGenreId(genreId);
        testBook.setGenreName(genreName);
        testBook.setAuthorsId(authorId);
        testBook.setAuthorFirstName(authorFirstName);
        testBook.setAuthorLastName(authorLastName);
        testBook.setPublishingHouseId(publishingHouseId);
        testBook.setPublishingHouseName(publishingHouseName);
        return testBook;
    }

    private List<Book> getBookList() {
        return new ArrayList<>(Arrays.asList(
                getBook("First book", "10.10.2010", 2, "firstDescription", "first.jpg", 1, "classic", 1, "firstNameAuthor", "firstLastName", 1, "FirstHouse", new ArrayList<>()),
                getBook("Second book", "11.10.2010", 3, "secondDescription", "second.jpg", 2, "romance", 2, "secondNameAuthor", "secondLastname", 2, "SecondHouse", new ArrayList<>()),
                getBook("Third book", "12.10.2010", 3, "thirdDescription", "third.jpg", 3, "fantasy", 1, "firstNameAuthor", "firstLastName", 3, "ThirdHouse", new ArrayList<>()),
                getBook("Fourth book", "13.11.2010", 5, "fourthDescription", "fourth.jpg", 1, "classic", 2, "secondNameAuthor", "secondLastname", 1, "FirstHouse", new ArrayList<>()),
                getBook("Fives book", "14.11.2010", 1, "fifthDescription", "fifth.jpg", 2, "romance", 1, "firstNameAuthor", "firstLastName", 2, "SecondHouse", new ArrayList<>()),
                getBook("Sixth book", "15.11.2010", 4, "sixthDescription", "sixth.jpg", 3, "fantasy", 2, "secondNameAuthor", "secondLastname", 3, "ThirdHouse", new ArrayList<>())));
    }

    private void assertBookFields(Book book) {
        assertThat(book.getId()).isNotNull();
        assertThat(book.getId()).isEqualTo(19);
        assertThat(book.getTitle()).isEqualTo("SeventhBook");
        assertThat(book.getPublished()).isEqualTo("22.12.2012");
        assertThat(book.getQuantity()).isEqualTo(1);
        assertThat(book.getDescription()).isEqualTo("seventhDescription");
        assertThat(book.getPhotoName()).isEqualTo("seventh.jpg");
        assertThat(book.getGenreId()).isEqualTo(1);
        assertThat(book.getGenreName()).isEqualTo("classic");
        assertThat(book.getAuthorsId()).isEqualTo(3);
        assertThat(book.getAuthorFirstName()).isEqualTo("thirdFirstName");
        assertThat(book.getAuthorLastName()).isEqualTo("thirdLastName");
        assertThat(book.getPublishingHouseId()).isEqualTo(4);
        assertThat(book.getPublishingHouseName()).isEqualTo("FourthHouse");
    }


}