package com.example.springai.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    private final ChatClient chatClient;
    private String pdfContext = "";

    public PdfService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public void indexPdf(MultipartFile file) throws Exception {
        try (InputStream is = file.getInputStream(); PDDocument doc = PDDocument.load(is)) {
            PDFTextStripper stripper = new PDFTextStripper();
            this.pdfContext = stripper.getText(doc);
            System.out.println("✅ PDF indexed. Total characters: " + pdfContext.length());
        }
    }

    public String search(String question) {
        if (pdfContext.isEmpty()) {
            return "❌ No PDF has been uploaded yet. Please upload a PDF first.";
        }

        // Create a system message with the PDF context
        String systemPrompt = "You are a helpful assistant. Answer questions based on the following PDF content:\n\n" +
                              pdfContext + "\n\n" +
                              "If the answer is not in the PDF, say 'This information is not available in the uploaded PDF.'";

        List<Message> messages = new ArrayList<>();
        messages.add(new SystemMessage(systemPrompt));
        messages.add(new UserMessage(question));

        Prompt prompt = new Prompt(messages);
        ChatResponse response = chatClient.call(prompt);

        return response.getResult().getOutput().getContent();
    }
}