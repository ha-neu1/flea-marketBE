package com.fleamarket.demo.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fleamarket.demo.model.Comment;
import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import com.fleamarket.demo.model.dto.CommentResponseDto;
import com.fleamarket.demo.model.dto.FileDto;
import com.fleamarket.demo.model.dto.ItemDto;
import com.fleamarket.demo.repository.CommentRepository;
import com.fleamarket.demo.repository.ItemRepository;
import com.fleamarket.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final AmazonS3Client amazonS3Client;

    private final CommentRepository commentRepository;

    private String S3Bucket = "test-bucket-hong"; // Bucket 이름

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

    public CommentResponseDto showItems(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("아이템이 존재하지 않습니다.")

        );
        List<Comment> allByItemId = commentRepository.findAllByItemId(itemId);
        return new CommentResponseDto(item, allByItemId);
    }
}
