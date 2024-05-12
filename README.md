# Superflix

- [How to Setup](#how-to-setup)
- [Stack](#stack)
- [API Endpoints](#api-endpoints)

## How to Setup

- Copy environment variables. Replace values to your own.

```bash
cp .env.example .env
```

- Build the project with [docker-compose](https://docs.docker.com/compose/).

```bash
docker-compose build
```

- Start containers.

```bash
docker-compose up --detach
```

## Stack

```javascript
    stack: [
        "springboot",
        "mariadb",
        "docker"
    ];
```

## API Endpoints

### <u>Auth</u>

| Method   | URL               | Description           |
|----------|-------------------|-----------------------|
| `POST`   | `/auth/login`     | Sign in an account    |
| `POST`   | `/auth/register`  | Sign up to an account |

### <u>Movie</u>

| Method   | URL                          | Description    |
|----------|------------------------------|----------------|
| `POST`   | `admin\|pro/movie`           | Create a movie |
| `GET`    | `admin\|pro\|user/movie`     | Get all movie  |
| `GET`    | `admin\|pro\|user/movie/:id` | Get a movie    |
| `PATCH`  | `admin\|pro/movie/:id`       | Update a movie |
| `DELETE` | `admin\|pro/movie/:id`       | Delete a movie |

### <u>Serie</u>

| Method   | URL                          | Description    |
|----------|------------------------------|----------------|
| `POST`   | `admin\|pro/serie`           | Create a serie |
| `GET`    | `admin\|pro\|user/serie`     | Get all series |
| `GET`    | `admin\|pro\|user/serie/:id` | Get a serie    |
| `PATCH`  | `admin\|pro/serie/:id`       | Update a serie |
| `DELETE` | `admin\|pro/serie/:id`       | Delete a serie |

### <u>Season</u>

| Method   | URL                           | Description     |
|----------|-------------------------------|-----------------|
| `POST`   | `admin\|pro/season`           | Create a season |
| `GET`    | `admin\|pro\|user/season`     | Get all seasons |
| `GET`    | `admin\|pro\|user/season/:id` | Get a season    |
| `PATCH`  | `admin\|pro/season/:id`       | Update a season |
| `DELETE` | `admin\|pro/season/:id`       | Delete a season |

### <u>Episode</u>

| Method   | URL                            | Description       |
|----------|--------------------------------|-------------------|
| `POST`   | `admin\|pro/episode`           | Create an episode |
| `GET`    | `admin\|pro\|user/episode`     | Get all episodes  |
| `GET`    | `admin\|pro\|user/episode/:id` | Get an episode    |
| `PATCH`  | `admin\|pro/episode/:id`       | Update an episode |
| `DELETE` | `admin\|pro/episode/:id`       | Delete an episode |
