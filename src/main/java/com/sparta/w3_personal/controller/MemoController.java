package com.sparta.w3_personal.controller;

import com.sparta.w3_personal.domain.Memo;
import com.sparta.w3_personal.domain.MemoRepository;
import com.sparta.w3_personal.domain.MemoRequestDto;
import com.sparta.w3_personal.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/boards")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }
    @GetMapping("/api/boards")
    public List<Memo> getMemos() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
    }
    @GetMapping("/api/boards/{id}")
    public List<Memo> getIdMemos(@PathVariable Long id) {
        return memoRepository.findAllById(id);
    }
}
