package com.rissins.board.service;

import com.rissins.board.domain.Attachment;
import com.rissins.board.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    public void save(Attachment attachment) {
        attachmentRepository.save(attachment);
    }
}
