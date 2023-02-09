public class Book {
    String title;
    int publicationYear;
    int numPages;
    String subject;
    Double rating;

    public Book(String title, int publicationYear, int numPages, String subject, Double rating){
        this.title = title;
        this.publicationYear = publicationYear;
        this.numPages = numPages;
        this.subject = subject;
        this.rating = rating;

    }

    public String toString(){
        return "Title: " + title + ", Publication year: " + publicationYear + ", Pages: " + numPages + ", Subject: "
                + subject + ", Rating: " + rating;
    }

    @Override
    public boolean equals(Object obj) {
        
        Book otherBook = (Book) obj;
        return this.title == otherBook.title;
    }
}
