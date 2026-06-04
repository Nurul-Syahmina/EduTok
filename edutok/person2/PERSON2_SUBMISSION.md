# Person 2 — Quiz Module (Submission Pack)
**Group:** G04 / SE-G11 | **Member:** Person 2 | **App:** EduTok | **Theme:** SDG 4

Send this file (plus your screenshots) to Person 1 for the group PDF/ZIP.

---

## 1. My role

| Item | Detail |
|------|--------|
| **Class** | `QuizManager` |
| **Interface** | `QuizQuestion` |
| **Folder** | `edutok/person2/` |
| **Supporting types** | `AbstractQuestion`, `MultipleChoiceQuestion`, `TrueFalseQuestion` |
| **Tester (reviews my PR)** | Person 3 |
| **I test** | Person 3 (`LessonCatalog`, `Learnable`, `Lesson`) — see Section 6 |

---

## 2. What my module does

The quiz module stores **20 questions** (10 Multiple Choice + 10 True/False) about **SDG 4**, Java/OOP, and project rules. Questions are held as `List<QuizQuestion>` so **polymorphism** is used: the GUI and `QuizManager` call `getPrompt()`, `checkAnswer()`, and `getType()` without knowing the concrete class.

**Inheritance:** `AbstractQuestion` implements `QuizQuestion`; `MultipleChoiceQuestion` and `TrueFalseQuestion` extend `AbstractQuestion` and **override** `checkAnswer()` and `getType()`.

**Integration (Person 1 only calls my API):**
- App start: `quizManager = new QuizManager(); quizManager.loadQuestions();`
- Display: `getCurrentQuestion()`, `getCurrentQuestionNumber()`, `getTotalQuestions()`
- MCQ options: `((MultipleChoiceQuestion) q).getOptions()` in GUI
- Submit: `submitAnswer(answer)` → `nextQuestion()` or end
- End of quiz: `getScorePercent()`, `getMotivationalMessage()`, `resetQuiz()`

Person 1 then passes the score to Person 4: `progress.recordQuiz(score); progress.save();`

---

## 3. Requirements checklist (Person 2)

| Requirement | Status | Evidence |
|-------------|--------|----------|
| 20+ questions | Yes (20) | `QuizManager.loadQuestions()` |
| 2+ types (MCQ + T/F) | Yes | `MultipleChoiceQuestion`, `TrueFalseQuestion` |
| Abstract class + subclasses | Yes | `AbstractQuestion` → MCQ / T/F |
| Polymorphic storage | Yes | `List<QuizQuestion> questions` |
| Exact score messages | Yes | `getMotivationalMessage()` |
| Interface `QuizQuestion` | Yes | `getPrompt`, `checkAnswer`, `getType` |
| Header comment in every file | Yes | All `.java` in `person2/` |
| Person 3 tests my code | Person 3 | PR review + their notes |
| I test Person 3 | Person 2 | Section 6 + `Person2TestRunner` |

---

## 4. UI screenshots — what to capture (Person 2)

Run the app from project root:

```text
javac -d . (Get-ChildItem -Path edutok -Recurse -Filter *.java).FullName
java edutok.person1.MainApplication
```

Use **Win + Shift + S** (or Snipping Tool). Name files clearly, e.g. `P2_01_home.png`.

### Screenshot A — Home → Quiz entry
1. Open EduTok home screen.
2. Capture window showing **Quiz** button (proves navigation into your module).

**Caption for PDF:**  
*Figure P2-A: Home screen. User selects Quiz to enter the Person 2 quiz module wired by Person 1.*

### Screenshot B — Multiple Choice question
1. Tap **Quiz**.
2. Capture first MCQ: **Question 1 / 20**, prompt, options **A–D**, answer field.

**Caption:**  
*Figure P2-B: Multiple choice question displayed via `QuizManager.getCurrentQuestion()` and `MultipleChoiceQuestion.getOptions()`. Person 1 renders options; Person 2 supplies question data and validation logic.*

### Screenshot C — True/False question
1. Answer through until a T/F item appears (questions 11–20), or answer quickly with any letter to advance.
2. Capture a True/False prompt (e.g. “Java is a case-sensitive language”).

**Caption:**  
*Figure P2-C: True/False question type. `TrueFalseQuestion` overrides `checkAnswer()` to accept true/false or t/f.*

### Screenshot D — Input validation (optional but strong)
1. On MCQ, tap Submit with **empty** answer → error dialog.
2. Or enter **E** → “Please enter A, B, C, or D.” (validation in Person 1 GUI using your question types).

**Caption:**  
*Figure P2-D: Client-side validation before `submitAnswer()` is called, improving UX and preventing invalid attempts.*

### Screenshot E — Quiz completion + motivational message
1. Complete the quiz (use mostly correct answers for a high score demo).
2. Capture the dialog: **Score: XX%** and message (**Outstanding!**, etc.).

**Caption:**  
*Figure P2-E: End of quiz. `getScorePercent()` and `getMotivationalMessage()` return exact wording required by the brief. Person 1 shows the dialog and calls Person 4 to save the score.*

### Screenshot F — Progress / Leaderboard after quiz (integration)
1. After quiz, open **Progress** and **Leaderboard**.
2. Capture updated score (proves Person 2 → Person 4 pipeline works).

**Caption:**  
*Figure P2-F: After quiz, score is recorded via `UserProgress.recordQuiz()` and persisted to `data/scores.txt` (Person 4). Shows end-to-end integration.*

---

## 5. Written explanation (paste into group PDF)

### 5.1 Quiz module design

Person 2 implemented the **Quiz Module** using OOP principles required by TMF2954. The `QuizQuestion` interface defines three methods used by both question types and by Person 1’s GUI: `getPrompt()`, `checkAnswer(String)`, and `getType()`. The abstract class `AbstractQuestion` holds shared fields (`prompt`, `correctAnswer`) and a `normalize()` helper for case-insensitive grading.

`MultipleChoiceQuestion` stores four options and treats answers **a–d** (case-insensitive). `TrueFalseQuestion` accepts **true/false** or **t/f**. All 20 questions are registered in `QuizManager.loadQuestions()` and stored in a `List<QuizQuestion>`, demonstrating **polymorphism**: one list, two concrete types, one interface.

`QuizManager` controls quiz flow: `submitAnswer`, `nextQuestion`, `hasNext`, `getScorePercent`, `resetQuiz`, and `getMotivationalMessage` with the **exact** strings from the project brief (80–100% → Outstanding!, etc.).

### 5.2 How Person 1 connects my module

Person 1 does **not** duplicate quiz logic. In `MainApplication`, a `QuizManager` instance loads questions at startup. The quiz panel reads the current `QuizQuestion`, formats MCQ options when the instance is `MultipleChoiceQuestion`, validates input, calls `submitAnswer`, advances with `nextQuestion`, and on the last question calls `progress.recordQuiz(quizManager.getScorePercent())` and `progress.save()`. This matches the integration diagram in the group brief.

### 5.3 SDG 4 link

Quiz content reinforces **Quality Education (SDG 4)**: e.g. “SDG 4 focuses on what?”, barriers to schooling, and project rules (no hardcoded scores, 20 questions, testing proof). This aligns EduTok’s gamified quiz with the group theme.

---

## 6. Proof of Testing and Debugging (Person 2 tests Person 3)

Person 3 owns the **Learning module** (`LessonCatalog`, `Learnable`, `Lesson`). Person 2 tests it via the **Learn** screen in EduTok and `Person2TestRunner`. Use the **three-figure layout** in the group PDF (same style as Section 4.0 in the sample brief).

### 6.1 Manual UI proof — three figures (paste captions into PDF)

| Figure | Screenshot to capture | Steps |
|--------|----------------------|--------|
| **Figure 5** | **Learn** — Page **1 / 12**, lesson **title** and **body** visible | Home → **Learn**. Confirms `LessonCatalog.getCurrentLesson()` and Person 1's `updateLessonDisplay()`. |
| **Figure 6** | **Learn** — after **Next** (Page 2 / 12 or new title); then **Previous** back to Page 1 | Proves `nextPage()`, `previousPage()`, and `hasNext()` / `hasPrevious()`. |
| **Figure 7** | **Learn** on **last page** (Page 12 / 12) **or** app still runs with no lesson image | Next on last page stays on 12; missing PNG shows empty image area, no crash. |

**Figure 5 caption (PDF):**  
*Figure 5: Learning module loaded correctly. Person 3's `LessonCatalog` provides at least 10 lessons; Person 1 displays page number, title, and body on the Learn screen.*

**Figure 6 caption (PDF):**  
*Figure 6: Lesson navigation works. Next and Previous buttons call Person 3's `nextPage()` and `previousPage()`; content and page number update as expected.*

**Figure 7 caption (PDF):**  
*Figure 7: Boundary behaviour on the Learn screen. On the last lesson, Next does not go past page 12; if lesson images are missing, the application continues without terminating.*

### 6.2 Automated console proof — Person 3 module

From project root:

```text
javac -d . (Get-ChildItem -Path edutok -Recurse -Filter *.java).FullName
java edutok.person2.Person2TestRunner
```

**Screenshot for PDF:** terminal showing `Person 2 tests Person 3 — Learning Module`, all `[PASS]` lines, and **`Passed: 33 | Failed: 0`** (or at least 11 learning tests + summary).

**Caption for terminal screenshot:**  
*Figure 8: Automated testing proof for Person 3's learning module. `Person2TestRunner` verifies lesson count (≥10), title/body content, page navigation, `resetToFirst`, `getLessonByPage`, and invalid page handling. Result: Passed 33 | Failed 0.*

| # | Test case | Expected |
|---|-----------|----------|
| L1 | Lesson count | ≥ 10 lessons |
| L2 | First page | Page 1, title + body |
| L3 | Next | Page increments, new content |
| L4 | Previous | Returns to earlier page |
| L5 | `resetToFirst` | Back to page 1 |
| L6 | `getLessonByPage(999)` | Returns `null` |
| L7 | `getAllLessons` size | Matches total lessons |

### 6.3 Person 3 tests Person 2 (your tester's job)

Person 3 reviews your quiz module (`QuizManager`, `QuizQuestion`, etc.) and runs the first block of `Person2TestRunner` (Quiz Module Tests). They should add: *"Person 3 tested Person 2 quiz module — pass."*

---

## 7. Files to include in ZIP (Person 2 folder)

```
edutok/person2/
  QuizManager.java
  QuizQuestion.java
  AbstractQuestion.java
  MultipleChoiceQuestion.java
  TrueFalseQuestion.java
  Person2TestRunner.java          (tests Person 3 — PDF Section 6)
  PERSON2_SUBMISSION.md           (this document)
```

Screenshots: put in group folder e.g. `screenshots/person2/`.

---

## 8. Quick demo answers (for screenshot E)

To finish the quiz quickly with a high score, use mostly: **a, b, b, a, c, a, b, a, b, d** for MCQ (1–10), then **true, true, false, true, true, false, true, false, true, true** for T/F (11–20). Adjust if your build differs; wrong answers still prove the score dialog.

---

*Person 2 — ready for Person 1 to merge into G04/SE-G11-ProjectDesign.pdf*
