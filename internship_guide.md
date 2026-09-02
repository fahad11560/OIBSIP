# 🚀 Oasis Infobyte Android Internship — Complete Roadmap

**Intern:** Mirza Fahad Baig  
**Track:** 📱 Android App Development  
**Duration:** 5 August 2026 → 15 September 2026 (~6 weeks)  
**Requirement:** Complete **at least 3 out of 5** tasks  
**Language:** ☕ **Java** (NOT Kotlin — the company requires Java)

---

## 📊 All 5 Tasks at a Glance

| # | Task | Difficulty | Key Concepts | My Recommendation |
|---|------|-----------|--------------|-------------------|
| 1 | Unit Converter | ⭐⭐ Medium | Spinner, Input Validation | ✅ **Do this** |
| 2 | To-Do App with Login | ⭐⭐⭐ Hard | SQLite, Auth, CRUD, Hashing | ⚡ Optional (bonus) |
| 3 | Calculator | ⭐ Easy | GridLayout, String Logic | ✅ **Do this first** |
| 4 | Quiz Application | ⭐⭐ Medium | RadioGroup, Multi-Activity, Score | ⚡ Optional (bonus) |
| 5 | Stopwatch | ⭐⭐ Medium | Handler, Runnable, Lifecycle | ✅ **Do this** |

## 🎯 My Recommended 3 Tasks (Best Strategy)

> [!TIP]
> **Start with the easiest, build confidence, then increase difficulty.**

| Order | Task | Why This Order |
|-------|------|----------------|
| **1st** | Task 3: Calculator | Simplest — builds your confidence with layouts & logic |
| **2nd** | Task 5: Stopwatch | Medium — introduces Handler/Runnable & lifecycle |
| **3rd** | Task 1: Unit Converter | Medium — Spinners, categories, validation |

> [!NOTE]
> If you finish early and want to impress, do **Task 4 (Quiz)** or **Task 2 (To-Do with Login)** as a 4th/5th task. More completed tasks = stronger certificate evaluation.

---

## 🗓️ 6-Week Schedule

| Week | Dates | Focus | Deliverable |
|------|-------|-------|-------------|
| **Pre-Week** | Jul 31 – Aug 4 | Setup + Java/Android basics | Environment ready |
| **Week 1** | Aug 5 – Aug 11 | 🔨 **Task 3: Calculator** | Working app + push to GitHub |
| **Week 2** | Aug 12 – Aug 18 | 🔨 **Task 5: Stopwatch** | Working app + push to GitHub |
| **Week 3** | Aug 19 – Aug 25 | 🔨 **Task 1: Unit Converter** | Working app + push to GitHub |
| **Week 4** | Aug 26 – Sep 1 | Polish all 3 apps + bug fixes | Polished code |
| **Week 5** | Sep 2 – Sep 8 | READMEs + Demo Videos + LinkedIn posts | All docs ready |
| **Week 6** | Sep 9 – Sep 14 | Peer evaluation + Final submission | ✅ SUBMITTED |

---

## 🔧 Phase 0: Setup (Do This NOW — Before Aug 5)

You have **5 days**. Use every one of them.

### Day 1-2: Install Tools
- [ ] Download & install [Android Studio](https://developer.android.com/studio)
- [ ] During setup install: Android SDK, Emulator, Gradle
- [ ] Create a test project → Run "Hello World" on the emulator
- [ ] Install [Git](https://git-scm.com/) for Windows

### Day 2-3: Create GitHub Repo
- [ ] Go to [github.com](https://github.com) → New Repository
- [ ] **Name:** `OIBSIP` (exactly this, all caps)
- [ ] Set to **Public**
- [ ] Add a README.md

**Your repo structure will look like this:**
```
OIBSIP/
├── README.md                              ← Main repo overview
├── Android-Task3-Calculator/              ← Calculator App
│   ├── app/src/...
│   ├── README.md
│   └── screenshots/
├── Android-Task5-Stopwatch/               ← Stopwatch App
│   ├── app/src/...
│   ├── README.md
│   └── screenshots/
└── Android-Task1-UnitConverter/           ← Unit Converter App
    ├── app/src/...
    ├── README.md
    └── screenshots/
```

> [!WARNING]
> **Folder naming MUST follow:** `OIBSIP/Android-Task[Number]-[ProjectName]/`
> Wrong naming = evaluation delays!

### Day 3-4: Learn Java + Android Basics
Since the internship requires **Java** (not Kotlin), refresh these:
- Variables, data types, strings
- `if/else`, `switch`, loops
- Arrays and ArrayLists
- Classes, objects, methods
- **Android specifics:**
  - What is an `Activity`?
  - How XML layouts work (`LinearLayout`, `ConstraintLayout`, `GridLayout`)
  - `findViewById()` — connecting XML views to Java code
  - `OnClickListener` — handling button taps
  - `Toast` messages
  - `Intent` — navigating between screens

### Day 5: LinkedIn + Telegram
- [ ] Watch the LinkedIn Profile Tutorial (shared in welcome email)
- [ ] Complete your LinkedIn profile
- [ ] Add headline: *"Android Developer Intern @ Oasis Infobyte | AICTE"*
- [ ] Join Telegram: [t.me/oasisinfobyte](https://t.me/oasisinfobyte)

---

## 📱 TASK 3: Calculator App (Week 1 — Start Here)

### 📋 Complete Feature Checklist
| # | Feature | Status |
|---|---------|--------|
| 1 | Display TextView showing current input and result | ⬜ |
| 2 | Number buttons (0–9) and decimal point | ⬜ |
| 3 | Operator buttons: +, −, ×, ÷ | ⬜ |
| 4 | Equals (=) button to evaluate | ⬜ |
| 5 | Clear (C) button to reset | ⬜ |
| 6 | Backspace button to delete last character | ⬜ |
| 7 | Division-by-zero error handling: show "Error" | ⬜ |
| 8 | Button grid using GridLayout or ConstraintLayout | ⬜ |
| 9 | No crashes on rapid/repeated button taps | ⬜ |

### 🏗️ Step-by-Step Build Guide

#### Step 1: Create the Project
- Open Android Studio → New Project → **Empty Activity**
- Name: `Calculator`
- Package: `com.fahad.calculator`
- Language: **Java**
- Minimum SDK: API 24

#### Step 2: Design the UI (`activity_main.xml`)
```
┌─────────────────────────────┐
│                      0      │  ← Result TextView (large font, right-aligned)
│─────────────────────────────│
│                   12 + 5    │  ← Input/Expression TextView
├───────┬───────┬───────┬─────┤
│   C   │  ( )  │   %   │  ÷  │
├───────┼───────┼───────┼─────┤
│   7   │   8   │   9   │  ×  │
├───────┼───────┼───────┼─────┤
│   4   │   5   │   6   │  −  │
├───────┼───────┼───────┼─────┤
│   1   │   2   │   3   │  +  │
├───────┼───────┴───────┼─────┤
│   0   │       .       │  =  │
└───────┴───────────────┴─────┘
        ← Use GridLayout (5 rows × 4 columns)
```

**Key XML components:**
- `TextView` for display (id: `tvResult`)
- `TextView` for expression (id: `tvExpression`)
- `Button` for each number/operator in a `GridLayout`
- Add a **Backspace (⌫)** button in the top row

#### Step 3: Write the Logic (`MainActivity.java`)
```java
// Core approach:
// 1. Use a StringBuilder to build the math expression as user taps buttons
// 2. When "=" is pressed, parse and evaluate the expression
// 3. Handle division by zero gracefully

public class MainActivity extends AppCompatActivity {
    
    private TextView tvExpression, tvResult;
    private StringBuilder currentExpression = new StringBuilder();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvExpression = findViewById(R.id.tvExpression);
        tvResult = findViewById(R.id.tvResult);
        
        // Set click listeners for all buttons
    }
    
    // Number button click → append to expression
    // Operator button click → append operator
    // Equals → evaluate expression
    // Clear → reset everything
    // Backspace → remove last character
}
```

#### Step 4: Handle Edge Cases
- Division by zero → show `"Error"`
- Multiple decimal points in same number → prevent
- Starting with an operator → prevent
- Rapid button tapping → don't crash

#### Step 5: Style It
- Dark background with colored buttons
- Operators in orange/accent color
- Numbers in white/light
- Large, clear display font

#### Step 6: Push to GitHub
```bash
# Copy the entire project folder into OIBSIP/Android-Task3-Calculator/
cd OIBSIP
git add Android-Task3-Calculator/
git commit -m "Task 3: Calculator App - Complete with all features"
git push origin main
```

---

## ⏱️ TASK 5: Stopwatch App (Week 2)

### 📋 Complete Feature Checklist
| # | Feature | Status |
|---|---------|--------|
| 1 | Large time display in HH:MM:SS (or MM:SS:ms) | ⬜ |
| 2 | Start button: begins timer from 0 or paused state | ⬜ |
| 3 | Stop/Pause button: freezes timer at current time | ⬜ |
| 4 | Reset button: stops and resets to 00:00:00 | ⬜ |
| 5 | Buttons change visual state (e.g., grey out when inactive) | ⬜ |
| 6 | Timer survives onPause/onResume lifecycle | ⬜ |
| 7 | (Bonus) Lap button with scrollable lap list | ⬜ |

### 🏗️ Step-by-Step Build Guide

#### Step 1: Create the Project
- Name: `Stopwatch`
- Package: `com.fahad.stopwatch`
- Language: **Java**

#### Step 2: Design the UI
```
┌─────────────────────────────┐
│                             │
│         00:00:00.00         │  ← Large centered time display
│                             │
├─────────┬─────────┬─────────┤
│  START  │  PAUSE  │  RESET  │  ← Control buttons
├─────────┴─────────┴─────────┤
│          [LAP]              │  ← Bonus: Lap button
├─────────────────────────────┤
│  Lap 1     00:05:23.45     │  ← Bonus: Scrollable lap list
│  Lap 2     00:12:10.82     │
│  Lap 3     00:18:44.31     │
└─────────────────────────────┘
```

#### Step 3: Core Logic — Handler + Runnable Pattern
```java
// This is the standard Android pattern for a stopwatch:
private Handler handler = new Handler();
private long startTime = 0L;
private long elapsedTime = 0L;
private boolean isRunning = false;

private Runnable timerRunnable = new Runnable() {
    @Override
    public void run() {
        long millis = System.currentTimeMillis() - startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;
        
        tvTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        handler.postDelayed(this, 10); // Update every 10ms
    }
};
```

#### Step 4: Handle Activity Lifecycle
```java
// IMPORTANT: The task requires the timer to survive screen rotation & background
@Override
protected void onPause() {
    super.onPause();
    // Save elapsed time so it continues when app resumes
}

@Override
protected void onResume() {
    super.onResume();
    // Restore timer state
}
```

#### Step 5: Button States
- When running: START greyed out, PAUSE & RESET active
- When paused: START active, PAUSE greyed out, RESET active
- When reset: Only START active

---

## 🔄 TASK 1: Unit Converter App (Week 3)

### 📋 Complete Feature Checklist
| # | Feature | Status |
|---|---------|--------|
| 1 | Input field for numeric value | ⬜ |
| 2 | Dropdown (Spinner) for source unit | ⬜ |
| 3 | Dropdown (Spinner) for target unit | ⬜ |
| 4 | Convert button → displays result | ⬜ |
| 5 | Result TextView with unit label | ⬜ |
| 6 | At least 3 categories: length, weight, + 1 more | ⬜ |
| 7 | Toast message if input is empty or non-numeric | ⬜ |
| 8 | Category selector that resets unit dropdowns | ⬜ |

### 🏗️ Step-by-Step Build Guide

#### Step 1: Create the Project
- Name: `UnitConverter`
- Package: `com.fahad.unitconverter`
- Language: **Java**

#### Step 2: Plan Your Categories

| Category | Units to Support |
|----------|-----------------|
| **Length** | Kilometer, Meter, Centimeter, Millimeter, Mile, Yard, Foot, Inch |
| **Weight** | Kilogram, Gram, Milligram, Pound, Ounce |
| **Temperature** | Celsius, Fahrenheit, Kelvin |

#### Step 3: Design the UI
```
┌──────────────────────────────┐
│     🔄 Unit Converter        │
├──────────────────────────────┤
│  Category:  [Length      ▼]  │  ← Spinner (category selector)
│                              │
│  Enter Value:                │
│  ┌────────────────────────┐  │
│  │ 100                    │  │  ← EditText
│  └────────────────────────┘  │
│                              │
│  From: [Centimeters     ▼]   │  ← Spinner (source unit)
│  To:   [Inches          ▼]   │  ← Spinner (target unit)
│                              │
│       [  CONVERT  ]          │  ← Button
│                              │
│  Result: 39.37 Inches        │  ← TextView
└──────────────────────────────┘
```

#### Step 4: Core Logic — Base Unit Conversion
```java
// Smart approach: convert everything to a BASE unit first, then to target
// For Length: base unit = Meter
// For Weight: base unit = Kilogram

double toBaseUnit(double value, String unit) {
    switch(unit) {
        case "Kilometer": return value * 1000;
        case "Centimeter": return value / 100;
        case "Mile": return value * 1609.34;
        case "Inch": return value * 0.0254;
        // ... etc
    }
}

double fromBaseUnit(double baseValue, String unit) {
    switch(unit) {
        case "Kilometer": return baseValue / 1000;
        case "Centimeter": return baseValue * 100;
        // ... etc
    }
}
```

#### Step 5: Category Selector Logic
```java
// When user changes category Spinner:
categorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
    @Override
    public void onItemSelected(...) {
        // Update both unit Spinners with units for the selected category
        // Clear the result
    }
});
```

#### Step 6: Input Validation
```java
String input = editText.getText().toString().trim();
if (input.isEmpty()) {
    Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
    return;
}
try {
    double value = Double.parseDouble(input);
} catch (NumberFormatException e) {
    Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
    return;
}
```

---

## 📝 README Template (Use for Each Task)

```markdown
# Task [X]: [App Name]

## 📖 Description
Brief description of what the app does and the problem it solves.

## 🎯 Features
- ✅ Feature 1 (from checklist)
- ✅ Feature 2
- ✅ Feature 3
- ✅ ... all features listed

## 📸 Screenshots
| Screen 1 | Screen 2 | Screen 3 |
|----------|----------|----------|
| ![](screenshots/screen1.png) | ![](screenshots/screen2.png) | ![](screenshots/screen3.png) |

## 🛠️ Tech Stack
- **Language:** Java
- **IDE:** Android Studio
- **Min SDK:** API 24
- **Layout:** GridLayout / ConstraintLayout

## 🚀 How to Run
1. Clone: `git clone https://github.com/YourUsername/OIBSIP.git`
2. Open `Android-Task[X]-[Name]` folder in Android Studio
3. Build and run on emulator or physical device

## 📹 Demo Video
[Watch Demo Video](link-to-your-video)

## 👤 Author
**Mirza Fahad Baig**
- Internship: Oasis Infobyte (AICTE)
- Track: Android App Development
- LinkedIn: [Your LinkedIn URL]
```

---

## 🎬 Demo Video Requirements

> [!IMPORTANT]
> Videos that don't follow this format **will be returned for correction**.

### Required Format:
```
[0:00 - 0:02]  TITLE CARD (static frame):
               ┌──────────────────────────────┐
               │  Mirza Fahad Baig            │
               │  Track: Android App Dev       │
               │  Task 3: Calculator App       │
               └──────────────────────────────┘

[0:02 - end]   LIVE DEMO:
               Show every feature working.
               Don't just show static screenshots.
```

### How to Record:
- **Android Studio Emulator:** Click the camera icon → Screen Record
- **Physical Device:** Use built-in screen recorder (or AZ Screen Recorder app)
- **Editing:** Add the title card using any free video editor (CapCut, DaVinci Resolve)
- **Upload to:** YouTube (unlisted) or Google Drive (anyone with link)

---

## 📣 LinkedIn Post Template

```
🚀 Completed Task [X] of my Android Development Internship at Oasis Infobyte!

📱 Project: [App Name]
🔧 Tech: Java | Android Studio | XML

Key features I implemented:
✅ Feature 1
✅ Feature 2  
✅ Feature 3

🎥 Watch the demo: [video link]
💻 Source code: [github link]

This internship is helping me build real-world Android development skills.
Grateful for the opportunity!

@Oasis Infobyte

#oasisinfobyte #androiddev #android #java #internship #AICTE
#mobileappdevelopment #appdevelopment
```

---

## ✅ Final Submission Checklist

### Before Submitting (Sep 9-14):

**GitHub:**
- [ ] Repo named exactly `OIBSIP` (all caps, public)
- [ ] Folder: `Android-Task3-Calculator/` with code + README + screenshots
- [ ] Folder: `Android-Task5-Stopwatch/` with code + README + screenshots
- [ ] Folder: `Android-Task1-UnitConverter/` with code + README + screenshots
- [ ] Main `README.md` in repo root with overview of all tasks

**Demo Videos (one per task):**
- [ ] Each video starts with 2-second title card (Name + Track + Task)
- [ ] Each video shows the app working end-to-end
- [ ] Videos uploaded to YouTube/Google Drive

**LinkedIn (one post per task):**
- [ ] Posted demo video for Task 3
- [ ] Posted demo video for Task 5
- [ ] Posted demo video for Task 1
- [ ] Tagged @Oasis Infobyte in every post
- [ ] Used `#oasisinfobyte` + `#androiddev` in every post

**Peer Evaluation:**
- [ ] Watched at least 2 other interns' demo videos on LinkedIn
- [ ] Left **substantive comments** (not just "Great work!" — mention specific features, ask questions, give constructive feedback)

**Final Submit:**
- [ ] Submit via the **official Task Submission Form** (from your email)
- [ ] Include `OIBSIP` GitHub repo link
- [ ] Include demo video links

> [!CAUTION]
> **Plagiarism = Immediate Termination.** Learn from tutorials, but write every line yourself. Understand what each line does. Evaluators can tell copy-pasted code.

---

## 🆘 Getting Help

| Resource | When to Use |
|----------|------------|
| [Telegram Group](https://t.me/oasisinfobyte) | Internship-specific questions |
| [Android Developer Docs](https://developer.android.com/docs) | Official references |
| YouTube tutorials (see each task's self-sourcing guide) | Learning how to build |
| [Stack Overflow](https://stackoverflow.com) | Debugging errors |
| **Me (your AI mentor)** | Code walkthroughs, reviews, debugging |

> When you're ready to start building any task, just say:
> *"Let's build Task 3: Calculator"* — and I'll write the complete code with you!
