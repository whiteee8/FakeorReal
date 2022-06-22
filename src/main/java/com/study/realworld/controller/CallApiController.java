package com.study.realworld.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class CallApiController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/test")
    public String showFileUploadForm() {
        return "test_fake";
    }

    @PostMapping("/fileUpload")
    public static String callAPI(@RequestPart("image") MultipartFile file, RedirectAttributes redirectAttributes) {

        String response = "";

        try {
            String url = "http://101.79.8.8:8000/simsearch";
            MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
            ByteArrayResource resource = new ByteArrayResource(file.getBytes()){
                @Override
                public String getFilename() throws IllegalStateException {
                    return file.getOriginalFilename();
                }
            };
            multiValueMap.add("file", resource);

            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.postForObject(url, multiValueMap, String.class);

            if (response != null) {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject firstObject = jsonArray.getJSONObject(0);
                String rate = (String) firstObject.get("Rate");
                Float percent = Float.parseFloat(rate);
                redirectAttributes.addFlashAttribute("percent", percent*100);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/test";
    }
}