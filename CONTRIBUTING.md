# Guide de Contribution

## Process de Contribution

### 1. Configuration de l'Environnement
- Android Studio Hedgehog | 2023.1.1+
- JDK 17
- Git
- Gradle 8.2+

### 2. Fork et Clone
```bash
# Fork sur GitHub puis
git clone https://github.com/VotreUsername/Ouxy3.git
cd Ouxy3
git remote add upstream https://github.com/DaMolks/Ouxy3.git
```

### 3. Branches
- `feature/*` pour les nouvelles fonctionnalités
- `fix/*` pour les corrections de bugs
- `docs/*` pour la documentation
- `test/*` pour l'ajout de tests

## Standards de Code

### Kotlin
- Suivre les conventions Kotlin officielles
- Utiliser les coroutines pour l'asynchrone
- Privilégier l'immutabilité (val > var)

### Architecture
- Architecture MVVM stricte
- Un ViewModel par écran
- Repository Pattern pour les données
- Injection de dépendances avec Koin

### Tests
- Tests unitaires obligatoires pour
  * ViewModels
  * Repositories
  * Use Cases
- Tests d'intégration pour
  * Navigation
  * Base de données

## Process de Commit

### Format des Messages
```
<type>: <sujet>

<description>
```

### Types
- `feat`: Nouvelle fonctionnalité
- `fix`: Correction de bug
- `docs`: Documentation
- `test`: Ajout/modification de tests
- `refactor`: Refactoring
- `style`: Formatage, lint

### Exemple
```
feat: Ajoute la capture de signature

Implémente une vue custom SignaturePad pour
permettre aux techniciens de signer numériquement.
```

## Pull Requests

### Préparation
1. S'assurer que les tests passent
2. Vérifier le formatage du code
3. Mettre à jour la documentation

### Template PR
```markdown
## Description
[Description des changements]

## Type de Changement
- [ ] Nouvelle fonctionnalité
- [ ] Correction de bug
- [ ] Documentation

## Tests
- [ ] Tests unitaires ajoutés
- [ ] Tests d'intégration mis à jour

## Checklist
- [ ] Code formatté
- [ ] Documentation mise à jour
- [ ] Tests passés
```

## Documentation

### Code
- Commenter le code complexe
- KDoc pour les classes et fonctions publiques
- README à jour

### Commits
- Messages clairs et descriptifs
- Une fonctionnalité par commit
- Historique propre (rebase si nécessaire)

## Support

### Questions
- Ouvrir une issue avec le tag `question`
- Vérifier les issues existantes

### Bugs
- Tag `bug` dans les issues
- Décrire le comportement attendu/actuel
- Fournir les steps de reproduction

## Release

### Versioning
- Suivre le Semantic Versioning
- MAJOR.MINOR.PATCH
- Changelog à jour

### Publication
1. Créer une branche release
2. Tests finaux
3. Merge dans main
4. Tag version