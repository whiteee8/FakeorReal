package com.study.realworld.service.vote;

import com.study.realworld.domain.article.ArticleRepository;
import com.study.realworld.domain.vote.Vote;
import com.study.realworld.domain.vote.VoteHistory;
import com.study.realworld.domain.vote.VoteHistoryRepository;
import com.study.realworld.domain.vote.VoteRepository;
import com.study.realworld.web.dto.vote.VoteArticleRequestDto;
import com.study.realworld.web.dto.vote.VoteHistorySaveRequestDto;
import com.study.realworld.web.dto.vote.VoteSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteHistoryRepository voteHistoryRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public Long save(VoteHistorySaveRequestDto requestDto) {
        VoteHistory voteHistory = voteHistoryRepository.findByArticleIdAndUserId(requestDto.getArticleId(), requestDto.getUserId());
        if (voteHistory == null){
            Optional<Vote> vote = voteRepository.findByArticleId(requestDto.getArticleId());
            if (!vote.isPresent())
                vote = Optional.ofNullable(voteRepository.save(new VoteSaveRequestDto(requestDto).toEntity()));
            vote.get().update(requestDto.isFake());

            return voteHistoryRepository.save(requestDto.toEntity()).getId();
        }
        else
            return -1L;
    }

    @Transactional
    public Long update(Long id, Long user_id, boolean fake) {
        VoteHistory voteHistory = voteHistoryRepository.findByArticleIdAndUserId(id, user_id);
        if (voteHistory == null) {
            Vote Vote = voteRepository.findByArticleId(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 투표에 권한이 없습니다. id=" + id));

            Vote.update(fake);
        }
        return id;
    }

    public List<VoteArticleRequestDto> findTop10By() {
        List<Vote> entity = voteRepository.findTop10ByOrderByFakeTrueDesc();

        List<VoteArticleRequestDto> list = new ArrayList<>();
        for (Vote e : entity) {
            String articleTitle = articleRepository.findTitleById(e.getArticleId());
            list.add(new VoteArticleRequestDto(articleTitle, e));
        }

        return list;
    }
}
