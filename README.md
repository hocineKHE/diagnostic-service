# Diagnostic Service

Système de diagnostic automatique basé sur l'index de santé patient.

## Règles métier

| Index de santé | Unité médicale |
|---|---|
| Multiple de 3 | Cardiologie |
| Multiple de 5 | Traumatologie |
| Multiple de 3 et 5 | Cardiologie + Traumatologie |
| Aucun multiple | Aucune pathologie détectée |

## Stack

- Java 21 · Spring Boot 3.2 · Maven

## Lancer le projet

```bash
mvn spring-boot:run
```

## Lancer les tests

```bash
mvn test
```

## API

```bash
# Succès
GET /api/diagnostic?healthIndex=33  → 200 CARDIOLOGIE
GET /api/diagnostic?healthIndex=55  → 200 TRAUMATOLOGIE
GET /api/diagnostic?healthIndex=15  → 200 CARDIOLOGIE, TRAUMATOLOGIE
GET /api/diagnostic?healthIndex=7   → 200 Aucune pathologie détectée

# Erreur
GET /api/diagnostic?healthIndex=-1  → 400 {"code":"INVALID_INPUT","message":"L'index de santé doit être un entier strictement positif"}
GET /api/diagnostic?healthIndex=0   → 400 {"code":"INVALID_INPUT","message":"L'index de santé doit être un entier strictement positif"}
```