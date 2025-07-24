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