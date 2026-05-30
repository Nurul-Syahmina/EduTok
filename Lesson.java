// Creator: Person 3 | Tester: Person 4
// Group: G04/SE-G11 | App: EduTok

package edutok.person3;

import javax.swing.ImageIcon;
import java.net.URL;

/**
 * Represents a single lesson page in the EduTok learning module.
 * Implements the learnable interface.
 * 
 * Each Lesson holds a title, body text, page number, and an 
 * image loaded from the resources/images/ folder.
 */
public class Lesson implements Learanble {
    private String title;
    private String body;
    private int pageNumber;
    private String imagePath; // relative path, e.g. "resources/images/lesson01.png"

    /**
     * Constructor for Lesson.
     * 
     * @param pageNumber the 1-based page number of this lesson
     * @param title the title of the lesson
     * @param body the body text / educational content
     * @param imagePath relative path to the lesson image
     */
    public Lesson(int pageNumber, String title, String body, String imagePath) {
        this.pageNumber = pageNumber;
        this.title = title;
        this.body = body;
        this.imagePath = imagePath;
    }

    /**
     * Returns the title of the lesson.
     */
    @Override
    public String getTitle() {
        return title;
    }

/**
 * Returns the body text of this lesson.
 */
@Override
public String getBody() {
    return body;
}

/**
 * Loads and returns the ImageIcon for this lesson.
 * Tries to load from classpath (resources/images/).
 * Falls back gracefully if image is not found.
 */
@Override
public ImageIcon getImage() {
    try {
        URL imgURL = getClass().getClassLoader().getResource(imagePath);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("[Lesson] Image not found: " + imagePath);
            return null; 
        }
    } catch (Exception e) {
        System.err.println("[Lesson] Error loading image: " + e.getMessage());
        return null;
    }
}

/**
 * Returns the page number of this lesson.
 */
@Override
public int getPageNumber() {
    return pageNumber;
}

/**
 * Returns a readable summary of this lesson (useful for debugging).
 */
@Override
public String toString() {
    return "Lesson[page=" + pageNumber + ", title=\"" + title + "\"]";
}

}

    
    

