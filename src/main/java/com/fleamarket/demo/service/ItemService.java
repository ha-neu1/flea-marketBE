package com.fleamarket.demo.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import com.fleamarket.demo.model.dto.FileDto;
import com.fleamarket.demo.model.dto.ItemDto;
import com.fleamarket.demo.repository.ItemRepository;
import com.fleamarket.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final AmazonS3Client amazonS3Client;

    private String S3Bucket = "flea-bucket";// Bucket 이름

    public ItemDto saveImage(ItemDto itemDto, MultipartFile file, String username) throws IOException {
        FileDto fileDto = createFile(file);
        com.fleamarket.demo.model.Eembbed.File saveFile =
                new com.fleamarket.demo.model.Eembbed.File(fileDto);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("로그인을 해주시길 바랍니다"));

        Item item = new Item(itemDto);
        item.setFile(saveFile);
        item.setRelationUser(user);

        itemRepository.save(item);
        return itemDto;
    }

    private FileDto createFile(MultipartFile file) throws IOException {
        String originalName = file.getOriginalFilename();
        long size = file.getSize();
        String originalFileExtension = FilenameUtils.getExtension(originalName);

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(file.getContentType());
        objectMetaData.setContentLength(size);

        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, originalName, file.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String fileName =
                UUID.randomUUID()
                        .toString()
                        .replaceAll("-", "") + "." + originalFileExtension;

        String fileUrl = amazonS3Client.getUrl(S3Bucket, originalName).toString();
        return new FileDto(fileUrl, originalName, fileName);
    }

    public Resource showImage(String username) throws IOException {
        Item item = itemRepository.findByUser_Username(username).orElseThrow(
                () -> new IllegalArgumentException("상품을 찾을 수 없습니다.")
        );
        com.fleamarket.demo.model.Eembbed.File file = item.getFile();
        if (file == null) {
            throw new IllegalArgumentException("이미지를 찾을 수 없습니다.");
        }
        //        String encodedUploadFileName = UriUtils.encode(file.getOrignName(), StandardCharsets.UTF_8);
        System.out.println("file:"+ file.getFileUrl() + file.getOrignName());
        Resource urlResource = new UrlResource("file:"+ file.getFileUrl() + file.getOrignName());
        if (urlResource == null) {
            throw new RuntimeException("이미지를 찾을 수 없습니다");
        }
        return urlResource;
    }
}
