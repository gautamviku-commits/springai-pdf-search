# Spring AI PDF Search

A Spring Boot application that demonstrates PDF upload and semantic search using Spring AI and OpenAI.

## Features

- ✅ Upload PDF files
- ✅ Extract and index PDF content
- ✅ Ask questions about PDF content
- ✅ Get AI-powered answers using GPT-3.5-turbo
- ✅ RESTful API endpoints

## Prerequisites

- Java 17+
- Maven 3.8+
- OpenAI API Key

## Setup

### 1. Clone the repository
```bash
git clone https://github.com/gautamviku-commits/springai-pdf-search.git
cd springai-pdf-search
```

### 2. Set OpenAI API Key
```bash
export OPENAI_API_KEY=your_openai_api_key_here
```

### 3. Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### 1. Upload PDF
```bash
curl -X POST -F "file=@path/to/your/file.pdf" http://localhost:8080/api/pdf/upload
```

**Response:**
```
✅ PDF uploaded and indexed successfully.
```

### 2. Search (GET)
```bash
curl "http://localhost:8080/api/pdf/search?q=What%20is%20the%20main%20topic?"
```

**Response:**
```json
{
  "question": "What is the main topic?",
  "answer": "The PDF discusses..."
}
```

### 3. Search (POST)
```bash
curl -X POST http://localhost:8080/api/pdf/search \
  -H "Content-Type: application/json" \
  -d '{"question": "What are the key points?"}'
```

**Response:**
```json
{
  "question": "What are the key points?",
  "answer": "Based on the PDF content, the key points are..."
}
```

### 4. Service Status
```bash
curl http://localhost:8080/api/pdf/status
```

**Response:**
```
✅ Spring AI PDF Search Service is running!
```

## Project Structure

```
springai-pdf-search/
├── src/main/java/com/example/springai/pdf/
│   ├── SpringAiPdfSearchApplication.java
│   ├── controller/
│   │   └── PdfController.java
│   ├── service/
│   │   └── PdfService.java
│   └── dto/
│       ├── SearchRequest.java
│       └── SearchResponse.java
├── src/main/resources/
│   └── application.properties
├── pom.xml
├── .gitignore
└── README.md
```

## Configuration

Edit `application.properties` to customize:
- `server.port` - Server port (default: 8080)
- `spring.ai.openai.model` - AI model (default: gpt-3.5-turbo)
- `spring.ai.openai.temperature` - Response creativity (0-1, default: 0.7)

## Usage Example

1. Start the application
2. Upload a PDF:
   ```bash
   curl -X POST -F "file=@sample.pdf" http://localhost:8080/api/pdf/upload
   ```
3. Ask questions:
   ```bash
   curl "http://localhost:8080/api/pdf/search?q=Summarize%20this%20document"
   ```

## Technologies

- Spring Boot 3.2.5
- Spring AI 0.8.1
- OpenAI API
- Apache PDFBox 2.0.29
- Lombok
- Maven

## License

MIT License

## Author

gautamviku-commits

## Support

For issues or questions, create an issue in the repository.