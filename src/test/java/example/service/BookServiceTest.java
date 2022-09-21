package example.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import example.dto.BookDto;
import example.exception.EntityNotFoundException;
import example.model.Book;
import example.repository.BookRepository;

import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(PER_CLASS)
@ActiveProfiles("test")
@Tag("UnitTest")
@DisplayName("Book Service Unit Tests")
public class BookServiceTest {

    private BookRepository bookRepositoryMock;
    private BookService bookService;


    @BeforeAll
    public void init() {
        bookRepositoryMock = mock(BookRepository.class);
        bookService = new BookService(bookRepositoryMock, new ModelMapper());
    }

    
    @DisplayName("given Book id, when get Book, then Book is retrieved")
    void givenBookId_whenGetBook_ThenBookRetrieved() {

        //given
        long existingBookId = 0L;
  
        Book book1 = new Book();
        book1.setId(existingBookId);
        book1.setTitle("title");
        book1.setDescription("example");
        book1.setGenre("Terror");
        book1.setPrice(BigDecimal.TEN);
  
        
        when(bookRepositoryMock.findById(existingBookId)).thenReturn(Optional.of(book1));

        //when
        BookDto bookDto1 = bookService.get(existingBookId);

        //then
        assertNotNull(book1);
        assertNotNull(book1.getId());
        assertEquals(bookDto1.getId(), book1.getId());
    }

    
    @DisplayName("given Book id, when get non existing Book, then exception is thrown")
    void givenBookId_whenGetNonExistingBook_ThenExceptionThrown() {

        //given
        Long nonExistingBookId = 404L;
        String errorMsg = "Book Not Found : "+nonExistingBookId;
        when(bookRepositoryMock.findById(nonExistingBookId)).thenThrow(new EntityNotFoundException(errorMsg));

        //when
        EntityNotFoundException throwException = assertThrows(EntityNotFoundException.class, () ->  bookService.get(nonExistingBookId));

        // then
        assertEquals(errorMsg, throwException.getMessage());
    }

   
    @DisplayName("when list Book, then Books are retrieved")
    void whenListBooks_ThenBooksRetrieved() {

        //given
        long existingBookId = 0L;
        Book book1 = new Book();
        book1.setId(existingBookId);
        book1.setTitle("title");
        book1.setDescription("example");
        book1.setGenre("Terror");
        book1.setPrice(BigDecimal.TEN);
  
        when(bookRepositoryMock.findAll()).thenReturn(Arrays.asList(book1));

        //when
        List<BookDto> books = bookService.list();

        //then
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(book1.getId(), books.get(0).getId());
    }

    
    @DisplayName("given Book data, when create new Book, then Book id is returned")
    void givenBookData_whenCreateBook_ThenBookIdReturned() {

        //given
       
        BookDto  bookDto1 = new  BookDto(1L,"example","Terror","title",BigDecimal.TEN);
        Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setDescription("example");
        book1.setGenre("Terror");
        book1.setPrice(BigDecimal.TEN);
  

        //when
        when(bookRepositoryMock.save(any(Book.class))).thenReturn(book1);
        Long bookId1 = bookService.create(bookDto1);

        //then
        assertNotNull(bookId1);
        assertEquals(book1.getId(), bookId1);
    }

    
    @DisplayName("given Book incomplete data, when create new Book, then exception is thrown")
    void givenBookIncompleteData_whenCreateBook_ThenExceptionIsThrown() {

        //given
       
        BookDto  bookDto1 = new  BookDto("example","Terror","title",BigDecimal.TEN);
        Book book1 = new Book();
        book1.setTitle("title");
        book1.setDescription("example");
        book1.setGenre("Terror");
        book1.setPrice(BigDecimal.TEN);
        
        String errorMsg = "Unable to save an incomplete entity : "+bookDto1;

        //when
        when(bookRepositoryMock.save(book1)).thenThrow(new RuntimeException(errorMsg));
        RuntimeException throwException = assertThrows(RuntimeException.class, () ->  bookService.create(bookDto1));

        // then
        assertEquals(errorMsg, throwException.getMessage());
    }

}
