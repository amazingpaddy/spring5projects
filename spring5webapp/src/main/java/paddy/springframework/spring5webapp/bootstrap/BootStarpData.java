package paddy.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import paddy.springframework.spring5webapp.domain.Author;
import paddy.springframework.spring5webapp.domain.Book;
import paddy.springframework.spring5webapp.domain.Publisher;
import paddy.springframework.spring5webapp.repositories.AuthorRepository;
import paddy.springframework.spring5webapp.repositories.BookRepository;
import paddy.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStarpData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStarpData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher paddyPublisher = new Publisher();
        paddyPublisher.setName("Paddy Publication");
        paddyPublisher.setCity("Mississauga");
        paddyPublisher.setState("Ontario");
        publisherRepository.save(paddyPublisher);

        Author paddy = new Author("Padmanabhan", "Vijendran");
        Book paddyBook = new Book("Show your Work", "12345");
        paddy.getBooks().add(paddyBook);
        paddyBook.getAuthors().add(paddy);
        paddyBook.setPublisher(paddyPublisher);
        paddyPublisher.getBooks().add(paddyBook);

        authorRepository.save(paddy);
        bookRepository.save(paddyBook);
        publisherRepository.save(paddyPublisher);

        Author uma = new Author("Uma", "Gopalan");
        Book umaBook = new Book("Siddhartha", "256423");
        uma.getBooks().add(umaBook);
        umaBook.getAuthors().add(uma);
        umaBook.setPublisher(paddyPublisher);
        paddyPublisher.getBooks().add(umaBook);

        authorRepository.save(uma);
        bookRepository.save(umaBook);
        publisherRepository.save(paddyPublisher);

        System.out.println("Started in BootStrap");
        System.out.println("Number of Books : " + bookRepository.count());
        System.out.println("Publisher Number of Books : " + paddyPublisher.getBooks().size());
    }
}
