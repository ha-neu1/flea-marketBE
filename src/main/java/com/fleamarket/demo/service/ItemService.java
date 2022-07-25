package com.fleamarket.demo.service;

import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import com.fleamarket.demo.model.dto.FileDto;
import com.fleamarket.demo.model.dto.ItemDto;
import com.fleamarket.demo.repository.ItemRepository;
import com.fleamarket.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

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
        String orignName = file.getOriginalFilename();
        String originalFileExtension = FilenameUtils.getExtension(orignName);
        String fileName =
                UUID.randomUUID()
                        .toString()
                        .replaceAll("-", "") + "." + originalFileExtension;
        String fileUrl = "C:\\Users\\jjucc\\바탕 화면\\practice\\flea-marketBE\\src\\main\\resources\\";
        File saveFile = new File(fileUrl + orignName);
        saveFile.getParentFile().mkdir();
        file.transferTo(saveFile);
        fileUrl = fileUrl.substring("C:".length());
        return new FileDto(fileUrl, orignName, fileName);

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
