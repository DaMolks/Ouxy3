# Ouxy3

## Description
Ouxy3 est une application Android de gestion de rapports d'intervention avec une architecture modulaire. Elle permet aux techniciens de gérer leurs interventions, sites et rapports de manière efficace et intuitive.

## Architecture
- **Core Application** : Base stable gérant les modules et la persistence
- **Modules Dynamiques** : Fonctionnalités isolées et indépendantes
- **EventBus** : Communication inter-modules centralisée

## Fonctionnalités
- Gestion des profils techniciens
- Capture de signature numérique
- Gestion des sites d'intervention
- Synchronisation et backup des données

## Technologies Utilisées
- Kotlin 1.9.22
- Android SDK 34 (min SDK 23)
- Room pour la persistence
- Koin pour l'injection de dépendances
- Navigation Component
- Material Design 3
- Tests unitaires et d'intégration

## Mise en Route
1. Cloner le repository
2. Ouvrir dans Android Studio
3. Synchroniser avec Gradle
4. Lancer l'application

## Structure du Projet
```
app/
├── core/          # Composants centraux
├── data/          # Couche données
├── ui/            # Interface utilisateur
│   ├── splash/      # Écran de démarrage
│   ├── onboarding/  # Configuration initiale
│   └── main/        # Écrans principaux
└── util/          # Utilitaires
```

## Tests
```bash
# Tests unitaires
./gradlew test

# Tests d'intégration
./gradlew connectedAndroidTest
```

## Documentation
Consulter le dossier `docs/` pour :
- Guide d'architecture
- Documentation technique
- Guidelines de développement

## Contribution
1. Fork le projet
2. Créer une branche feature
3. Commit les changements
4. Push vers la branche
5. Créer une Pull Request

## Licence
Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.
