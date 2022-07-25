package com.fleamarket.demo.service;

import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import com.fleamarket.demo.model.dto.FileDto;
import com.fleamarket.demo.model.dto.ItemDto;
import com.fleamarket.demo.repository.ItemRepository;
import com.fleamarket.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
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
        FileDto fileDto = null;
        try {
            String orignName = file.getOriginalFilename();
            String originalFileExtension = FilenameUtils.getExtension(orignName);
            String fileName =
                    UUID.randomUUID()
                            .toString()
                            .replaceAll("-", "") +"."+originalFileExtension;
            String fileUrl = "C:\\Users\\jjucc\\문서\\";
            File saveFile = new File(fileUrl + orignName);
            saveFile.getParentFile().mkdir();
            file.transferTo(saveFile);
            fileDto = new FileDto(fileUrl, orignName, fileName);
            return fileDto;
        } catch (Exception exception) {
            throw new RuntimeException(" 파일을 저장할 수 없습니다");
        }finally {
            return fileDto;
        }
    }
}
