# POST /api/notes with JSON body:
{
	"title": "Spring Boot",
	"content": "Build enotes project step by step"
}


# GET /api/notes


# Example on Validation Error
POST localhost:8080/api/notes
{
  "title": "",
  "content": ""
}

# Page 
GET /api/notes/paginated?page=1&size=3

# archived
PUT localhost:8080/api/notes/8/archive

PUT localhost:8080/api/notes/8/unarchive

GET localhost:8080/api/notes/archived

# update note
PUT /api/notes/5

{
  "title": "Updated title",
  "content": "Updated content"
}

PUT /api/notes/999

# delete note
DELETE /api/notes/8

DELETE /api/notes/{invalid_id}

DELETE /api/notes/{archieved_id}

# register user
POST localhost:8080/api/auth/register
{
  "fullName": "Rohit Das",
  "email": "rohit@example.com",
  "password": "test1234"
}

# login
POST localhost:8080/api/auth/login

{
  "email": "rohit@example.com",
  "password": "test1234"
}

# header

GET localhost:8080/api/notes
Authorization Bearer token

# get note by id
GET localhost:8080/api/notes/3
Authorization Bearer token

# Secure & RESTful API Structure
| Method   | Endpoint                    | Purpose                  |
| -------- | --------------------------- | ------------------------ |
| `GET`    | `/api/notes`                | All notes (current user) |
| `GET`    | `/api/notes/{id}`           | ✅ Fetch one note         |
| `POST`   | `/api/notes`                | Create note              |
| `PUT`    | `/api/notes/{id}`           | Update note              |
| `DELETE` | `/api/notes/{id}`           | Delete note              |
| `PUT`    | `/api/notes/{id}/archive`   | Archive note             |
| `PUT`    | `/api/notes/{id}/unarchive` | Unarchive note           |
| `GET`    | `/api/notes/archived`       | Archived notes           |

# Restore soft deleted note
Soft delete a note:
DELETE /api/notes/{id}

Restore it:
PUT /api/notes/{id}/restore

Check list again:
GET /api/notes

# Permanently delete a note
Soft delete a note:
DELETE /api/notes/{id}

Permanently delete it:
DELETE /api/notes/{id}/permanent

Check the DB: that note should be gone from the table.

# Login as your admin
POST /api/auth/register
{
  "fullName": "Admin User",
  "email": "admin@example.com",
  "password": "admin123",
  "role": "ADMIN"
}
Login as your admin
POST /api/auth/login
{
  "email": "admin@enotes.com",
  "password": "Admin@123"
}
Copy the returned JWT token.
GET /api/admin/users

Retry accessing /api/admin/users with:
Regular user token
should return 403

# total user from admin stats
login as admin:
	POST localhost:8080/api/auth/login
copy auth token
localhost:8080/api/admin/stats

try from normal user should get 403