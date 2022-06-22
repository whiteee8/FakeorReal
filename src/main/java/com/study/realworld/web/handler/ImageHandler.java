package com.study.realworld.web.handler;

import com.study.realworld.domain.article.Image;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ImageHandler {
    @Transactional
    public Image parseFileInfo(MultipartFile multipartFile, Long orderId) throws Exception {
        // return files

        // if empty, return null
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        // save file as name 'update-date'
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = simpleDateFormat.format(new Date());

        // set absolute root for saving in project folder
        String absolutePath = new File("").getAbsolutePath() + "/";

        // set root and save there
        String path = "media/" + currentDate;
        File file = new File("src/main/resources/static/assets/" + path);

        // not exist the root, mkdir the root
        if (!file.exists()) {
            file.mkdirs();
        }

        Image image = null;
        // manage files
        if (!multipartFile.isEmpty()) {
            // jpeg, png, gif
            String contentType = multipartFile.getContentType();
            String originalFileExtension;

            // check extention
            if (ObjectUtils.isEmpty(contentType)) {
                return null;
            } else {
                if (contentType.contains("image/jpeg")) {
                    originalFileExtension = ".jpg";
                } else if (contentType.contains("image/png")) {
                    originalFileExtension = ".png";
                } else if (contentType.contains("image/gif")) {
                    originalFileExtension = ".gif";
                } else {
                    return null;
                }
            }

            String newFileName = System.nanoTime() + originalFileExtension;
            // add file in list
            image = Image.builder()
                    .orderId(orderId)
                    .originalFileName(multipartFile.getOriginalFilename())
                    .storedFilePath(path + "/" + newFileName)
                    .fileSize(multipartFile.getSize())
                    .build();

            // init file to new file
            file = new File(absolutePath + "src/main/resources/static/assets/" + path + "/" + newFileName);
            multipartFile.transferTo(file);
        }
        return image;
    }
}
