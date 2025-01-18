# Guide des Modules Ouxy3

## Concepts de Base

Un module dans Ouxy3 est un composant indépendant qui :
- Est isolé du reste de l'application
- Communique via l'EventBus
- Peut être chargé dynamiquement
- A son propre cycle de vie

## Structure d'un Module

```
module-example/
├── build.gradle.kts           # Configuration Gradle du module
├── src/
│   └── main/
│       ├── AndroidManifest.xml
│       ├── java/
│       │   └── com/damolks/ouxy3/modules/example/
│       │       ├── api/          # Interface publique
│       │       │   └── ExampleModuleApi.kt
│       │       ├── internal/     # Implémentation
│       │       │   └── ExampleModuleImpl.kt
│       │       ├── di/          # Injection de dépendances
│       │       │   └── moduleExampleModule.kt
│       │       ├── ui/          # Interface utilisateur
│       │       │   ├── ExampleActivity.kt
│       │       │   └── ExampleViewModel.kt
│       │       └── data/        # Couche données
│       │           ├── model/
│       │           └── repository/
│       └── res/                 # Ressources du module
```

## Création d'un Nouveau Module

### 1. Configuration Gradle

```kotlin
// settings.gradle.kts
include(":module-example")

// module-example/build.gradle.kts
plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(project(":app"))
}
```

### 2. Implémenter l'Interface du Module

```kotlin
// api/ExampleModuleApi.kt
interface ExampleModuleApi {
    fun initialize()
    fun onStart()
    fun onStop()
    val moduleId: String
}

// internal/ExampleModuleImpl.kt
class ExampleModuleImpl : ExampleModuleApi {
    override val moduleId = "example_module"
    
    override fun initialize() {
        // Initialisation
    }
    
    override fun onStart() {
        // Démarrage
    }
    
    override fun onStop() {
        // Arrêt
    }
}
```

### 3. Configuration de l'Injection de Dépendances

```kotlin
// di/moduleExampleModule.kt
val moduleExampleModule = module {
    single<ExampleModuleApi> { ExampleModuleImpl() }
    viewModel { ExampleViewModel(get()) }
}
```

### 4. Communication

```kotlin
// Envoi d'événement
OuxyEventBus.publish(
    ModuleEvent(
        type = "EXAMPLE_EVENT",
        data = "Some data",
        source = moduleId
    )
)

// Réception d'événement
OuxyEventBus.subscribe("CORE_EVENT") { event ->
    // Traitement de l'événement
}
```

## Tests

### Test Unitaires
```kotlin
@Test
fun `module initialization is correct`() {
    val module = ExampleModuleImpl()
    module.initialize()
    // Assertions
}
```

### Tests d'Intégration
```kotlin
@Test
fun `module communicates with core`() = runTest {
    val module = get<ExampleModuleApi>()
    val event = ModuleEvent(...)
    module.handleEvent(event)
    // Vérifier la communication
}
```

## Sécurité

### Isolation
- Chaque module s'exécute dans son propre processus
- L'accès aux ressources est contrôlé par des permissions
- La communication se fait uniquement via l'EventBus

### Permissions
```xml
<!-- AndroidManifest.xml du module -->
<manifest>
    <permission
        android:name="com.damolks.ouxy3.modules.example.PERMISSION"
        android:protectionLevel="signature" />
</manifest>
```

## Déploiement

### Configuration
```kotlin
// build.gradle.kts du module
android {
    dynamicFeatures = mutableSetOf(":module-example")
}
```

### Publication
1. Build du module : `./gradlew :module-example:bundleRelease`
2. Test du module : `./gradlew :module-example:connectedCheck`
3. Publication : Le module peut être publié séparément sur le Play Store

## Bonnes Pratiques

### Architecture
- Suivre MVVM dans les modules
- Garder les modules indépendants
- Minimiser la taille des modules
- Documenter les interfaces publiques

### Performance
- Optimiser le chargement initial
- Utiliser le lazy loading
- Gérer efficacement le cycle de vie

### Maintenance
- Versionner les interfaces
- Logger les événements importants
- Documenter les changements

## Dépannage

### Problèmes Courants
1. Module non chargé
   - Vérifier les dépendances
   - Vérifier les permissions
2. Communication défaillante
   - Vérifier l'EventBus
   - Vérifier le format des événements
3. Performances
   - Profiler le chargement
   - Optimiser les ressources