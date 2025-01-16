# Guidelines pour les Modules

## Structure des Modules

### Package Structure
```
module/
├── api/           # Interfaces publiques
├── internal/      # Implémentation
├── ui/            # Interface utilisateur
└── data/          # Données du module
```

### Interfaces Requises
```kotlin
interface OuxyModule {
    fun initialize(): Boolean
    fun onStart()
    fun onStop()
    fun onError(error: Throwable)
    fun getMetadata(): ModuleMetadata
}
```

## Communications

### EventBus
- Events typés uniquement
- Validation des permissions
- Gestion des erreurs

### Données Partagées
- Via ContentProvider
- Permissions déclaratives
- Validation des types

## Sécurité

### Isolation
- Processus séparé
- Sandbox module
- Permissions granulaires

### Validation
- Signature du module
- Version minimale core
- Tests d'intégration

## UI/UX

### Guidelines
- Respect Material Design 3
- Support thèmes système
- Composants standards

### Performance
- Lazy loading
- Cache intelligent
- Optimisation mémoire