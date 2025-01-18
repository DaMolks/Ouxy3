# Guide de Développement

## Configuration de l'Environnement

### Prérequis
- Android Studio Arctic Fox ou supérieur
- JDK 11
- Android SDK 34
- Gradle 8.2

### Configuration
1. Cloner le repository
2. Synchroniser avec Gradle
3. Vérifier les dépendances

## Architecture

### Structure du Projet
```
app/
├── core/          # Composants de base
├── data/          # Couche de données
├── di/            # Injection de dépendances
├── ui/            # Interface utilisateur
│   ├── main/        # Écrans principaux
│   └── onboarding/   # Écrans d'onboarding
└── util/          # Utilitaires
```

### Modules
- **Core** : Composants essentiels
- **Data** : Gestion des données
- **UI** : Interface utilisateur

## Guidelines

### Code
- Suivre les conventions Kotlin
- Documenter les classes et méthodes publiques
- Écrire des tests unitaires
- Garder les fonctions courtes et focalisées

### Architecture
- MVVM pour les écrans
- Clean Architecture pour les données
- Injection de dépendances avec Koin
- Navigation avec NavComponent

### UI/UX
- Material Design 3
- Accessibilité
- Support des thèmes
- Support des langues

## Processus de Développement

### Nouvelle Fonctionnalité
1. Créer une branche
2. Implémenter les tests
3. Développer la fonctionnalité
4. Vérifier la couverture de tests
5. Soumettre une PR

### Correction de Bug
1. Reproduire le bug
2. Écrire un test
3. Corriger le bug
4. Vérifier la correction
5. Mettre à jour la documentation

## Tests

### Types de Tests
- Tests unitaires (JUnit)
- Tests d'intégration (Espresso)
- Tests UI (Compose)

### Exécution des Tests
```bash
# Tests unitaires
./gradlew test

# Tests d'intégration
./gradlew connectedAndroidTest

# Couverture
./gradlew createDebugCoverageReport
```