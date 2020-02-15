package com.mogikanlol.aib.service;

import com.mogikanlol.aib.configuration.ImagesProperties;
import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.dto.NewThreadRequest;
import com.mogikanlol.aib.repository.BoardRepository;
import com.mogikanlol.aib.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final BoardRepository boardRepository;
    private final ImagesProperties imagesProperties;

    public Thread getById(Long id) {
        return threadRepository.findById(id)
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }

    public Thread create(NewThreadRequest request, MultipartFile image) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(request.getBoardId()));

        Thread thread = new Thread()
                .setContent(request.getContent())
                .setTitle(request.getTitle())
                .setBoard(board);

        if (image != null) {
            String imageName = generateImageName(image);
            thread.setImageName(imageName);
            saveImage(image, imageName);
        }

        return threadRepository.save(thread);
    }

    private void saveImage(MultipartFile image, String imageName) {
        Path path = Paths.get(imagesProperties.getPathToFolder() + "/" + imageName);
        try {
            Files.write(path, image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String generateImageName(MultipartFile file) {
        String extension = getFileExtension(file);
        return UUID.randomUUID().toString() + extension;
    }

    private String getFileExtension(MultipartFile file) {
        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    }
}
