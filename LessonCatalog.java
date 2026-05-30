// Creator: Person 3 | Tester: Person 4
// Group: G04/SE-G11 | App: EduTok

package edutok.person3;

import javax.swing.ImageIcon;
import java.util.ArrayList;

/**
 * LessonCatalog manages all Lesson pages for the EduTok learning module.
 * 
 * It stores lessons in an ArrayList<Learnable> (as required),
 * supports next/previous page navigation, and provides
 * helper methods for Person 1 (GUI) to call when displaying lessons.
 * 
 * Topic: SDG 4 - Quality Education
 * Contains 12 lesson pages.
 */
public class LessonCatalog {

    // Store lessons as Learnable (interface type) — demonstrates polymorphism
    private ArrayList<Learnable> lessons;

    // Tracks the current page index (0-based internally)
    private int currentIndex;

    /**
     * Constructor — initialises and loads all 12 lessons.
     */
    public LessonCatalog() {
        lessons = new ArrayList<>();
        currentIndex = 0;
        loadLessons();
    }

    /**
     * Populates the catalog with 12 SDG 4 themed lessons.
     * Images should be placed in: resources/images/lesson01.png ... lesson12.png
     */
    private void loadLessons() {

        lessons.add(new Lesson(1,
            "What is SDG 4?",
            "SDG 4 stands for Sustainable Development Goal 4: Quality Education.\n\n" +
            "It is one of 17 global goals set by the United Nations in 2015 as part of the " +
            "2030 Agenda for Sustainable Development.\n\n" +
            "SDG 4 aims to ensure inclusive and equitable quality education and promote " +
            "lifelong learning opportunities for all people, regardless of age, gender, or background.",
            "resources/images/lesson01.png"
        ));

        lessons.add(new Lesson(2,
            "Why Education Matters",
            "Education is a fundamental human right and a cornerstone of sustainable development.\n\n" +
            "When people receive quality education, they are better equipped to break the cycle of " +
            "poverty, participate in democratic processes, and contribute to economic growth.\n\n" +
            "Education also promotes gender equality, reduces child marriage, and improves health outcomes " +
            "for families and communities.",
            "resources/images/lesson02.png"
        ));

        lessons.add(new Lesson(3,
            "The Global Education Crisis",
            "Despite progress, millions of children and youth around the world are still out of school.\n\n" +
            "Key statistics:\n" +
            "• Over 250 million children are not in school globally.\n" +
            "• 617 million children and adolescents cannot read or do basic math.\n" +
            "• In low-income countries, only 1 in 6 children reach secondary education.\n\n" +
            "Barriers include poverty, conflict, discrimination, and lack of trained teachers.",
            "resources/images/lesson03.png"
        ));

        lessons.add(new Lesson(4,
            "Early Childhood Education",
            "Early childhood education (ECE) refers to formal and informal learning for children " +
            "from birth up to age 8.\n\n" +
            "Research shows that the first 5 years of life are the most critical for brain development. " +
            "Quality ECE programs improve cognitive skills, social behaviour, and long-term academic success.\n\n" +
            "SDG 4 specifically calls for access to quality pre-primary education for all children.",
            "resources/images/lesson04.png"
        ));

        lessons.add(new Lesson(5,
            "Primary and Secondary Education",
            "SDG 4 Target 4.1 calls for all girls and boys to complete free, equitable, and quality " +
            "primary and secondary education leading to relevant learning outcomes.\n\n" +
            "Primary education (ages 6–12) builds foundational literacy and numeracy.\n" +
            "Secondary education (ages 12–18) prepares students for higher education or the workforce.\n\n" +
            "Ensuring completion of both levels is key to achieving the SDG 4 vision.",
            "resources/images/lesson05.png"
        ));

        lessons.add(new Lesson(6,
            "Gender Equality in Education",
            "SDG 4 is closely linked to SDG 5 (Gender Equality). Globally, girls face greater " +
            "barriers to education than boys, particularly in developing regions.\n\n" +
            "Challenges include:\n" +
            "• Cultural norms that prioritise boys' education\n" +
            "• Early marriage removing girls from school\n" +
            "• Lack of safe sanitation facilities for girls\n\n" +
            "Educating girls has a multiplier effect — it reduces poverty, improves child health, " +
            "and boosts national economies.",
            "resources/images/lesson06.png"
        ));

        lessons.add(new Lesson(7,
            "Education for Persons with Disabilities",
            "Inclusive education means all learners, including those with physical, cognitive, or " +
            "sensory disabilities, have access to quality learning in mainstream schools.\n\n" +
            "SDG 4 explicitly mentions persons with disabilities as a group requiring special attention.\n\n" +
            "Inclusive classrooms benefit all students by promoting empathy, teamwork, and diverse " +
            "problem-solving approaches.",
            "resources/images/lesson07.png"
        ));

        lessons.add(new Lesson(8,
            "Technical and Vocational Education",
            "Not all learners pursue a traditional academic path. Technical and Vocational Education " +
            "and Training (TVET) equips people with practical skills for employment.\n\n" +
            "Examples include training in carpentry, plumbing, computing, healthcare, and agriculture.\n\n" +
            "SDG 4 Target 4.3 aims to ensure equal access to affordable TVET for all, including " +
            "adults who need to reskill for changing job markets.",
            "resources/images/lesson08.png"
        ));

        lessons.add(new Lesson(9,
            "The Role of Teachers",
            "Teachers are the backbone of quality education. SDG 4 Target 4.c focuses on " +
            "increasing the number of qualified teachers worldwide.\n\n" +
            "Key issues:\n" +
            "• Teacher shortages in rural and remote areas\n" +
            "• Low salaries leading to poor motivation\n" +
            "• Need for continuous professional development\n\n" +
            "Investing in teacher training and well-being directly improves student learning outcomes.",
            "resources/images/lesson09.png"
        ));

        lessons.add(new Lesson(10,
            "Technology and Digital Learning",
            "Technology is transforming education. Digital tools like e-learning platforms, " +
            "educational apps, and online courses have expanded access to quality content globally.\n\n" +
            "During the COVID-19 pandemic, remote learning became a necessity. This revealed a " +
            "digital divide — many students lacked devices or internet access.\n\n" +
            "SDG 4 calls for safe, inclusive, and effective use of technology to support learning " +
            "for all students, especially in underserved communities.",
            "resources/images/lesson10.png"
        ));

        lessons.add(new Lesson(11,
            "Lifelong Learning",
            "Learning does not stop after school. Lifelong learning refers to the continuous " +
            "development of skills and knowledge throughout a person's life.\n\n" +
            "This includes:\n" +
            "• Adult literacy programmes\n" +
            "• Online courses and certifications\n" +
            "• Workplace training\n" +
            "• Community education initiatives\n\n" +
            "SDG 4 promotes lifelong learning for all ages to support personal growth, " +
            "employability, and active citizenship.",
            "resources/images/lesson11.png"
        ));

        lessons.add(new Lesson(12,
            "How You Can Support SDG 4",
            "Everyone has a role to play in achieving SDG 4. Here's how you can contribute:\n\n" +
            "• Stay curious — keep learning every day\n" +
            "• Volunteer or tutor peers in your community\n" +
            "• Support organisations that fund girls' education\n" +
            "• Advocate for inclusive schools in your area\n" +
            "• Use apps like EduTok to spread awareness!\n\n" +
            "Together, we can ensure quality education for every person on the planet by 2030.",
            "resources/images/lesson12.png"
        ));
    }

    // ─────────────────────────────────────────────
    // NAVIGATION METHODS (called by Person 1 GUI)
    // ─────────────────────────────────────────────

    /**
     * Returns the current lesson being viewed.
     * @return current Learnable lesson
     */
    public Learnable getCurrentLesson() {
        return lessons.get(currentIndex);
    }

    /**
     * Advances to the next lesson page.
     * Does nothing if already on the last page.
     */
    public void nextPage() {
        if (currentIndex < lessons.size() - 1) {
            currentIndex++;
        }
    }

    /**
     * Goes back to the previous lesson page.
     * Does nothing if already on the first page.
     */
    public void previousPage() {
        if (currentIndex > 0) {
            currentIndex--;
        }
    }

    /**
     * Returns true if there is a next page available.
     * @return boolean
     */
    public boolean hasNext() {
        return currentIndex < lessons.size() - 1;
    }

    /**
     * Returns true if there is a previous page available.
     * @return boolean
     */
    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    /**
     * Resets navigation back to the first lesson.
     * Call this when the user re-enters the Learn screen.
     */
    public void resetToFirst() {
        currentIndex = 0;
    }

    // ─────────────────────────────────────────────
    // UTILITY METHODS
    // ─────────────────────────────────────────────

    /**
     * Returns the total number of lesson pages.
     * @return total lessons count
     */
    public int getTotalLessons() {
        return lessons.size();
    }

    /**
     * Returns the current page index (1-based, for display).
     * e.g. "Page 3 of 12"
     * @return current page number (1-based)
     */
    public int getCurrentPageNumber() {
        return currentIndex + 1;
    }

    /**
     * Returns a specific lesson by its 1-based page number.
     * Returns null if page number is out of range.
     * @param pageNumber 1-based page number
     * @return Learnable lesson or null
     */
    public Learnable getLessonByPage(int pageNumber) {
        if (pageNumber >= 1 && pageNumber <= lessons.size()) {
            return lessons.get(pageNumber - 1);
        }
        return null;
    }

    /**
     * Returns all lessons as an ArrayList<Learnable>.
     * Can be used by Person 1 to build a lesson list/menu.
     * @return ArrayList of all lessons
     */
    public ArrayList<Learnable> getAllLessons() {
        return lessons;
    }

    /**
     * Convenience: returns the title of the current lesson.
     * @return current lesson title
     */
    public String getCurrentTitle() {
        return getCurrentLesson().getTitle();
    }

    /**
     * Convenience: returns the body text of the current lesson.
     * @return current lesson body
     */
    public String getCurrentBody() {
        return getCurrentLesson().getBody();
    }

    /**
     * Convenience: returns the image of the current lesson.
     * @return current lesson ImageIcon (may be null if image missing)
     */
    public ImageIcon getCurrentImage() {
        return getCurrentLesson().getImage();
    }
}