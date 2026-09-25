---
name: Autonomous Financial Intelligence
colors:
  surface: '#10141a'
  surface-dim: '#10141a'
  surface-bright: '#353940'
  surface-container-lowest: '#0a0e14'
  surface-container-low: '#181c22'
  surface-container: '#1c2026'
  surface-container-high: '#262a31'
  surface-container-highest: '#31353c'
  on-surface: '#dfe2eb'
  on-surface-variant: '#bbcbb8'
  inverse-surface: '#dfe2eb'
  inverse-on-surface: '#2d3137'
  outline: '#869583'
  outline-variant: '#3c4a3c'
  surface-tint: '#3ce36a'
  primary: '#3fe56c'
  on-primary: '#003912'
  primary-container: '#00c853'
  on-primary-container: '#004c1b'
  inverse-primary: '#006e2a'
  secondary: '#d0bcff'
  on-secondary: '#3c0091'
  secondary-container: '#571bc1'
  on-secondary-container: '#c4abff'
  tertiary: '#82d2ff'
  on-tertiary: '#00354a'
  tertiary-container: '#31b9f4'
  on-tertiary-container: '#004661'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#69ff87'
  primary-fixed-dim: '#3ce36a'
  on-primary-fixed: '#002108'
  on-primary-fixed-variant: '#00531e'
  secondary-fixed: '#e9ddff'
  secondary-fixed-dim: '#d0bcff'
  on-secondary-fixed: '#23005c'
  on-secondary-fixed-variant: '#5516be'
  tertiary-fixed: '#c4e7ff'
  tertiary-fixed-dim: '#7bd0ff'
  on-tertiary-fixed: '#001e2c'
  on-tertiary-fixed-variant: '#004c69'
  background: '#10141a'
  on-background: '#dfe2eb'
  surface-variant: '#31353c'
typography:
  display-lg:
    fontFamily: Inter
    fontSize: 48px
    fontWeight: '800'
    lineHeight: 56px
    letterSpacing: -0.04em
  display-lg-mobile:
    fontFamily: Inter
    fontSize: 36px
    fontWeight: '800'
    lineHeight: 44px
    letterSpacing: -0.03em
  headline-lg:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: -0.02em
  headline-lg-mobile:
    fontFamily: Inter
    fontSize: 26px
    fontWeight: '700'
    lineHeight: 32px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Inter
    fontSize: 22px
    fontWeight: '600'
    lineHeight: 28px
    letterSpacing: -0.01em
  headline-sm:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '600'
    lineHeight: 24px
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  body-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '400'
    lineHeight: 16px
  numeric-hero:
    fontFamily: Inter
    fontSize: 40px
    fontWeight: '800'
    lineHeight: 48px
    letterSpacing: -0.03em
  numeric-hero-mobile:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '800'
    lineHeight: 40px
    letterSpacing: -0.02em
  numeric-md:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '700'
    lineHeight: 24px
    letterSpacing: -0.01em
  label-mono-sm:
    fontFamily: JetBrains Mono
    fontSize: 11px
    fontWeight: '500'
    lineHeight: 14px
    letterSpacing: 0.02em
  label-md:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '600'
    lineHeight: 16px
    letterSpacing: 0.04em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 1rem
  margin: 1rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2rem
---

## Brand & Style

This design system delivers an intelligent, hyper-modern financial control center engineered for native Android experiences. It fuses Material 3 precision with computational fintech authority: combining the disciplined density of developer-grade tooling with the fluid intelligence of advanced AI assistance.

The aesthetic philosophy bridges deep-space OLED blacks with precision emissive accents:
- **Atmosphere:** Controlled, high-contrast, distraction-free nocturnal finance.
- **Duality of Function:** Emerald signifies definitive fiscal health and verified ledger state; electric violet-to-cyan gradients signify generative intelligence, predictive analysis, and ongoing processing.
- **Physicality:** Subtle edge-lit borders, stacked surface containers, and deliberate micro-tactility derived from Android Jetpack Compose elevation mechanics.

## Colors

The palette operates on a calibrated dark-surface hierarchy engineered for OLED battery conservation and sharp alphanumeric legibility under varying ambient lighting conditions.

### Surface Tonal Hierarchy
- **Canvas / Root Background:** `#0D1117` (Deep Obsidian). Absolute canvas baseline.
- **Surface Container (Tier 1):** `#161B22` (Charcoal). Primary card container and listing backdrop.
- **Surface Container High (Tier 2):** `#21262D` (Elevated Graphite). Modals, popovers, nested sheets, and interactive target backgrounds.
- **Border / Structural Divider:** `#30363D` (Slate Stroke). Low-luminance structural delineation for cards, separators, and segmented controls.

### Functional Accents & Semantic Tones
- **Primary / Fiscal Growth:** `#00C853` (Fintech Emerald). Core primary buttons, verified transaction badges, positive balances, active toggles.
- **Primary Subtle / Income Accent:** `#2ECC71` (Mint Leaf). Secondary positive stats, chart strokes, trend upward indicators.
- **AI Primary:** `#8B5CF6` (Electric Violet). AI assistant threads, Gemini query anchors, active predictive chips.
- **AI Secondary:** `#38BDF8` (Atmospheric Cyan). Gradient termination for AI prompt fields, predictive insight cards, and streaming tokens.
- **AI Synthetic Gradient:** Linear progression from `#8B5CF6` (0%) to `#38BDF8` (100%), angled at 135deg for ambient indicators, active AI fab borders, and intelligent query triggers.
- **Negative / Alert:** `#FF5252` (Coral Crimson). Expense spikes, destructive prompts, overdraft thresholds, and downward deviations.
- **Warning:** `#F59E0B` (Amber). Pending imports, uncategorized expenses, and synchronization gaps.

### Text & Content Lumens
- **Text Primary:** `#FFFFFF` (Full Lumen White). Core ledger totals, card titles, hero data.
- **Text Secondary:** `#8B949E` (Slate Subdued). Category tags, timestamps, secondary metrics, column headers.
- **Text Disabled / Muted:** `#484F58` (Low Charcoal). Inactive toggles, placeholder metadata, unfocused state items.

## Typography

Typography establishes an immediate structural split: clean, highly legible grotesque forms for navigation and narrative interfaces, paired with technical monospaced and tabular execution for monetary metrics.

- **Typefaces:** **Inter** serves as the primary system engine across all structural headings and narrative contents. **JetBrains Mono** is reserved strictly for machine timestamps, transaction reference hashes, raw card ingestion previews, and category data micro-labels.
- **Monetary Tabular Rules:** All instances of currency values, percentage swings, and account numbers must leverage tabular lining (`tnum` OpenType feature flag) to ensure numerical tracking aligns predictably during streaming data updates and list scanning.
- **Weight Contrast:** Balance totals leverage extra-bold (`800`) weights with tighter tracking to deliver instant data comprehension within small mobile hero footprints.

## Layout & Spacing

The layout is built for native vertical-flow Android interfaces utilizing an 8-point spatial model (with 4-point micro-adjustments for compact chip groupings and badge offsets).

### Viewport Scaffolding
- **Phone (<600dp):** Single-column fluid arrangement. Lateral margins sit strictly at `16px` (`margin`), maximizing horizontal space for wide transaction rows and multi-metric charts.
- **Tablet / Large Foldable (600dp - 840dp):** Dual-column layout where the left column houses the active balance card, rapid action inputs, and chat interface, while the right column handles transaction tables and analytical breakdowns. Gutter steps to `24px`.
- **Desktop / DeX (>840dp):** 12-column grid system anchored to a max-width of `1280px` centered, utilizing `24px` margins and `16px` gutters.

### Structural Flow Rules
- Safe area insets account for Android edge-to-edge execution (transparent system bars).
- Dynamic navigation spacing enforces bottom clearance of `88px` above the bottom navigation bar to prevent sticky floating action buttons or quick-add triggers from overlapping the viewport end-state.

## Elevation & Depth

Visual depth is achieved through **structural surface tiering** augmented by **diffused neon luminescence** rather than traditional murky dropshadows.

### Material 3 Surface Depths
- **Level 0 (Canvas):** `#0D1117` — Raw background. Zero elevation.
- **Level 1 (Base Container):** `#161B22` with a `1px` border of `#30363D`. Used for static feed items, default cards, and charts.
- **Level 2 (Active / Raised Container):** `#21262D` with a `1px` border of `#30363D`. Used for interactive cards, dropdowns, sticky action sheets, and unselected bottom sheets.
- **Level 3 (AI Focus / Modal):** `#161B22` accented with a `1px` border using an alpha gradient (`rgba(139, 92, 246, 0.4)` to `rgba(56, 189, 248, 0.2)`).

### Atmospheric Shadows
- **Standard Card Elevation:** Soft dark ambient shadow: `0 4px 16px -2px rgba(0, 0, 0, 0.6)`.
- **Emerald Glow (Primary Actions):** Primary interaction items project an intentional ambient cast: `0 8px 24px -4px rgba(0, 200, 83, 0.25)`.
- **AI Pulsing Aura:** Surfaces parsing Gemini inference feature a diffuse atmospheric blur: `0 0 24px 0 rgba(139, 92, 246, 0.15)`.

## Shapes

This design system uses a calibrated rounded geometry honoring Material 3 expressive components:

- **Base Cards & Major Containers:** `16px` (`rounded-lg`) to `24px` (`rounded-xl` / `rounded-2xl` Compose shape) for comprehensive framing of transaction groups and account summaries.
- **Interactive Buttons & Form Fields:** `12px` to `16px` corner curvature, delivering a tactile, modern touch target.
- **Chips, Category Tags, and Indicators:** Strict `9999px` full-pill geometries for quick visual segmentation against rectangular card containers.
- **AI Response Bubbles:** Asymmetric rounding featuring `18px` on three corners and `4px` on the origin corner to denote conversational directionality.

## Components

### Buttons
- **Primary Fiscal Action:** Solid `#00C853`, text `#0D1117` (Inter Bold), rounded to `12px`. Pressed state transitions to `#2ECC71`. Height: `48px`.
- **AI Core Action:** Linear gradient background (`#8B5CF6` to `#38BDF8`), text `#FFFFFF` (Inter Bold). Enclosed with a high-glow inner border (`rgba(255, 255, 255, 0.2)`).
- **Secondary / Outline:** Container `#161B22`, border `1px solid #30363D`, text `#FFFFFF`. Hover/Focused state illuminates border to `#8B949E`.

### Filter & Category Chips
- **Structural Spec:** Full-pill shape (`9999px`), height `32px`, padding `0 12px`.
- **Default State:** Surface `#21262D`, border `1px solid #30363D`, text `#8B949E`.
- **Selected State (Fiscal):** Surface `rgba(0, 200, 83, 0.15)`, border `1px solid #00C853`, text `#00C853`.
- **Selected State (AI Filter):** Surface `rgba(139, 92, 246, 0.15)`, border `1px solid #8B5CF6`, text `#FFFFFF`. Includes a leading `14px` spark icon.

### Transaction Lists
- **Item Wrapper:** Background transparent or `#161B22`, separated by an inset border `1px solid rgba(48, 54, 61, 0.6)`. 
- **Leading Element:** Category icon container (`40x40px`, `rounded-lg`, background `#21262D`, text-accented icon).
- **Center Body:** Category/Merchant name (`Inter SemiBold 14px`, `#FFFFFF`) over Datestamp/Payment Method (`Inter Regular 12px`, `#8B949E`).
- **Trailing Amount:** Negative values rendered in `#FFFFFF` with muted minus sign; positive values rendered in bold `#00C853`.

### Input Fields & Search Bars
- **Container:** Height `52px`, background `#161B22`, border `1px solid #30363D`, border radius `12px`, text `#FFFFFF`.
- **Focused State:** Border changes to `1.5px solid #00C853` with a subtle outer glow `rgba(0, 200, 83, 0.2)`.
- **AI Prompt Input Field:** Background `#21262D`, border `1px solid rgba(139, 92, 246, 0.5)`. Accompanied by a trailing iridescent micro-action icon that pulses during streaming API transactions.

### Cards
- **Account Balance Hero Card:** Surface `#161B22`, `rounded-2xl` (`24px`), border `1px solid #30363D`. Contains primary numerical hero metrics (`#FFFFFF`), dynamic sparkline graphs, and quick transfer/add buttons.
- **AI Insight Card:** Surface `#161B22`, framed with a persistent top border gradient (`#8B5CF6` to `#38BDF8`, `2px` thick). Houses real-time expenditure warnings, auto-categorization suggestions, and conversational prompts.

### Bottom Navigation Bar
- **Bar Shell:** Height `72px`, background `rgba(22, 27, 34, 0.85)` with system-level backdrop blur (`20px`). Top border `1px solid #30363D`.
- **Tabs (4):** `Dashboard`, `Gastos`, `Chat IA`, `Importar`.
- **Inactive Item:** `#8B949E` icon and label (`Inter Medium 11px`).
- **Active Standard Item:** Active indicator pill (`rgba(0, 200, 83, 0.15)`, `64x32px`), icon and label `#00C853`.
- **Active AI Tab:** Active indicator pill (`rgba(139, 92, 246, 0.2)`), icon rendered with multi-stop gradient (`#8B5CF6` to `#38BDF8`), label `#FFFFFF`.

### Sleek Data Charts
- **Palette Spline:** Lines plotted with a `2.5px` stroke using `#00C853` with a vertical gradient fade fill (`rgba(0, 200, 83, 0.2)` down to `rgba(0, 200, 83, 0.0)`).
- **Negative Variance Spline:** Line stroke `#FF5252` with corresponding red base glow.
- **Grid Lines:** Minimal dotted horizontal metrics using `#21262D`. Axis labels in `JetBrains Mono 11px` colored `#8B949E`.