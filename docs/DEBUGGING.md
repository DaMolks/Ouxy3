# Guide de Debugging Ouxy3

## Problèmes Connus et Solutions

### 1. Gestion des Sessions
Problème : Utilisation incorrecte des nouvelles API de chiffrement
Solution : 
- Mise à jour vers les nouvelles API de sécurité
- Ajout de gestion d'erreurs robuste
- Documentation des exceptions possibles

### 2. Interface Utilisateur
Problème : Détection de signature et états des boutons
Solution :
- Refactoring du SignaturePad
- Amélioration de la gestion d'état
- Tests de validation ajoutés

### 3. Gestion des Modules
Problème : Chargement et monitoring des modules
Solution :
- Nouveau système de monitoring
- Interface de debug dédiée
- Logging amélioré

## Outils de Debugging

### ModuleMonitor
```kotlin
val moduleMonitor = get<ModuleMonitor>()
moduleMonitor.moduleStates.collect { states ->
    // Monitoring des états
}
```

### Logs
```kotlin
logger.debug("Module state", mapOf(
    "moduleId" to id,
    "state" to state,
    "error" to error
))
```

## Processus de Vérification

1. Vérifier l'état des modules
2. Consulter les logs d'erreur
3. Tester les corrections
4. Valider les changements

## Points d'Attention

- Toujours vérifier la sécurité des sessions
- Valider les transitions UI
- Tester le chargement des modules
- Documenter les corrections