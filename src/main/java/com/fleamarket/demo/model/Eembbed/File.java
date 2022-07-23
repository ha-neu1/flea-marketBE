package com.fleamarket.demo.model.Eembbed;

import javax.persistence.Embeddable;

@Embeddable
public class File {
    public String fileUrl;
    public String orignName;
    public String fileName; // UUID
}
