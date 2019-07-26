package entities;

public class Book extends Print {

    // Уникальный для книги номер, включает в себя код страны и издательства
    private String isbn;
    // ФИО автора, в дальнейшем переделать в объет отдельного класса
    private String writer;
    // Жанр книги
    private BookGenre genre;

    public Book() {
        super(PrintType.BOOK);
        this.setTakeaway(true);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Book print = (Book)obj;
        return super.equals(obj)
                && (isbn == print.getIsbn() || (isbn != null && isbn.equals(print.getIsbn())))
                && (genre == print.getGenre() || (genre != null && genre.equals(print.getGenre())))
                && (writer == print.getWriter() || (writer != null && writer.equals(print.getWriter())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + super.hashCode();
        if (writer != null) res = prime*res + writer.hashCode();
        if (isbn != null) res = prime*res + isbn.hashCode();

        return res;
    }

    @Override
    public String toString() {
        return "Book{" +
                super.toString() +
                "isbn='" + isbn + '\'' +
                ", writer='" + writer + '\'' +
                ", genre=" + genre +
                "}";
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }
}
