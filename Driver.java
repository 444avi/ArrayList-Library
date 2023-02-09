import java.util.Comparator;
import java.util.Scanner;

public class Driver {
	public static ArrayList<Book> library;

	public static void main(String[] args) {
		initLibrary(20);
		menu();
	}

	public static void initLibrary(int numBooks) {
		library = new ArrayList<>();
		String subject = "";
		// https://stackoverflow.com/questions/22186778/using-math-round-to-round-to-one-decimal-place
		double scale = (int) Math.pow(10, 1);

		// we'll use the ArrayList for publication years so we can randomly pick a year
		// and remove it so we don't use it again
		ArrayList<Integer> publicationYears = new ArrayList<>();
		for (int i = 1980; i < 2020; i++) {
			publicationYears.add(i);
		}

		int programmingCount, dataStructCount, algorithmsCount, opSysCount, gamingCount;
		programmingCount = dataStructCount = algorithmsCount = opSysCount = gamingCount = 0;

		for (int i = 0; i < numBooks; i++) {
			boolean subjectAssigned = false;

			while (subjectAssigned == false) {
				int x = (int) (Math.random() * 5) + 1;
				if (x == 1) {
					if (programmingCount >= 5)
						continue;
					subject = "Programming";
					programmingCount++;
				} else if (x == 2) {
					if (dataStructCount >= 5)
						continue;
					subject = "Data Structures";
					dataStructCount++;
				} else if (x == 3) {
					if (algorithmsCount >= 5)
						continue;
					subject = "Algorithms";
					algorithmsCount++;
				} else if (x == 4) {
					if (opSysCount >= 5)
						continue;
					subject = "Operating Systems";
					opSysCount++;
				} else if (x == 5) {
					if (gamingCount >= 5)
						continue;
					subject = "Gaming";
					gamingCount++;
				}
				subjectAssigned = true;
			}

			// get random publication year
			int index = (int) (Math.random() * publicationYears.getSize());
			int publicationYear = publicationYears.get(index);
			publicationYears.remove(index);

			// https://stackoverflow.com/questions/22186778/using-math-round-to-round-to-one-decimal-place
			Double value = (double) (Math.random() * 10.0 + 0.1);
			Double rating = (double) (Math.round(value * scale) / scale);

			library.add(
					new Book(
							"Book" + (i + 1),
							publicationYear,
							((int) (Math.random() * 950) + 50),
							subject,
							rating));
		}
	}

	public static ArrayList<Book> sort(Comparator<Book> comparator) {

		return null;
	} // credit to my dad

	public static void sortByPageLength() {
		for (int j = 0; j < library.getSize() - 1; j++) {
			int minIndex = j;
			for (int k = j + 1; k < library.getSize(); k++) {
				if (library.get(k).numPages < library.get(minIndex).numPages) {
					minIndex = k;
				}
			}
			Book temp = library.get(j);
			library.set(j, library.get(minIndex));
			library.set(minIndex, temp);
		}
	} // credit to CSawesome(modified after)

	public static void sortbyReviewRatings() {
		for (int j = 0; j < library.getSize() - 1; j++) {
			int minIndex = j;
			for (int k = j + 1; k < library.getSize(); k++) {
				if (library.get(k).rating < library.get(minIndex).rating) {
					minIndex = k;
				}
			}
			Book temp = library.get(j);
			library.set(j, library.get(minIndex));
			library.set(minIndex, temp);
		}

	}// credit to CSawesome(modified after)

	public static void sortByPublicationYear() {

		for (int j = 0; j < library.getSize() - 1; j++) {
			int minIndex = j;
			for (int k = j + 1; k < library.getSize(); k++) {
				if (library.get(k).publicationYear < library.get(minIndex).publicationYear) {
					minIndex = k;
				}
			}
			Book temp = library.get(j);
			library.set(j, library.get(minIndex));
			library.set(minIndex, temp);
		}
	} // credit to CSawesome(modified after)

	public enum SearchType {
		NAME,
		SUBJECT
	} // credit to my dad

	public static ArrayList<Book> search(ArrayList<Book> library, SearchType searchType, String searchParam) {
		ArrayList<Book> searchResults = new ArrayList<>();
		switch (searchType) {
			case SUBJECT:
				for (int i = 0; i < library.getSize(); i++) {
					Book book = library.get(i);
					if (book.subject.equalsIgnoreCase(searchParam)) {
						searchResults.add(book);
					}
				}
				break;

			case NAME:
				// search by name
				for (int i = 0; i < library.getSize(); i++) {
					Book book = library.get(i);
					if (book.title.equalsIgnoreCase(searchParam)) {
						searchResults.add(book);
					}
				}
				break;
		}
		return searchResults;
	} // credit to my dad(modified)

	public static void printBooks(ArrayList<Book> books) {
		for (int i = 0; i < books.getSize(); i++) {
			System.out.println(books.get(i));
		}
	}

	public static void menu() {
		Scanner in = new Scanner(System.in);
		String name;
		ArrayList<Book> books;

		System.out.println("Please select one of the following options:");
		System.out.println("1. List all books");
		System.out
				.println("2. Display the books sorted according to year of publication, starting with the oldest one");
		System.out.println("3. Sort the books according to length in pages, starting with the shortest");
		System.out.println("4. Sort the books according to review ratings, starting with the highest rating");
		System.out.println("5. Ask user for a subject, and display all the books belonging to that specific subject");
		System.out.println("6. Search for a specific book by name, and display all the details if the book exists");
		System.out.println("7. Add a book to the list of books (ask the user for all the details)");
		System.out.println("8. Exit");

		String x = in.nextLine();

		switch (x) {
			case "1":
				printBooks(library);
				menu();
			case "2":
				sortByPublicationYear();
				printBooks(library);
				menu();
			case "3":
				sortByPageLength();
				printBooks(library);
				menu();
			case "4":
				sortbyReviewRatings();
				printBooks(library);
				menu();
			case "5":
				System.out.println("What subject would you like to search?");
				name = in.next();
				books = search(library, SearchType.SUBJECT, name);
				if (books.getSize() == 0) {
					System.out.println("No books found");
				} else {
					printBooks(books);
				}
				menu();
			case "6":
				System.out.println("What book would you like to search?");
				name = in.next();
				books = search(library, SearchType.NAME, name);
				if (books.getSize() == 0) {
					System.out.println("No books found");
				} else {
					printBooks(books);
				}
				menu();
			case "7":
				System.out.println("Please enter the following details");
				System.out.println("Publication year:");
				String py = in.next();
				int publish = Integer.parseInt(py);

				System.out.println("Pages:");
				String pn = in.next();
				int pages = Integer.parseInt(pn);

				System.out.println("Subject:");
				String subject = in.next();

				System.out.println("Rating:");
				String br = in.next();
				Double rating = Double.parseDouble(br);

				library.add(new Book("Book 21", publish, pages, subject, rating));

				menu();
			case "8":
				System.out.println("Goodbye...");
				System.exit(0);
			default:
				System.out.println("Invalid choice, please try again.");
				menu();
		}

		in.close();

	}
}
