package com.francescomalagrino.ai.SpringAIDemo.Controller;

import com.francescomalagrino.ai.SpringAIDemo.Service.ChatService;
import com.francescomalagrino.ai.SpringAIDemo.Service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenAiController {

    private final ChatService chatService;
    private final ImageService imageService;

    public GenAiController(ChatService chatService, ImageService imageService) {
        this.chatService = chatService;
        this.imageService = imageService;
    }

    @GetMapping("ask-ai")
    public String getRespose(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    /*@GetMapping("generate-image")
    public void generateImage(@RequestParam("prompt") String prompt,
                              HttpServletResponse response) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);

        // Get URL of the generated image
        String imageUrl = imageResponse.getResult().getOutput().getUrl();

        // Send redirect to the image URL
        response.sendRedirect(imageUrl);
    }

    @GetMapping("generate-image")
    public List<String> generateImage(@RequestParam("prompt") String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);

        //Streams to Get URLS of the generated image
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .collect(Collectors.toList());
        return imageUrls;
    }
    */
    @GetMapping("generate-image")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") int n,
                                       @RequestParam(defaultValue = "1024") int width,
                                       @RequestParam(defaultValue = "1024") int height) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, width, height);

        // Streams to get urls from ImageResponse
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        return imageUrls;
    }

}
