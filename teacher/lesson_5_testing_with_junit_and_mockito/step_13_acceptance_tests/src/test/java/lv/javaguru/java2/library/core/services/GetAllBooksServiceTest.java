package lv.javaguru.java2.library.core.services;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.requests.GetAllBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllBooksResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAllBooksServiceTest {

	@Mock private Database database;
	@InjectMocks private GetAllBooksService service;

	@Test
	public void shouldGetBooksFromDb() {
		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author"));
		Mockito.when(database.getAllBooks()).thenReturn(books);

		GetAllBooksRequest request = new GetAllBooksRequest();
		GetAllBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author");
	}

}