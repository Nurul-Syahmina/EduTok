// Creator: Person 3 | Tester: Person 4
// Group: G04/SE-G11 | App: EduTok

package edutok.person3;

import javax.swing.ImageIcon;

/**
 * Interface representing a learnable lesson page.
 * Each lesson page must provide its title, body text,
 * associated image, and its page number in the catalog.
 */
public interface Learnable {

    /**
     * * Returns the title of the lesson page.
     * @return lesson title as a String
     */
    String getTitle();

    /**
     * Returns the main body/content text of the lesson page.
     * @return lesson body as a String
     */
    String getBody();

    /**
     * Returns the image associated with the lesson page.
     * Image should be located in resources/images/
     * @return ImageIcon for display in GUI 
     */
    ImageIcon getImage();

    /**
     * Returns the page number of the lesson (1-based index).
     * @return page number as an integer
     */
    int getPageNumber();
}
