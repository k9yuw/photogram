package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;
    @GetMapping({"/","/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping({"/image/popular"})
    public String popular() {
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        //서비스 호출

        imageService.사진업로드(imageUploadDto, principalDetails);
        return "redirect:/user/"+principalDetails.getUser().getId();
    }
}
