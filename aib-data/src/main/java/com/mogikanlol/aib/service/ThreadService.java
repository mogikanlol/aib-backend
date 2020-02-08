package com.mogikanlol.aib.service;

import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.dto.NewThreadRequest;
import com.mogikanlol.aib.repository.BoardRepository;
import com.mogikanlol.aib.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final BoardRepository boardRepository;

    public Thread getById(Long id) {
        return threadRepository.findById(id)
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }

    public Thread create(NewThreadRequest request) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(request.getBoardId()));

        Thread thread = new Thread()
                .setContent(request.getContent())
                .setTitle(request.getTitle())
                .setBoard(board);

        return threadRepository.save(thread);
    }
}
