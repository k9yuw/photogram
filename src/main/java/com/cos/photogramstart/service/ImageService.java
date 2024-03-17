package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID(); // uuid 란 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약.
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
        System.out.println("이미지 파일이름: " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        // 통신, I/O가 일어날 때는 예외가 발생할 수 있다. 이런건 컴파일 시에 못잡고 런타임 때 잡을 수 있으므로 예외 처리를 다 해주어야 한다.
        try {
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        } catch(Exception e){
            e.printStackTrace();
        }

        // image 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);

    }
}
