package lesson_3_request_response_input_data_validation.code.before.database;

import lesson_3_request_response_input_data_validation.code.before.Book;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
