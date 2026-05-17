package com.example.springai.pdf.controller;

import com.example.springai.pdf.dto.SearchRequest;
import com.example.springai.pdf.dto.SearchResponse;
import com.example.springai.pdf.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class PdfController {

    private final PdfService pdfService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            pdfService.indexPdf(file);
            return ResponseEntity.ok("✅ PDF uploaded and indexed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error uploading PDF: " + e.getMessage());
        }
    }

    @PostMapping("/search")
    public ResponseEntity<SearchResponse> search(@RequestBody SearchRequest request) {
        try {
            String answer = pdfService.search(request.getQuestion());
            return ResponseEntity.ok(new SearchResponse(request.getQuestion(), answer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new SearchResponse(request.getQuestion(), "❌ Error: " + e.getMessage())
            );
        }
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> searchGet(@RequestParam("q") String question) {
        try {
            String answer = pdfService.search(question);
            return ResponseEntity.ok(new SearchResponse(question, answer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new SearchResponse(question, "❌ Error: " + e.getMessage())
            );
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("✅ Spring AI PDF Search Service is running!");
    }
}