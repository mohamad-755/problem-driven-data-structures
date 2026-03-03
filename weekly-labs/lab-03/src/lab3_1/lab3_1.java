package lab3_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The Song class represents a single song.
 * It stores the artist and title and a pointer to the next Song in the linked
 * list.
 * It implements the Comparable interface so that songs can be compared by
 * artist
 * and then by title (both comparisons are case–insensitive).
 */
class Song implements Comparable<Song> {
    private String artist;
    private String title;
    private Song next;

    // Constructor that sets artist and title; next defaults to null.
    public Song(String artist, String title) {
        this.artist = artist;
        this.title = title;
        this.next = null;
    }

    // Constructor that sets artist, title, and next.
    public Song(String artist, String title, Song next) {
        this.artist = artist;
        this.title = title;
        this.next = next;
    }

    // Default constructor.
    public Song() {
        this.artist = "";
        this.title = "";
        this.next = null;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public Song getNext() {
        return next;
    }

    public void setNext(Song next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {
        // Check for self-comparison.
        if (this == obj) {
            return true;
        }
        // Check for null or type mismatch.
        if (obj == null || !(obj instanceof Song)) {
            return false;
        }
        Song other = (Song) obj;
        // Two songs are equal if both artist and title are equal (ignoring case).
        return this.artist.equalsIgnoreCase(other.artist) &&
                this.title.equalsIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        // Return the song details in the desired format.
        return "Artist: " + artist + "\nTitle: " + title;
    }

    @Override
    public int compareTo(Song other) {
        // Compare first by artist.
        int artistComparison = this.artist.compareToIgnoreCase(other.artist);
        if (artistComparison != 0) {
            return artistComparison;
        }
        // If artists are the same, compare by title.
        return this.title.compareToIgnoreCase(other.title);
    }
}

/**
 * The SongList class implements a linked list of Song objects.
 * It provides methods for reading songs from a file, adding, deleting,
 * searching, displaying, and writing the list of songs.
 */
class SongList {
    private Song head;
    private int numSongs;

    public SongList() {
        head = null;
        numSongs = 0;
    }

    /**
     * Reads a file with the given path.
     * The file should have song data in the following format:
     *
     * Artist: The Beatles
     * Title: Hey Jude
     *
     * Each pair of lines represents one song.
     */
    public void readFile(String aFile) {
        File file = new File(aFile);
        if (!file.exists() || !file.canRead()) {
            System.out.println("File does not exist or cannot be read.");
            return;
        }
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String artistLine = fileScanner.nextLine().trim();
                // Skip any empty lines.
                if (artistLine.isEmpty()) {
                    continue;
                }
                if (!artistLine.startsWith("Artist: ")) {
                    System.out.println("Invalid file format. Expected 'Artist: ' prefix.");
                    continue;
                }
                String artist = artistLine.substring(8).trim();

                if (!fileScanner.hasNextLine()) {
                    break; // Missing title line.
                }
                String titleLine = fileScanner.nextLine().trim();
                if (!titleLine.startsWith("Title: ")) {
                    System.out.println("Invalid file format. Expected 'Title: ' prefix.");
                    continue;
                }
                String title = titleLine.substring(7).trim();
                // Use addSong so that the song is inserted in sorted order.
                addSong(artist, title);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Adds a new song (if it does not already exist) into the linked list
     * in sorted order (alphabetically by artist then title).
     */
    public void addSong(String artist, String title) {
        Song newSong = new Song(artist, title);
        // Check if the song already exists.
        Song current = head;
        while (current != null) {
            if (current.equals(newSong)) {
                System.out.println("Song already exists:\n" + newSong);
                return;
            }
            current = current.getNext();
        }
        // Insert newSong into the sorted list.
        if (head == null || newSong.compareTo(head) < 0) {
            newSong.setNext(head);
            head = newSong;
        } else {
            Song prev = head;
            current = head.getNext();
            while (current != null && newSong.compareTo(current) >= 0) {
                prev = current;
                current = current.getNext();
            }
            newSong.setNext(current);
            prev.setNext(newSong);
        }
        numSongs++;
    }

    /**
     * Deletes all songs in the list that match the given artist.
     */
    public void deleteByArtist(String artist) {
        boolean found = false;
        // Delete matching nodes at the head.
        while (head != null && head.getArtist().equalsIgnoreCase(artist)) {
            head = head.getNext();
            numSongs--;
            found = true;
        }
        // Delete matching nodes beyond the head.
        Song current = head;
        while (current != null && current.getNext() != null) {
            if (current.getNext().getArtist().equalsIgnoreCase(artist)) {
                current.setNext(current.getNext().getNext());
                numSongs--;
                found = true;
            } else {
                current = current.getNext();
            }
        }
        if (!found) {
            System.out.println("No songs found for artist: " + artist);
        } else {
            System.out.println("All songs by " + artist + " have been deleted.");
        }
    }

    /**
     * Deletes the first song found with the given title.
     */
    public void deleteByTitle(String title) {
        boolean found = false;
        // Check if head needs to be deleted.
        if (head != null && head.getTitle().equalsIgnoreCase(title)) {
            head = head.getNext();
            numSongs--;
            found = true;
        } else {
            Song current = head;
            while (current != null && current.getNext() != null) {
                if (current.getNext().getTitle().equalsIgnoreCase(title)) {
                    current.setNext(current.getNext().getNext());
                    numSongs--;
                    found = true;
                    break; // Delete only the first occurrence.
                }
                current = current.getNext();
            }
        }
        if (!found) {
            System.out.println("No song found with title: " + title);
        } else {
            System.out.println("Song with title '" + title + "' has been deleted.");
        }
    }

    /**
     * Searches for and displays all songs that match the given artist.
     */
    public void searchByArtist(String artist) {
        boolean found = false;
        Song current = head;
        while (current != null) {
            if (current.getArtist().equalsIgnoreCase(artist)) {
                System.out.println(current);
                System.out.println();
                found = true;
            }
            current = current.getNext();
        }
        if (!found) {
            System.out.println("No songs found for artist: " + artist);
        }
    }

    /**
     * Returns a new SongList containing all songs that match the given artist.
     * Returns null if no songs are found.
     */
    public SongList getSongsByArtist(String artist) {
        SongList artistList = new SongList();
        Song current = head;
        while (current != null) {
            if (current.getArtist().equalsIgnoreCase(artist)) {
                // Add the song to the artist-specific list.
                artistList.addSong(current.getArtist(), current.getTitle());
            }
            current = current.getNext();
        }
        if (artistList.numSongs == 0) {
            return null;
        }
        return artistList;
    }

    /**
     * Searches for a song by title and displays it.
     */
    public void searchByTitle(String title) {
        boolean found = false;
        Song current = head;
        while (current != null) {
            if (current.getTitle().equalsIgnoreCase(title)) {
                System.out.println(current);
                found = true;
                break; // Assuming only one song is needed.
            }
            current = current.getNext();
        }
        if (!found) {
            System.out.println("No song found with title: " + title);
        }
    }

    /**
     * Returns a Song object that matches the given title.
     * If no matching song is found, returns null.
     */
    public Song getByTitle(String title) {
        Song current = head;
        while (current != null) {
            if (current.getTitle().equalsIgnoreCase(title)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Displays the entire catalog of songs.
     */
    public void displayCatalog() {
        if (head == null) {
            System.out.println("No songs in the catalog.");
            return;
        }
        Song current = head;
        while (current != null) {
            System.out.println(current);
            System.out.println();
            current = current.getNext();
        }
    }

    /**
     * Writes the current list of songs to a file in the original format.
     * Each song is written as:
     * Artist: <artist>
     * Title: <title>
     */
    public void writeFile(String aFile) {
        try (PrintWriter writer = new PrintWriter(aFile)) {
            Song current = head;
            while (current != null) {
                writer.println("Artist: " + current.getArtist());
                writer.println("Title: " + current.getTitle());
                // Uncomment the following line to insert a blank line between songs.
                // writer.println();
                current = current.getNext();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

/**
 * The SongCatalog class provides a menu–driven command-line interface
 * for managing a collection of songs stored in a linked list.
 */
public class lab3_1 {
    public static void main(String[] args) {
        SongList album = new SongList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Import songs from a file");
            System.out.println("2. Add a new song");
            System.out.println("3. Delete a song");
            System.out.println("4. Search for a song");
            System.out.println("5. Get a song by title");
            System.out.println("6. Get all songs by artist");
            System.out.println("7. Display all songs");
            System.out.println("8. Exit program");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the absolute path to the file: ");
                    String filePath = scanner.nextLine();
                    album.readFile(filePath);
                    System.out.println("Songs imported successfully.");
                    break;
                case 2:
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    album.addSong(artist, title);
                    System.out.println("Song added successfully.");
                    break;
                case 3:
                    System.out.print("Delete by artist or title (A or T): ");
                    String deleteOption = scanner.nextLine().toUpperCase();
                    if (deleteOption.equals("A")) {
                        System.out.print("Enter artist: ");
                        String deleteArtist = scanner.nextLine();
                        album.deleteByArtist(deleteArtist);
                    } else if (deleteOption.equals("T")) {
                        System.out.print("Enter title: ");
                        String deleteTitle = scanner.nextLine();
                        album.deleteByTitle(deleteTitle);
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case 4:
                    System.out.print("Search by artist or title (A or T): ");
                    String searchOption = scanner.nextLine().toUpperCase();
                    if (searchOption.equals("A")) {
                        System.out.print("Enter artist: ");
                        String searchArtist = scanner.nextLine();
                        album.searchByArtist(searchArtist);
                    } else if (searchOption.equals("T")) {
                        System.out.print("Enter title: ");
                        String searchTitle = scanner.nextLine();
                        album.searchByTitle(searchTitle);
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case 5:
                    System.out.print("Enter title to get the song: ");
                    String getTitle = scanner.nextLine();
                    Song song = album.getByTitle(getTitle);
                    if (song != null) {
                        System.out.println(song);
                    } else {
                        System.out.println("Song not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter artist to get all songs: ");
                    String getAllArtist = scanner.nextLine();
                    SongList artistSongs = album.getSongsByArtist(getAllArtist);
                    if (artistSongs != null) {
                        artistSongs.displayCatalog();
                    } else {
                        System.out.println("No songs found for artist: " + getAllArtist);
                    }
                    break;
                case 7:
                    album.displayCatalog();
                    break;
                case 8:
                    // Before exiting, write the modified song list to songs.txt.
                    album.writeFile("songs.txt");
                    running = false;
                    System.out.println("Exiting program. Thank you for using the software.");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
