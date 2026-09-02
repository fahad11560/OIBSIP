# Goal: Build Task 1 (Unit Converter App)

This plan outlines the architecture and implementation steps for the Unit Converter app (Task 1 of the OIBSIP internship), incorporating the baseline requirements from the internship guide, your custom preferences, and **full Light/Dark mode support**. 

We will follow a **strict Step-by-Step Workflow**. I will complete one step, test it, and wait for your explicit confirmation before moving on to the next.

## App Overview
A colorful, playful Android Unit Converter supporting Length, Weight, and Temperature. It features a modern gradient-based UI that adapts seamlessly to Light and Dark themes, a unit swap functionality, and real-time auto-conversion as the user types.

## Proposed Features

### 1. Categories & Units
- **Length**: Kilometer, Meter, Centimeter, Millimeter, Mile, Yard, Foot, Inch
- **Weight**: Kilogram, Gram, Milligram, Pound, Ounce
- **Temperature**: Celsius, Fahrenheit, Kelvin

### 2. User Interface (Playful with Light/Dark Mode)
- **Background**: Soft gradient or solid background that looks vibrant in light mode and deep/soothing in dark mode.
- **Inputs & Cards**: Rounded `EditText` and Card-based layout with adaptive colors (e.g., white in light mode, dark gray in dark mode).
- **Dropdowns**: `Spinner` views for Category, Source Unit, and Target Unit.
- **Buttons**:
  - A prominent, pill-shaped **Convert** button.
  - A **Swap (🔄)** icon button situated between the Source and Target spinners.
- **Typography**: Clean system font, elevated using weight and size contrast.

### 3. Core Functionality
- **Auto-Conversion**: Implementing a `TextWatcher` on the input field to instantly calculate and display the result as the user types.
- **Manual Conversion**: The Convert button will still function as a manual trigger, satisfying the internship guide requirements.
- **Dynamic Spinners**: Changing the selected category will automatically populate the "From" and "To" spinners with the relevant units and reset fields.

---

## Step-by-Step Implementation Workflow

> [!IMPORTANT]
> I will execute this plan one step at a time. After each step, I will pause, ask you to test/verify the app, and wait for your "go ahead" before proceeding.

### Step 1: UI Foundations & Theming
- Update `values/colors.xml` and `values-night/colors.xml` with dynamic light/dark palettes.
- Create necessary UI drawables in `res/drawable/`:
  - `bg_button_gradient.xml` (pill-shaped convert button)
  - `bg_input.xml` (rounded background for inputs/spinners)
  - `ic_swap.xml` (vector asset for swapping)
- **Test Checkpoint:** You will verify the colors and build success.

### Step 2: Designing the Main Layout
- Overhaul `activity_main.xml` using `ConstraintLayout`.
- Add all required components: Category Spinner, Input field, From/To Spinners, Swap button, Convert button, and Result TextView.
- Ensure the layout is responsive and respects Light/Dark theme colors.
- **Test Checkpoint:** You will run the app on an emulator/device and confirm the layout looks good in both themes.

### Step 3: Implementing Core Logic
- Modify `MainActivity.java` to link all UI elements.
- Setup `ArrayAdapter` for dynamic unit population based on the selected category.
- Implement the "Base Unit" architecture for conversion logic (Length, Weight, Temperature).
- **Test Checkpoint:** You will test category switching and ensure the correct units appear in the Spinners.

### Step 4: Adding Polish (Auto-Convert & Validation)
- Add `TextWatcher` to `EditText` for real-time auto-conversion.
- Add `OnClickListener` to the Swap button.
- Add input validation (`Toast` for empty inputs).
- **Test Checkpoint:** You will do a final end-to-end test of all calculations, edge cases, and the swap button.

### Step 5: Final Submission Setup
- Create `Android-Task1-UnitConverter/README.md`.
- Create `Android-Task1-UnitConverter/screenshots/` folder.
- Update the root repository `README.md`.
- Commit and push to GitHub.

---

## User Review Required

> [!NOTE]
> Does this step-by-step approach and the Light/Dark mode integration align with your expectations? Click **Proceed** to approve this plan, and I will immediately begin **Step 1**.
