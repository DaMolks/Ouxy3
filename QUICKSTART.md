# 🚀 Guide de Démarrage Rapide - Ouxy3

## 📍 Points d'Entrée

### Flux Principal de l'Application
```
SplashActivity → OnboardingActivity (1er lancement) → MainActivity
```

### Architecture des Packages
```
com.damolks.ouxy3
├── ui/           # Interface utilisateur
│   ├── splash/     # Animation de démarrage
│   ├── onboarding/ # Configuration initiale
│   ├── main/       # Écran principal et modules
│   └── views/      # Composants réutilisables
├── data/         # Couche données
│   ├── database/   # Room et entités
│   ├── repository/ # Accès aux données
│   └── model/      # Modèles métier
└── util/         # Utilitaires
```

## ⚙️ Composants Clés

### Onboarding : Étapes
1. **TechnicianProfileFragment**
   - Collecte des informations du technicien
   - Validation des champs obligatoires
   ```kotlin
   data class TechnicianFormState(
       val firstName: String,
       val lastName: String,
       val sector: String,
       val matricule: String,
       val teamLeader: String
   )
   ```

2. **SignatureFragment**
   - Capture de signature via SignaturePad
   - Lissage du trait avec quadratique de Bézier
   ```kotlin
   class SignaturePad : View {
       // Point clé : path.quadTo() pour le lissage
   }
   ```

3. **SitesSetupFragment**
   - Ajout de sites optionnel
   - RecyclerView avec MaterialCardView

### Navigation
- Navigation Component avec nav_onboarding.xml
- Événements uniques via Event.kt

## 📝 Conventions Importantes

### Pattern MVVM
```kotlin
class ExampleViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
}
```

### Injection de Dépendances (Koin)
```kotlin
val appModule = module {
    single { TechnicianRepository(get()) }
    viewModel { TechnicianProfileViewModel(get()) }
}
```

### Styles et Thèmes
- Material Design 3
- Thèmes clair/sombre dynamiques
- Palette de couleurs définie dans colors.xml

## 🎯 Points Critiques
1. **Gestion des États**
   - Tout état UI dans le ViewModel
   - LiveData pour l'observation
   - Validation synchrone

2. **Persistance**
   - Room comme source unique de vérité
   - Coroutines pour l'asynchrone
   - Repositories pour l'abstraction

3. **Modularité**
   - Modules indépendants
   - Communication via interfaces
   - Injection de dépendances

## 📄 Fichiers à Consulter en Premier
1. app/build.gradle.kts - Configuration et dépendances
2. OuxyApplication.kt - Point d'entrée et setup Koin
3. ui/splash/SplashActivity.kt - Démarrage et navigation
4. data/database/OuxyDatabase.kt - Schéma de données