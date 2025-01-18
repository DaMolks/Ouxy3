# Documentation de l'Architecture

## Vue d'Ensemble

L'application Ouxy3 suit une architecture modulaire MVVM avec les composants suivants :

### Core Application
- Base stable et centrale
- Gestion des modules
- Base de données Room
- EventBus pour la communication
- Gestion de session

### Modules
- Modules dynamiques et indépendants
- Communication via EventBus
- Accès aux données via Repository

### Flux de Données
```
[UI] <-> [ViewModel] <-> [Repository] <-> [Room/DataStore]
```

## Composants Clés

### Base de Données
```kotlin
@Database(
    entities = [Technician::class, Site::class],
    version = 1,
    exportSchema = true
)
abstract class OuxyDatabase : RoomDatabase()
```

### EventBus
```kotlin
interface OuxyEvent {
    val type: String
    val version: Int
    val source: String
    val timestamp: Long
}
```

### Injection de Dépendances
```kotlin
val appModule = module {
    single { SessionManager(get()) }
    single { OuxyDatabase.getInstance(get()) }
}
```

### Navigation
- Navigation Component
- Nav graphs par feature
- Deep links support

## Guides de Développement

### Création d'un Module
1. Définir l'interface publique
2. Implémenter la logique métier
3. Créer les vues nécessaires
4. Enregistrer dans Koin

### Gestion des Données
1. Définir le modèle Room
2. Créer le DAO
3. Implémenter le Repository
4. Injecter via Koin

### Communication Inter-Modules
1. Définir l'événement
2. Publier via EventBus
3. S'abonner aux événements

## Sécurité

### Stockage
- Chiffrement des données sensibles
- SharedPreferences sécurisées

### Isolation
- Modules en sandbox
- Permissions granulaires

## Tests

### Tests Unitaires
- ViewModels
- Repositories
- Use Cases

### Tests d'Intégration
- Base de données
- Navigation
- EventBus

## Performance

### Optimisations
- Lazy loading des modules
- Cache intelligent
- Coroutines pour l'asynchrone

### Monitoring
- Crash reporting
- Analytics
- Logs structurés
