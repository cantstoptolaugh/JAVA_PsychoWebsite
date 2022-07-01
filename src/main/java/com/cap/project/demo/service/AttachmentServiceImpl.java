package com.cap.project.demo.service;

import com.cap.project.demo.domain.Attachment;
import com.cap.project.demo.domain.FileStore;
import com.cap.project.demo.domain.enums.AttachmentType;
import com.cap.project.demo.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl {

    private final AttachmentRepository attachmentRepository;
    private final FileStore fileStore;

    public List<Attachment> saveAttachments(Map<AttachmentType, List<MultipartFile>> multipartFileListMap) throws IOException {

        List<Attachment> imageFiles = fileStore.storeFiles(multipartFileListMap.get(AttachmentType.IMAGE), AttachmentType.IMAGE);
        //List<Attachment> generalFiles = fileStore.storeFiles(multipartFileListMap.get(AttachmentType.GENERAL), AttachmentType.GENERAL);

        List<Attachment> result = Stream.of(imageFiles)
                .flatMap(f -> f.stream())
                .collect(Collectors.toList());

        return result;
    }


}
