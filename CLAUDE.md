# KAKEBO AI — Rastreador de Gastos con IA

## Qué es esta app
App Android nativa de finanzas personales llamada **KAKEBO AI**. Permite importar movimientos bancarios desde CSV/Excel, visualizar métricas de gasto, y chatear con IA (Gemini) para analizar los datos. Opera en modo **Offline First** con persistencia local en Room. Proyecto de portafolio orientado a LinkedIn.

## Stack técnico
- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose + Material3
- **Arquitectura:** MVVM (ViewModel + StateFlow)
- **Base de datos local:** Room (SQLite)
- **IA:** Gemini API (gemini-2.0-flash) via Google AI SDK para Android
- **Importación:** CSV/XLSX/TSV desde almacenamiento local (Google Drive en evaluación)
- **Navegación:** Navigation Compose con Bottom Navigation Bar

## Pantallas y navegación
Bottom Navigation Bar con 4 tabs:

1. **Dashboard** — métricas generales, gráfica de torta por categoría, barras por mes, actividad reciente
2. **Gastos** — lista completa de movimientos importados
3. **Chat IA** — conversación con Gemini sobre los datos de gastos
4. **Importar** — carga de extractos bancarios CSV/XLSX/TSV desde el dispositivo

## Estructura de datos de gastos
Columnas del extracto bancario:
- `fecha`: fecha del movimiento (DD/MM/YYYY)
- `descripcion`: nombre del comercio o concepto
- `monto`: valor del gasto en CLP (negativo = egreso)
- `categoria`: categoría del gasto (Alimentación, Transporte, Servicios & Hogar, Entretenimiento, Salud & Otros)
- `tipo_pago`: Tarjeta Débito / Tarjeta Crédito / Transferencia

## Estructura de paquetes
```
com.example.myapplication
├── data
│   ├── db/          # Room: entidades, DAOs, Database
│   └── repository/  # repositorios
├── ui
│   ├── dashboard/   # pantalla de métricas
│   ├── expenses/    # lista de gastos
│   ├── chat/        # chat con Gemini
│   ├── import/      # importación de archivos
│   └── theme/       # colores, tipografía, tema
└── MainActivity.kt
```

## Diseño visual
Design system completo en `design/autonomous_financial_intelligence/DESIGN.md`.
Capturas de pantalla y código HTML de referencia en `design/`.

### Resumen del sistema de diseño
- **Nombre:** Autonomous Financial Intelligence
- **Tema:** Dark OLED, estilo fintech/bancario moderno
- **Fondo principal:** `#10141a` (Deep Obsidian)
- **Primario (fiscal):** `#00C853` (Fintech Emerald)
- **IA primario:** `#8B5CF6` (Electric Violet) → gradiente a `#38BDF8` (Cyan)
- **Error/negativo:** `#FF5252` (Coral Crimson)
- **Advertencia:** `#F59E0B` (Amber)
- **Texto primario:** `#FFFFFF` | **Texto secundario:** `#8B949E`
- **Tipografía:** Inter (UI general) + JetBrains Mono (valores monetarios, timestamps, hashes)
- **Moneda:** CLP (Peso Chileno)

### Pantallas diseñadas (ver `design/`)
| Carpeta | Pantalla |
|---|---|
| `dashboard_rastreador_de_gastos_ia/` | Dashboard con métricas, gráficas y actividad reciente |
| `chat_ia_asistente_gemini/` | Chat IA con Gemini |
| `importar_carga_de_datos_y_preview/` | Importar extractos con previsualización |

## Estado actual de features

| Feature | Estado |
|---|---|
| Importar CSV/XLSX/TSV local | Prioritario — arrancar por aquí |
| Previsualización antes de importar | Incluida en pantalla de importación |
| Google Drive | Beta / En evaluación — no implementar aún |
| Lista de gastos (Gastos tab) | Pendiente |
| Dashboard con gráficas | Pendiente |
| Chat con Gemini | Pendiente |
| Escaneo con cámara (ML Kit) | DESHABILITADO — botón visible con label "Próximamente - Fase 2" |

## Reglas importantes
- **NO habilitar la cámara todavía.** Botón visible, sin permisos ni lógica funcional. Label: "Próximamente — Fase 2: Procesamiento OCR con ML Kit Vision."
- **Google Drive** aparece en el diseño como "Beta / En evaluación" — mostrar el botón pero sin conectar OAuth todavía.
- **Offline First:** toda la lógica de persistencia va en Room. Sin dependencia de red para las features core.
- **Moneda:** CLP. Los montos son negativos para egresos.
- Gemini API key no debe hardcodearse en código productivo; para demo/portafolio es una limitación conocida documentada.
- **Prioridad:** flujo de importación y parsing correcto antes de cualquier otra feature.

## Convenciones de código
- Un archivo por Composable principal
- ViewModels con `StateFlow` para estado de UI
- Coroutines + `viewModelScope` para operaciones asíncronas
- Sin comentarios obvios; solo comentar WHY cuando no es evidente
