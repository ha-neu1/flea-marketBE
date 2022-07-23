package com.fleamarket.demo.model.Eembbed;

import com.fleamarket.demo.model.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class File {
    public String fileUrl;
    public String orignName;
    public String fileName; // UUID

    public File(FileDto fileDto) {
        this.fileUrl = fileDto.getFileUrl();
        this.orignName = fileDto.getOrignName();
        this.fileName = fileDto.getFileName();
    }
}
