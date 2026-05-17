# Sustav za praćenje glazbenika i koncerata

## Kloniranje repozitorija (Repository cloning)

Klonirati repozitorij:

```bash
git clone https://github.com/martinaDadic/Sustav-za-pracenje-glazbenika-i-koncerata.git
```

Pozicionirati se u direktorij projekta:

```bash
cd Sustav-za-pracenje-glazbenika-i-koncerata
```

---

## Zahtjevi (Prerequisites)

- Java 21
- Node.js 20+
- PostgreSQL (preporuka za macOS: [Postgres.app](https://postgresapp.com))

---

## Postavljanje baze podataka (Database Setup)

1. Pokrenuti PostgreSQL i stvoriti novu bazu podataka:
```sql
CREATE DATABASE spgk;
```

2. Pokrenuti skriptu za stvaranje sheme nad bazom podataka `spgk`:
```
db_setup.sql
```

3. Pokrenuti skriptu za punjenje baze podataka `spgk` testnim podacima:
```
initialization.sql
```

---

## Postavljanje backenda (Backend Setup)

Backend je Spring Boot aplikacija koja se nalazi u direktoriju `spgk-backend`.

### Konfiguracija (Configuration)

Stvoriti datoteku `env.properties` u korijenskom direktoriju, `spgk-backend` (pored `build.gradle`):

```properties
DB_URL=jdbc:postgresql://localhost:5432/spgk
DB_USER=your_postgres_username
DB_PASSWORD=your_postgres_password
```

> ⚠️ Ova datoteka se navodi u .gitignore datoteci i mora se postaviti ručno na svakom uređaju.

### Pokretanje backenda (Running the backend)

```bash
cd spgk-backend
./gradlew bootRun
```

Backend će se pokrenuti na adresi `http://localhost:8080`.

### Pokretanje testova (Running tests)

```bash
cd spgk-backend
./gradlew test
```

---

## Postavljanje frontenda (Frontend Setup)

Frontend je Vue.js aplikacija koja se nalazi u direktoriju `frontend`.

### Instalacija ovisnosti (Install dependencies)

```bash
cd frontend
npm install
```

### Pokretanje frontenda (Running the frontend)

```bash
cd frontend
npm run dev
```

Frontend će se pokrenuti na adresi `http://localhost:5173`.

### Pokretanje testova (Running tests)
```bash
cd frontend
npm test
```

> ⚠️ Backend aplikacija se mora pokrenuti prije frontend aplikacije.

---

## API krajnje točke (API Endpoints)

### Koncerti (Concerts)
| Metoda | Krajnja točka | Opis |
|--------|----------|-------------|
| GET | `/api/concerts` | Dohvati sve koncerte |
| GET | `/api/concerts/{id}` | Dohvati koncert prema ID-u |
| GET | `/api/concerts/search?search=term` | Pretraži koncerte |
| POST | `/api/concerts` | Stvori novi koncert |
| PUT | `/api/concerts/{id}` | Uredi novi koncert |
| DELETE | `/api/concerts/{id}` | Obriši koncert |

### Izvođači (Artists)
| Metoda | Krajnja točka | Opis |
|--------|----------|-------------|
| GET | `/api/artists` | Dohvati sve izvođače |

### Organizatori koncerata (Organizers)
| Metoda | Krajnja točka | Opis |
|--------|----------|-------------|
| GET | `/api/organizers` | Dohvati sve organizatore koncerata |

---

## Arhitektura (Architecture)

Troslojna arhitektura (Three tier architecture):

- **Prezentacijski sloj** — REST kontroleri (`@RestController`)
- **Poslovni sloj** — Servisi (`@Service`)
- **Sloj pristupa podacima** — Repozitoriji (`@Repository`) koji koriste Spring Data JPA

---

## Potrebna tehnologija (Tech Stack)

- **Backend:** Java 21, Spring Boot 4, Spring Data JPA, PostgreSQL
- **Frontend:** Vue.js 3, Vite
- **Baza podataka:** PostgreSQL