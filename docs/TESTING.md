# Guide des Tests

## Structure des Tests

### Tests Unitaires
Situés dans `src/test/java`

```
tests/
├── core/          # Tests des composants core
├── data/          # Tests des repositories
├── viewmodel/      # Tests des ViewModels
└── utils/         # Tests des utilitaires
```

### Tests d'Intégration
Situés dans `src/androidTest/java`

```
androidTest/
├── data/          # Tests Room et DAOs
├── ui/            # Tests des écrans
└── navigation/    # Tests de navigation
```

## Exécution des Tests

### Tests Unitaires
```bash
# Tous les tests
./gradlew test

# Tests d'un module spécifique
./gradlew :app:testDebugUnitTest

# Test unique
./gradlew test --tests "*.TechnicianRepositoryTest"
```

### Tests d'Intégration
```bash
# Tous les tests
./gradlew connectedAndroidTest

# Package spécifique
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=com.damolks.ouxy3.ui
```

## Exemples de Tests

### Test Unitaire de Repository
```kotlin
@Test
fun `test insert and read technician`() = runBlocking {
    val technician = Technician(
        firstName = "John",
        lastName = "Doe",
        sector = "Nord"
    )
    val id = repository.insertTechnician(technician)
    val loaded = repository.getTechnicianById(id)
    assertEquals(technician.firstName, loaded?.firstName)
}
```

### Test d'Intégration UI
```kotlin
@Test
fun test_navigation_flow() {
    onView(withId(R.id.nextButton))
        .perform(click())
    onView(withId(R.id.signatureScreen))
        .check(matches(isDisplayed()))
}
```

## Mocking

### Mockk pour Kotlin
```kotlin
@Test
fun `test with mocked dependencies`() {
    val dao = mockk<TechnicianDao>()
    every { dao.getTechnicianById(any()) } returns technician
    
    val repository = TechnicianRepository(dao)
    val result = repository.getTechnicianById(1)
    
    verify { dao.getTechnicianById(1) }
    assertEquals(technician, result)
}
```

### Test Coroutines
```kotlin
@Test
fun `test suspend function`() = runTest {
    val result = repository.insertTechnician(technician)
    assertNotNull(result)
}
```

## Bonnes Pratiques

### Nommage des Tests
- Utiliser des noms descriptifs
- Format: `what_we_are_testing_what_should_happen`
- Ou utilisation de backticks pour phrases descriptives

### Structure AAA
1. **Arrange** : préparation des données
2. **Act** : exécution de l'action
3. **Assert** : vérification du résultat

### Tests UI
- Tests déterministes
- Gestion des animations
- Idempotence

## Coverage

### Génération du Rapport
```bash
./gradlew createDebugCoverageReport
```

### Objectifs
- Core/Data : 80%+
- ViewModels : 70%+
- UI : 50%+

## Intégration Continue

### GitHub Actions
```yaml
name: Android Tests
on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Unit Tests
        run: ./gradlew test
```
