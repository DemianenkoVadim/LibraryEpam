package ua.com.epam.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.epam.library.dao.AuthorDao;
import ua.com.epam.library.entity.Author;
import ua.com.epam.library.exception.myexception.AuthorNotFoundException;
import ua.com.epam.library.service.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    private AuthorService authorService;

    @Mock
    private AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorService = new AuthorServiceImpl(authorDao);
    }

    @Test
    void testFindAuthorByFirstNameAndLastName_ShouldReturnSuccessfullAuthor() {
        //given
        String firstName = "Jacky";
        String lastName = "Hammer";
        given(authorDao.findByFirstNameAndLastName(firstName, lastName)).willReturn(Optional.of(new Author()));

        //when
        when(authorDao.findByFirstNameAndLastName(firstName, lastName)).thenReturn(Optional.of(new Author()));
        Optional<Author> authorOptional = authorService.findAuthorByFirstNameAndLastName(firstName, lastName);

        //then
        assertTrue(authorOptional.isPresent(), "Author should be present");
        verify(authorDao).findByFirstNameAndLastName(firstName, lastName);
    }

    @Test
    void testFindAuthorByFirstNameAndLastname_ShouldReturnThrownWithTwoNullParameters() {
        //given
        given(authorDao.findByFirstNameAndLastName(null, null))
                .willThrow(new AuthorNotFoundException("Author with first name: null and last name null is not found"));
        //when
        //then
        assertThrows(AuthorNotFoundException.class, () -> Optional
                .ofNullable(authorService.findAuthorByFirstNameAndLastName(null, null))
                .orElseThrow(() -> new AuthorNotFoundException("Author with first name: null and last name null is not found")));
    }

    @Test
    void testFindAuthorByFirstNameAndLastname_ShouldReturnThrownWithOneNullParameter() {
        //given
        String firstName = "Jack";
        given(authorDao.findByFirstNameAndLastName(firstName, null))
                .willThrow(new AuthorNotFoundException("Author with first name: %s and last name null is not found".formatted(firstName)));
        //when
        //then
        assertThrows(AuthorNotFoundException.class, () -> Optional
                .ofNullable(authorService.findAuthorByFirstNameAndLastName(firstName, null))
                .orElseThrow(() -> new AuthorNotFoundException("Author with first name: %s and last name null is not found".formatted(firstName))));
    }

    @Test
    void getTestFindAuthorByFirstNameAndLastName_ShouldReturnThrownWithEmptyParameters() {
        //given
        String firstName = "";
        String lastName = "";
        given(authorDao.findByFirstNameAndLastName(firstName, lastName))
                .willThrow(new AuthorNotFoundException("Author with first name: empty first name and last name: empty last name is not found"));
        //when
        //then
        assertThrows(AuthorNotFoundException.class, () -> Optional
                .ofNullable(authorService.findAuthorByFirstNameAndLastName(firstName, lastName))
                .orElseThrow(() -> new AuthorNotFoundException("Author with first name: empty first name and last name: empty last name is not found")));
    }

    @Test
    void testFindAuthorByFirstNameAndLastName_AuthorDoesNotExist_ShouldThrownException() {
        //given
        Author testAuthor = getAuthor("Jake", "Simpson");

        given(authorDao.findByFirstNameAndLastName(anyString(), anyString()))
                .willThrow(new AuthorNotFoundException("Author with first name: %s and last name %s is not found".formatted(anyString(), anyString())));

        //when
        assertThatThrownBy(() -> authorService.findAuthorByFirstNameAndLastName(testAuthor.getFirstName(), testAuthor.getLastName()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Author with first name:  and last name  is not found");
    }

    @Test
    void testFindAuthorFirstName_ShouldReturnListOfAuthors() {
        List<Author> authors = getListOfTestAuthors();

        //when
        when(authorDao.findAuthorFirstNames(authors.get(0).getFirstName())).thenReturn(getListOfTestAuthors());

        List<Author> authorsFirstNames = authorService.findAuthorFirstName(authors.get(0).getFirstName());

        //then
        assertThat(authorsFirstNames.isEmpty()).isFalse();

        verify(authorDao).findAuthorFirstNames(authors.get(0).getFirstName());
    }

    @Test
    void testFindAuthorFirstName_NameNotExist_ShouldReturnEmptyListOfAuthors() {

        //when
        when(authorDao.findAuthorFirstNames(anyString())).thenReturn(List.of());

        List<Author> authorsByFirstName = authorService.findAuthorFirstName(anyString());

        //then
        assertThat(authorsByFirstName.isEmpty()).isTrue();

        verify(authorDao).findAuthorFirstNames(anyString());
    }

    @Test
    void testFindAuthorLastNames_ShouldReturnListOfAuthors() {
        List<Author> authors = getListOfTestAuthors();

        //when
        when(authorDao.findAuthorLastNames(authors.get(0).getLastName())).thenReturn(getListOfTestAuthors());

        List<Author> authorLastnames = authorService.findAuthorLastName(authors.get(0).getLastName());

        //then
        assertThat(authorLastnames.isEmpty()).isFalse();

        verify(authorDao).findAuthorLastNames(authorLastnames.get(0).getLastName());
    }

    @Test
    void testFindAuthorLastNames_LastNameNotExist_ShouldReturnEmptyListOfAuthors(){
        //when
        when(authorDao.findAuthorLastNames(anyString())).thenReturn(List.of());

        List<Author> authorsByLastName = authorService.findAuthorLastName(anyString());

        //then
        assertThat(authorsByLastName.isEmpty()).isTrue();

        verify(authorDao).findAuthorLastNames(anyString());
    }

    private List<Author> getListOfTestAuthors() {
        List<Author> testsAuthor = new ArrayList<>();
        testsAuthor.add(getAuthor("Fist Author", "First Last"));
        testsAuthor.add(getAuthor("Second Author", "Second Last"));
        testsAuthor.add(getAuthor("Third Author", "Third Last"));
        return testsAuthor;
    }

    private Author getAuthor(String firstName, String lastName) {
        return new Author(firstName, lastName, new ArrayList<>());
    }
}