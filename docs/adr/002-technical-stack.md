# Stack Technique

## Choix Technologiques

### Android Core
- Kotlin 1.9.22
- Android SDK 34
- Gradle 8.2

### UI/UX
- Material Design 3
- Navigation Component
- View Binding
- Lottie pour les animations

### Base de Données
- Room
- SQLite
- SharedPreferences

### Asynchrone
- Coroutines
- Flow
- LiveData

### DI
- Koin

### Tests
- JUnit
- Espresso
- Coroutines Test

## Justifications

### 1. Kotlin vs Java
- Null safety native
- Coroutines pour l'asynchrone
- Extensions fonctions
- Code plus concis

### 2. Room vs SQLite Direct
- Validation à la compilation
- Mapping objet-relationnel
- Support Coroutines natif

### 3. Koin vs Dagger/Hilt
- Setup minimal
- Pure Kotlin
- Courbe d'apprentissage douce

### 4. Material Design 3
- Standard Android moderne
- Composants prêts à l'emploi
- Thèmes dynamiques

## Contraintes Techniques

### Minimum SDK : 23 (Android 6.0)
- Couvre >95% des appareils
- Accès aux APIs modernes
- Support WorkManager

### Build & Deploy
- APK < 10MB
- Modules téléchargeables à la demande
- Mises à jour indépendantes